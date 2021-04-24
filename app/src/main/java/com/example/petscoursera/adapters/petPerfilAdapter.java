package com.example.petscoursera.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petscoursera.R;
import com.example.petscoursera.pojos.petsArrays;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class petPerfilAdapter extends RecyclerView.Adapter<petPerfilAdapter.petperfilViewHolder>{

    //This list contains the petArrays objects that will use the adapter
    ArrayList<petsArrays> petPerfilArrayList;

    //Constructor for petPrfilAdapter
    public petPerfilAdapter(ArrayList<petsArrays> petPerfilArraylist){
        this.petPerfilArrayList = petPerfilArraylist;}

    @NonNull
    @Override
    //Here we inflate the layout cardview, we give it the views it is going to use and then we pass that
    //information to the viewholder so it can get the views
    public petperfilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mypetphotos_cardview, parent, false);

        return new petperfilViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull petperfilViewHolder holder, int position) {
        petsArrays petPerfil = petPerfilArrayList.get(position);
        holder.imvCardView2.setImageResource(petPerfil.getPetPhoto());
        holder.tvCounter2.setText(NumberToString(petPerfil.getPetLikes()));

        //This method counts the number of likes for each pet
        holder.imButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int likes = petPerfil.getPetLikes()+1;
                petPerfil.setPetLikes(likes);
                holder.tvCounter2.setText(NumberToString(likes));
            }
        });
    }

    @Override
    //This one returns the information of how many elements are in the list petsArrayList
    public int getItemCount() {return petPerfilArrayList.size();}

    //ViewHolder
    public static class petperfilViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imvCardView2;
        private final TextView tvCounter2;
        private final ImageButton imButton2;

        //Constructor for petperfilViewHolder
        public petperfilViewHolder(@NonNull View itemView) {
            super(itemView);
            imvCardView2 = (ImageView) itemView.findViewById(R.id.imvCardView2);
            tvCounter2   = (TextView)  itemView.findViewById(R.id.tvCounter2);
            imButton2    = (ImageButton) itemView.findViewById(R.id.imvYellowBone2);
        }
    }

    //-----------------------------------------------------------------------------------------------------------//
    //Method to convert from number to string
    public String NumberToString(int number){return ""+number;}
}

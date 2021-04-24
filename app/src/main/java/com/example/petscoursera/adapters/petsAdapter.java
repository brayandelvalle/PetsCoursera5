package com.example.petscoursera.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petscoursera.R;
import com.example.petscoursera.pojos.petsArrays;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class petsAdapter extends RecyclerView.Adapter<petsAdapter.petsViewHolder> {
    //This list contains the petArrays objects that will use the adapter
    ArrayList<petsArrays> petsArraysList;
    Activity activity1;

    //-----------------------------------------------------------------------------------------------------------//
    //the constructor for petsAdapter
    public petsAdapter(ArrayList<petsArrays> petsArraysList, Activity activity1){
        this.petsArraysList = petsArraysList;
        this.activity1 = activity1;
    }

    //-----------------------------------------------------------------------------------------------------------//
    @NonNull
    @Override
    //Here we inflate the layout cardview, we give it the views it is going to use and then we pass that
    //information to the viewholder so it can get the views
    public petsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflator. This one associate the cardview layout pets_info.xml, that will be recycled, with the recyclerview.
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pets_info, parent, false);

        //We are going to return a petsViewHolder object to be able to manage the views.
        return new petsViewHolder(v);
    }

    //-----------------------------------------------------------------------------------------------------------//
    @Override
    //Here we pass the list of pets petsArraysList to the views "imvCardView", "ibtBone"...
    //So, here we are setting/associating the elements to the views.
    public void onBindViewHolder(@NonNull petsViewHolder holder, int position) {
        petsArrays pets = petsArraysList.get(position);
        holder.imvCardView.setImageResource(pets.getPetPhoto());
        holder.tvPetName.setText(pets.getPetName());
        holder.tvCounter.setText(NumberToString(pets.getPetLikes()));

        //This method counts the number of likes for each pet
        holder.ibtBone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int likes = pets.getPetLikes()+1;
                pets.setPetLikes(likes);
                holder.tvCounter.setText(NumberToString(likes));
            }
        });

        //Pet info in a Toast when touching the pet photo
        holder.imvCardView.setOnClickListener(v -> {
            String petInfo = pets.getPetName() + ", " + pets.getPetLikes() + " likes";
            Toast.makeText(activity1, petInfo, Toast.LENGTH_SHORT).show();
        });
    }

    //-----------------------------------------------------------------------------------------------------------//
    @Override
    //This one returns the information of how many elements are in the list petsArrayList
    public int getItemCount() {return petsArraysList.size();}

    //-----------------------------------------------------------------------------------------------------------//
    //This is the viewholder, here we connect to the views in the CardView pets_info.xml with the recyclerview.
    public static class petsViewHolder extends RecyclerView.ViewHolder{

        //Creating the view objects in the cardview
        private final ImageView imvCardView;
        private final TextView tvPetName;
        private final TextView tvCounter;
        private final ImageButton ibtBone;


        //Constructor for petsViewHolder
        public petsViewHolder(@NonNull View itemView) {
            super(itemView);
            //Associating the views and the objects:
            imvCardView = (ImageView) itemView.findViewById(R.id.imvCardView);
            tvPetName   = (TextView) itemView.findViewById(R.id.tvPetName);
            tvCounter   = (TextView) itemView.findViewById(R.id.tvCounter);
            ibtBone     = (ImageButton) itemView.findViewById(R.id.ibtBone);

        }
    }
    //-----------------------------------------------------------------------------------------------------------//
    //Method to convert from number to string
    public String NumberToString(int number){return ""+number;}
}



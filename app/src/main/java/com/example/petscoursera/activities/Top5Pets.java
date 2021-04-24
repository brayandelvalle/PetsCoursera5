package com.example.petscoursera.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.petscoursera.R;
import com.example.petscoursera.adapters.petsAdapter;
import com.example.petscoursera.pojos.petsArrays;

import java.util.ArrayList;

public class Top5Pets extends AppCompatActivity {

    ArrayList<petsArrays> petsInfoArrayList2;
    RecyclerView PetsList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top5_pets);

        //Support for the action bar
        //----------------------------------------------------------------------------------------------//

        Toolbar MyToolbar2 = (Toolbar) findViewById(R.id.MyActionBar2);
        setSupportActionBar(MyToolbar2);
        ActionBar MyActionBar = getSupportActionBar();
        if(MyActionBar != null){MyActionBar.setDisplayHomeAsUpEnabled(true);}

        //Receiving the info for the pets and creating the petsArrays object
        //----------------------------------------------------------------------------------------------//
        ArrayList<String> petsNamesTopSort   = (ArrayList<String>) getIntent().getSerializableExtra("petsNamesTopSort");
        ArrayList<Integer> petsLikesTopSort  = (ArrayList<Integer>) getIntent().getSerializableExtra("petsLikesTopSort");

        //Creating the petsArrays object with the top 5 liked pets:
        petsInfoArrayList2 = new ArrayList<>();
        for (int pos=0; pos<petsNamesTopSort.size()-1; ++pos){
            petsInfoArrayList2.add(new petsArrays(petsNamesTopSort.get(pos), petsLikesTopSort.get(pos), petNameToIntPhoto(petsNamesTopSort.get(pos)) ));}

        /*
        TextView tvProof = (TextView) findViewById(R.id.tvPetName);
        String proofText = petsInfoArrayList2.get(0).getPetName()+" "+petsInfoArrayList2.get(0).getPetLikes();
        tvProof.setText(proofText); */


        //RecyclerView
        //----------------------------------------------------------------------------------------------//
        PetsList2 = (RecyclerView) findViewById(R.id.rvPets2);

        //Now we decide how we want to show the list of pets; linear, grid, staggered
        //We are going to use a LinearLayoutManager
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        PetsList2.setLayoutManager(llm);

        //Initializing the adapter
        InitializePetsAdapter2();

        /*
        //Now, we are sending back the info of each pet to main activity.
        //----------------------------------------------------------------------------------------------//
        Intent int21 = new Intent(this, MainActivity.class);
        int CONFIRM = 1;
        int21.putExtra("CONFIRM", CONFIRM);
        int21.putExtra("petsNamesTopSort2", petsNamesTopSort);
        int21.putExtra("petsLikesTopSort2", petsLikesTopSort);
        */

    }

    //This method will initialize to petsAdapter.
    //It creates an object of petsAdapter, gives it the list of pets petsInfoArrayList so petsAdapter can use it.
    //The adapter calls the layout with the cardview pets_info
    //-------------------------------------------------------------------------------------------------------------------//
    private void InitializePetsAdapter2(){
        petsAdapter adapter = new petsAdapter(petsInfoArrayList2, this);
        PetsList2.setAdapter(adapter);}

    // This methods takes the pet name and turns it into its picture
    public int petNameToIntPhoto(String name){
        switch (name){
            case "Rasputia": return R.drawable.rasputia;
            case "Kraken":return R.drawable.kraken;
            case "Cerbero":return R.drawable.cerbero;
            case "Unicornio":return R.drawable.unicornio;
            case "Grifo":return R.drawable.grifo;
            case "Hidra":return R.drawable.hidra;
            default:return R.drawable.rasputia;}
    }

}

package com.example.petscoursera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.lang.String.*;

public class MainActivity extends AppCompatActivity {

    //Object "petsArray"
    ArrayList<petsArrays> petsInfoArrayList;

    //Recyclerview
    RecyclerView PetsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Support for the action bar
        //----------------------------------------------------------------------------------------------//
        Toolbar MyToolbar1 = findViewById(R.id.MyActionBar1);
        setSupportActionBar(MyToolbar1);

        //RecyclerView
        //----------------------------------------------------------------------------------------------//
        //Declaring the recyclerview object
        PetsList = (RecyclerView) findViewById(R.id.rvPets);

        //Now we decide how we want to show the list of pets; linear, grid, staggered
        //We are going to use a LinearLayoutManager
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        PetsList.setLayoutManager(llm);

        //Initializing the list of mythical pets
        InitializePetsList();

        //Initializating the adapter
        InitializePetsAdapter();

        /*
        //Receiving back the information of each pet from Top5Pets
        //----------------------------------------------------------------------------------------------//
        int CONFIRM = getIntent().getIntExtra("CONFIRM", 0);
        if(CONFIRM==1){
            ArrayList<String> names2 = (ArrayList<String>) getIntent().getSerializableExtra("petsNamesTopSort2");
            ArrayList<Integer> likes2 = (ArrayList<Integer>) getIntent().getSerializableExtra("petsLikesTopSort2");

            //Now, to make it easier to reassign the elements of each pet we are going to use a Map, which is a kind of dictionary for java:
            Map<String, Integer> mapNameLikes = new HashMap<String, Integer>();
            for(int pos=0; pos<names2.size(); ++pos){mapNameLikes.put(names2.get(pos), likes2.get(pos)) ;}

            //Using the mapNameLikes object to reassign the info of each pet:
            for (int pos=0; pos<petsInfoArrayList.size(); ++pos){
                String petName = petsInfoArrayList.get(pos).getPetName();
                petsInfoArrayList.get(pos).setPetLikes(mapNameLikes.get(petName)) ;}}
        */
    }

        //Inflating the menu options.
        //----------------------------------------------------------------------------------------------//
        @Override
        public boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.menu.toolbaroptions, menu);
            return true;
        }

        //Giving the actions for the each item of the menu
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if(id==R.id.btTop5){
                //Sorting the pets by their likes
                //We first create a new petArrays ArrayList with the same information that has petsInfoArrayList but that will be sorted.
                ArrayList<petsArrays>petsInfoArrayListSort;
                petsInfoArrayListSort = new ArrayList<>();
                for(int pos=0; pos<petsInfoArrayList.size(); ++pos ){petsInfoArrayListSort.add(new petsArrays(
                            petsInfoArrayList.get(pos).getPetName(),
                            petsInfoArrayList.get(pos).getPetLikes(),
                            petsInfoArrayList.get(pos).getPetPhoto() ));}

                //And now we sort it by using the Collections.sort() method on petsInfoArrayListSort
                Collections.sort(petsInfoArrayListSort);

                //Sending the information to the second activity Top5Pets
                //For doing this we send the names, likes and photos of the sorted petsInfoArrayListSort separated to
                //create the petsArrays object in the Top5Pets activity once again.
                ArrayList<String> petsNamesTopSort = new ArrayList<>();
                ArrayList<Integer> petsLikesTopSort = new ArrayList<>();

                for (int pos=0; pos<petsInfoArrayListSort.size(); ++pos){
                    petsNamesTopSort.add(petsInfoArrayListSort.get(pos).getPetName());
                    petsLikesTopSort.add(petsInfoArrayListSort.get(pos).getPetLikes());}

                Intent intent12 = new Intent(this, Top5Pets.class);
                intent12.putExtra("petsNamesTopSort",  petsNamesTopSort);
                intent12.putExtra("petsLikesTopSort",  petsLikesTopSort);
                startActivity(intent12);
            }
            return super.onOptionsItemSelected(item);
        }

    //This method will initialize to petsAdapter.
    //It creates an object of petsAdapter, gives it the list of pets petsInfoArrayList so petsAdapter can use it.
    //The adapter calls the layout with the cardview pets_info
    //-------------------------------------------------------------------------------------------------------------------//
    private void InitializePetsAdapter(){
        petsAdapter adapter = new petsAdapter(petsInfoArrayList, this);
        PetsList.setAdapter(adapter);}

    //Method to initialize the list of pets.
    //-------------------------------------------------------------------------------------------------------------------//
    public void InitializePetsList(){
        //ArrayList with the name, likes and photo of each pet.
        petsInfoArrayList = new ArrayList<>();
        petsInfoArrayList.add(new petsArrays("Rasputia", 0, R.drawable.rasputia));
        petsInfoArrayList.add(new petsArrays("Kraken",   0, R.drawable.kraken));
        petsInfoArrayList.add(new petsArrays("Cerbero",  0, R.drawable.cerbero));
        petsInfoArrayList.add(new petsArrays("Unicornio",0, R.drawable.unicornio));
        petsInfoArrayList.add(new petsArrays("Grifo",    0, R.drawable.grifo));
        petsInfoArrayList.add(new petsArrays("Hidra",    0, R.drawable.hidra)); }
}
package com.example.petscoursera.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.petscoursera.activities.Acercade;
import com.example.petscoursera.activities.Contacto;
import com.example.petscoursera.R;
import com.example.petscoursera.activities.Top5Pets;
import com.example.petscoursera.adapters.petsAdapter;
import com.example.petscoursera.pojos.petsArrays;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class RecyclerviewFragment extends Fragment {

    //Object "petsArray"
    ArrayList<petsArrays> petsInfoArrayList;

    //Recyclerview
    RecyclerView PetsList;

    // Required empty public constructor
    public RecyclerviewFragment() { }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        //This line allows to work the method onCreateOptionsMenu
        setHasOptionsMenu(true);

        //RecyclerView
        //----------------------------------------------------------------------------------------------//
        //Declaring the recyclerview object
        PetsList = (RecyclerView) v.findViewById(R.id.rvPets);

        //Now we decide how we want to show the list of pets; linear, grid, staggered
        //We are going to use a LinearLayoutManager
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        PetsList.setLayoutManager(llm);

        //Initializing the list of mythical pets
        InitializePetsList();

        //Initializating the adapter
        InitializePetsAdapter();

        return v;
    }

    //Inflating the menu options.
    //----------------------------------------------------------------------------------------------//
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.toolbaroptions, menu);
    }

    //Giving the actions for each item of the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //Star button
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

            Intent intent12 = new Intent(getActivity(), Top5Pets.class);
            intent12.putExtra("petsNamesTopSort",  petsNamesTopSort);
            intent12.putExtra("petsLikesTopSort",  petsLikesTopSort);
            startActivity(intent12);
        }
        //The option "Contacto" in the menu options
        else if(id==R.id.menOpt_Contacto){
            Toast.makeText(getActivity(), "Contacto", Toast.LENGTH_SHORT).show();
            Intent iMainContacto = new Intent(getActivity(), Contacto.class);
            startActivity(iMainContacto);
        }
        //The option "Acercade" in the menu options
        else if(id==R.id.menOpt_About){
            Toast.makeText(getActivity(), "Acerca de", Toast.LENGTH_SHORT).show();
            Intent iMainAcercade = new Intent(getActivity(), Acercade.class);
            startActivity(iMainAcercade);
        }

        return super.onOptionsItemSelected(item);
    }

    //This method will initialize to petsAdapter.
    //It creates an object of petsAdapter, gives it the list of pets petsInfoArrayList so petsAdapter can use it.
    //The adapter calls the layout with the cardview pets_info
    //-------------------------------------------------------------------------------------------------------------------//
    private void InitializePetsAdapter(){
        petsAdapter adapter = new petsAdapter(petsInfoArrayList, getActivity());
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
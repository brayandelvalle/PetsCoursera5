package com.example.petscoursera.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petscoursera.R;
import com.example.petscoursera.adapters.PetPerfilAdapter;
import com.example.petscoursera.pojos.PetsArrays;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {

    //Object "petsArray"
    ArrayList<PetsArrays> petPerfilArrayList;

    //Recyclerview
    RecyclerView PetPerfil;

    // Required empty public constructor
    public PerfilFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        //This line allows to work the method onCreateOptionsMenu
        setHasOptionsMenu(true);

        //RecyclerView
        //----------------------------------------------------------------------------------------------//
        //Declaring the recyclerview object
        PetPerfil = v.findViewById(R.id.rvPetsPerfil);

        //Now we decide how we want to show the list of pets; linear, grid, staggered
        //We are going to use a LinearLayoutManager
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);
        //glm.setOrientation(LinearLayoutManager.VERTICAL);
        PetPerfil.setLayoutManager(glm);

        //Initializing the list of mythical pets
        InitializePetPerfilList();

        //Initializating the adapter
        InitializePetsAdapter();

        return v;
    }

    //This method will initialize to petsPerfilAdapter.
    //It creates an object of petsPerfilAdapter, gives it the list of pets petsPerfilArrayList so petsPerfilAdapter can use it.
    //The adapter calls the layout with the cardview pets_info
    //-------------------------------------------------------------------------------------------------------------------//
    private void InitializePetsAdapter() {
        PetPerfilAdapter adaptador = new PetPerfilAdapter(petPerfilArrayList);
        PetPerfil.setAdapter(adaptador); }


    //Method to initialize the list of pets profile.
    //-------------------------------------------------------------------------------------------------------------------//
    public void InitializePetPerfilList(){
        petPerfilArrayList = new ArrayList<>();
        petPerfilArrayList.add(new PetsArrays("Rasputia", 2, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 3, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 5, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 7, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 11, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 13, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 17, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 19, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 23, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 29, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 31, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 37, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 41, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 43, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 47, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 53, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 59, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 61, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 67, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 71, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 73, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 79, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 83, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 89, R.drawable.rasputia));
        petPerfilArrayList.add(new PetsArrays("Rasputia", 97, R.drawable.rasputia)); }
}
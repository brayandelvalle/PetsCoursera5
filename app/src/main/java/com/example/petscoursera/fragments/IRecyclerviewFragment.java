package com.example.petscoursera.fragments;

import com.example.petscoursera.adapters.PetsAdapter;
import com.example.petscoursera.pojos.PetsArrays;

import java.util.ArrayList;

public interface IRecyclerviewFragment {
    //method to generate the linear layout
    void generateLinearLayoutRV();

    //this method builds the adapter to be used by the initializer
    PetsAdapter buildAdapter(ArrayList<PetsArrays> petsArrays);

    //initialize the adapter
    void InitializeAdapterRV(PetsAdapter adapter);

}
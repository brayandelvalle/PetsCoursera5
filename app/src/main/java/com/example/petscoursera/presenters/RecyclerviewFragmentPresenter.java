package com.example.petscoursera.presenters;

import android.content.Context;

import com.example.petscoursera.db.PetsConstructor;
import com.example.petscoursera.fragments.IRecyclerviewFragment;
import com.example.petscoursera.pojos.PetsArrays;

import java.util.ArrayList;

public class RecyclerviewFragmentPresenter implements IRecyclerviewFragmentPresenter {
    //constructor
    private final IRecyclerviewFragment iRecyclerviewFragment;
    private final Context context;
    private PetsConstructor petsConstructor;
    private ArrayList<PetsArrays> petsInfoArrayList;

    public RecyclerviewFragmentPresenter(IRecyclerviewFragment iRecyclerviewFragment, Context context){
        this.iRecyclerviewFragment = iRecyclerviewFragment;
        this.context = context;
        obtainPetFromDataBase();

    }

    @Override
    public void obtainPetFromDataBase() {
        petsConstructor = new PetsConstructor(context);

        //this line is the one that separates the source of the data with its presentation
        petsInfoArrayList = petsConstructor.obtainData();

        //this line shows the pets on the recyclerview
        showPetOnRV();
    }

    @Override
    public void showPetOnRV() {
        //initializing the adapter by first building it:
        iRecyclerviewFragment.InitializeAdapterRV(iRecyclerviewFragment.buildAdapter(petsInfoArrayList));
        iRecyclerviewFragment.generateLinearLayoutRV();

    }
}

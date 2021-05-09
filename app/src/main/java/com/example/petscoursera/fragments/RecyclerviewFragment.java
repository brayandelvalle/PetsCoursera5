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
import com.example.petscoursera.adapters.PetsAdapter;
import com.example.petscoursera.db.PetsConstructor;
import com.example.petscoursera.pojos.PetsArrays;
import com.example.petscoursera.presenters.IRecyclerviewFragmentPresenter;
import com.example.petscoursera.presenters.RecyclerviewFragmentPresenter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class RecyclerviewFragment extends Fragment implements IRecyclerviewFragment {

    //Object "petsArray"
    ArrayList<PetsArrays> petsInfoArrayList;

    //Recyclerview
    RecyclerView PetsList;

    //Presenter
    IRecyclerviewFragmentPresenter presenter;

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
        PetsList = v.findViewById(R.id.rvPets);

        //Presenter
        //----------------------------------------------------------------------------------------------//
        presenter = new RecyclerviewFragmentPresenter(this, getContext());


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
            //Toast.makeText(getContext(), "Hollaaaaa", Toast.LENGTH_SHORT).show();
            //Sorting the pets by their likes
            //We first create a new petArrays ArrayList with the same information that has petsInfoArrayList but that will be sorted.
            ArrayList<PetsArrays> petsInfoArrayListSort;
            petsInfoArrayListSort = new ArrayList<>();


            //this line may bring back the data from the constructor
            PetsConstructor petsConstructor = new PetsConstructor(getContext());
            petsInfoArrayList = petsConstructor.obtainData();
            for(int pos=0; pos<5; ++pos ){petsInfoArrayListSort.add(new PetsArrays(
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

    //This methods will initialize to petsAdapter.
    //It creates an object of petsAdapter, gives it the list of pets petsInfoArrayList so petsAdapter can use it.
    //The adapter calls the layout with the cardview pets_info
    //-------------------------------------------------------------------------------------------------------------------//
    @Override
    public void generateLinearLayoutRV() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        PetsList.setLayoutManager(llm);
    }

    @Override
    public PetsAdapter buildAdapter(ArrayList<PetsArrays> petsArrays) {
        return new PetsAdapter(petsArrays, getActivity());
    }

    @Override
    public void InitializeAdapterRV(PetsAdapter adapter) {
        PetsList.setAdapter(adapter);
    }
}
package com.example.petscoursera.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.petscoursera.R;
import com.example.petscoursera.adapters.PageAdapter;
import com.example.petscoursera.fragments.PerfilFragment;
import com.example.petscoursera.fragments.RecyclerviewFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Views for the fragments
    private Toolbar toolbarFragment;
    private TabLayout tabsFragment;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Support for the action bar
        //----------------------------------------------------------------------------------------------//
        Toolbar MyToolbar1 = findViewById(R.id.MyActionBar1);
        setSupportActionBar(MyToolbar1);

        //Fragment
        //----------------------------------------------------------------------------------------------//
        toolbarFragment = (Toolbar)findViewById(R.id.toolbarFragment);
        tabsFragment = (TabLayout) findViewById(R.id.tabsFragment);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setUpViewPager();

        //Validating the toolbar
        if(toolbarFragment != null){setSupportActionBar(toolbarFragment);}

        /*
         */

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

    //Method to create the list of fragments
    //-------------------------------------------------------------------------------------------------------------------//
    private ArrayList<Fragment> addFragment(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerviewFragment());
        fragments.add(new PerfilFragment());
        return fragments; }

    //Method to setup the fragments to the viewpager
    //-------------------------------------------------------------------------------------------------------------------//
    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), addFragment(), 1 ));
        tabsFragment.setupWithViewPager(viewPager);
        tabsFragment.getTabAt(0).setIcon(R.drawable.ic_petshelter);
        tabsFragment.getTabAt(1).setIcon(R.drawable.ic_cattab);

    }

}
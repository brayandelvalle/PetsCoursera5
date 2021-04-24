package com.example.petscoursera.adapters;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

//The PageAdapter is the one that will work with the viewpager introducing it (in each tab) the fragments
public class PageAdapter extends FragmentPagerAdapter {

    //List of fragments
    ArrayList<Fragment> fragments;

    //Constructor
    public PageAdapter(FragmentManager fm,  ArrayList<Fragment> fragments, int behavior) {
        super(fm, behavior);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}

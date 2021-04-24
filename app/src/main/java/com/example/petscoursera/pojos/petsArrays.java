package com.example.petscoursera.pojos;

public class petsArrays implements Comparable<petsArrays>{
    private String petName;
    private int petLikes;
    private int petPhoto;

    //Constructor
    public petsArrays(String petName, int petLikes, int petPhoto) {
        this.petName = petName;
        this.petLikes = petLikes;
        this.petPhoto = petPhoto;
    }

    //Getters and setters
    public String getPetName() {return petName;}
    public void setPetName(String petName) {this.petName = petName;}

    public int getPetLikes() {return petLikes; }
    public void setPetLikes(int petLikes) {this.petLikes = petLikes; }

    public int getPetPhoto() {return petPhoto;}
    public void setPetPhoto(int petPhoto) {this.petPhoto = petPhoto;}

    //These lines will make possible to compare the pets by their likes.
    @Override
    public int compareTo(petsArrays o) {return o.getPetLikes()-this.petLikes;}
}

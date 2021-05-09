package com.example.petscoursera.pojos;

public class PetsArrays implements Comparable<PetsArrays>{

    private int idPet;
    private String petName;
    private int petLikes;
    private int petPhoto;

    //Constructor
    public PetsArrays(String petName, int petLikes, int petPhoto) {
        this.petName = petName;
        this.petLikes = petLikes;
        this.petPhoto = petPhoto;
    }

    //this constructor in needed for the SQLite data base
    public PetsArrays() { }

    //Getters and setters
    public String getPetName() {return petName;}
    public void setPetName(String petName) {this.petName = petName;}

    public int getPetLikes() {return petLikes; }
    public void setPetLikes(int petLikes) {this.petLikes = petLikes; }

    public int getPetPhoto() {return petPhoto;}
    public void setPetPhoto(int petPhoto) {this.petPhoto = petPhoto;}

    public int getIdPet() {return idPet;}
    public void setIdPet(int idPet) {this.idPet = idPet;}

    //These lines will make possible to compare the pets by their likes.
    @Override
    public int compareTo(PetsArrays o) {return o.getPetLikes()-this.petLikes;}


}

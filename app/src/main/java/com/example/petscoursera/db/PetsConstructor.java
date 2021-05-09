package com.example.petscoursera.db;

import android.content.ContentValues;
import android.content.Context;
import android.provider.ContactsContract;

import com.example.petscoursera.R;
import com.example.petscoursera.pojos.PetsArrays;

import java.util.ArrayList;

public class PetsConstructor {
    private static final int LIKE = 1;
    //Constructor
    private final Context context;
    public PetsConstructor(Context context){
        this.context=context;
    }

    public ArrayList<PetsArrays> obtainData(){
        //ArrayList with the name, likes and photo of each pet.
        /*
        ArrayList<PetsArrays> petsInfoArrayList = new ArrayList<>();
        petsInfoArrayList.add(new PetsArrays("Rasputia", 0, R.drawable.rasputia));
        petsInfoArrayList.add(new PetsArrays("Kraken",   0, R.drawable.kraken));
        petsInfoArrayList.add(new PetsArrays("Cerbero",  0, R.drawable.cerbero));
        petsInfoArrayList.add(new PetsArrays("Unicornio",0, R.drawable.unicornio));
        petsInfoArrayList.add(new PetsArrays("Grifo",    0, R.drawable.grifo));
        petsInfoArrayList.add(new PetsArrays("Hidra",    0, R.drawable.hidra));
        return petsInfoArrayList;
        */

        DataBase db = new DataBase(context);
        insertPets(db);

        return db.obtainAllPets();
    }

    //Method to insert some pets to DataBase db object
    public void insertPets(DataBase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantsDataBase.TABLE_PET_NAME, "Rasputia");
        contentValues.put(ConstantsDataBase.TABLE_PET_PHOTO, R.drawable.rasputia );
        db.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantsDataBase.TABLE_PET_NAME, "Kraken");
        contentValues.put(ConstantsDataBase.TABLE_PET_PHOTO, R.drawable.kraken );
        db.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantsDataBase.TABLE_PET_NAME, "Cerbero");
        contentValues.put(ConstantsDataBase.TABLE_PET_PHOTO, R.drawable.cerbero );
        db.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantsDataBase.TABLE_PET_NAME, "Unicornio");
        contentValues.put(ConstantsDataBase.TABLE_PET_PHOTO, R.drawable.unicornio );
        db.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantsDataBase.TABLE_PET_NAME, "Grifo");
        contentValues.put(ConstantsDataBase.TABLE_PET_PHOTO, R.drawable.grifo );
        db.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantsDataBase.TABLE_PET_NAME, "Hidra");
        contentValues.put(ConstantsDataBase.TABLE_PET_PHOTO, R.drawable.hidra );
        db.insertPet(contentValues);
    }

    //We insert the like to the pet
    public void giveLikeToPet(PetsArrays pet){
        DataBase db = new DataBase(context);
        ContentValues contentValues = new ContentValues();

        //Filling the object content values with the likes
        contentValues.put(ConstantsDataBase.TABLE_PETS_LIKES_ID_PET, pet.getIdPet());
        contentValues.put(ConstantsDataBase.TABLE_PETS_LIKES_NUMBER, LIKE);
        db.insertLike(contentValues);
    }

    public int obtainLikes(PetsArrays pet){
        //Consulting the data base
        DataBase db = new DataBase(context);
        return db.sumLikes(pet);
    }

}

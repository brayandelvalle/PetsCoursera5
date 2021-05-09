package com.example.petscoursera.db;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.petscoursera.db.ConstantsDataBase;
import com.example.petscoursera.pojos.PetsArrays;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    private Context context;
    //the constructor
    public DataBase(@Nullable Context context) {
        super(context, ConstantsDataBase.DATABASE_NAME, null, ConstantsDataBase.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    //In this method we create the structure of the data bases
    public void onCreate(SQLiteDatabase db) {
        //Table with the pets information
        String queryBuildPetsTable = "CREATE TABLE " + ConstantsDataBase.TABLE_PET + "(" +
                ConstantsDataBase.TABLE_PET_ID       + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantsDataBase.TABLE_PET_NAME     + " TEXT," +
                ConstantsDataBase.TABLE_PET_PHOTO    + " INTEGER "+
                ")" ;

        //Table with the information of the likes
        //ConstantsDataBase.TABLE_PETS_LIKES_ID_PET is the foreign key
        String queryBuildPetsTableLikes = "CREATE TABLE "   + ConstantsDataBase.TABLE_PET_LIKES + "(" +
                ConstantsDataBase.TABLE_PETS_LIKES_ID       + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantsDataBase.TABLE_PETS_LIKES_ID_PET   + " INTEGER, " +
                ConstantsDataBase.TABLE_PETS_LIKES_NUMBER   + " INTEGER, " +
                "FOREIGN KEY (" + ConstantsDataBase.TABLE_PETS_LIKES_ID_PET + ") " +
                "REFERENCES " + ConstantsDataBase.TABLE_PET + "(" + ConstantsDataBase.TABLE_PET_ID + ")" +
                ")";

        //Creating the queries
        db.execSQL(queryBuildPetsTable);
        db.execSQL(queryBuildPetsTableLikes);
    }

    @Override
    //This method restructure the data base if it is needed. It only affects its structure
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantsDataBase.TABLE_PET);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantsDataBase.TABLE_PET_LIKES);
        onCreate(db);
    }

    //This method will consult the data base
    public ArrayList<PetsArrays> obtainAllPets(){
        ArrayList<PetsArrays> petsArrays = new ArrayList<>();

        //Consulting/executing the data base
        String query = "SELECT * FROM " + ConstantsDataBase.TABLE_PET;

        //Here we open the database in writerable mode
        SQLiteDatabase db = this.getWritableDatabase();

        //To move in the registers
        Cursor registers = db.rawQuery(query, null);

        //filling the pets array
        while (registers.moveToNext()){
            PetsArrays currentPet = new PetsArrays();
            currentPet.setIdPet(registers.getInt(0));
            currentPet.setPetName(registers.getString(1));
            currentPet.setPetPhoto(registers.getInt(2));

            //This query will obtain/recover the number of likes to be impresed in the textView counter
            String queryLikes = "SELECT COUNT (" + ConstantsDataBase.TABLE_PETS_LIKES_NUMBER + ") as likes " +
                                " FROM " + ConstantsDataBase.TABLE_PET_LIKES +
                                " WHERE " + ConstantsDataBase.TABLE_PETS_LIKES_ID_PET + "=" + currentPet.getIdPet();

            Cursor registerLikes = db.rawQuery(queryLikes, null);
            if(registerLikes.moveToNext()){currentPet.setPetLikes(registerLikes.getInt(0));}else {currentPet.setPetLikes(0);}

            petsArrays.add(currentPet);
        }

        db.close();
        return petsArrays;
    }

    //Method to insert some pets to DataBase db object
    //The object ContentResolver
    public void insertPet(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantsDataBase.TABLE_PET, null, contentValues);
        db.close();
    }

    //This method will insert the likes to the pet
    public void insertLike(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantsDataBase.TABLE_PET_LIKES, null, contentValues);
        db.close();
    }

    //This method will return the sum of the likes
    public int sumLikes(PetsArrays pet){
        int likes = 0;
        String query = " SELECT COUNT(" + ConstantsDataBase.TABLE_PETS_LIKES_NUMBER+ ")" +
                        " FROM " + ConstantsDataBase.TABLE_PET_LIKES +
                        " WHERE " + ConstantsDataBase.TABLE_PETS_LIKES_ID_PET + "=" + pet.getIdPet()
                ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor register = db.rawQuery(query, null);

        if(register.moveToNext()){ likes = register.getInt(0);}

        db.close();

        return likes;
    }

}
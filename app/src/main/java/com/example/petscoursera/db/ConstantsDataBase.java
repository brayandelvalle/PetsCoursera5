package com.example.petscoursera.db;

public final class ConstantsDataBase {

    public static final String DATABASE_NAME = "pets";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_PET = "pet";
    public static final String TABLE_PET_ID = "id";
    public static final String TABLE_PET_NAME = "name";
    public static final String TABLE_PET_PHOTO = "photo";

    public static final String TABLE_PET_LIKES = "pet_likes";
    public static final String TABLE_PETS_LIKES_ID = "id";
    public static final String TABLE_PETS_LIKES_ID_PET = "id_pet"; //the foreign key
    public static final String TABLE_PETS_LIKES_NUMBER = "pet_likes"; //the foreign key
}

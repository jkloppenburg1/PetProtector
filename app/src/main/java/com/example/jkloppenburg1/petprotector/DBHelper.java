package com.example.jkloppenburg1.petprotector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;

class DBHelper extends SQLiteOpenHelper {

    //TASK 1: DEFINE THE DATABASE VERSION, NAME AND TABLE NAME
    static final String DATABASE_NAME = "PetProtector";
    private static final String DATABASE_TABLE = "Pets";
    private static final int DATABASE_VERSION = 1;


    //TASK 2: DEFINE THE FIELDS (COLUMN NAMES) FOR THE TABLE
    private static final String KEY_FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_DETAILS = "details";
    private static final String FIELD_PHONE = "phone";
    private static final String FIELD_IMAGE_URI = "image_uri";


    public DBHelper(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase database){
        // TODO:  Define the SQL statement to create a new table with the fields above.
        String table = "CREATE TABLE" + DATABASE_TABLE + "("
                + KEY_FIELD_ID + "INTEGER AUTO INCREMENT, "
                + FIELD_NAME + "TEXT, "
                + FIELD_DETAILS + "TEXT, "
                + FIELD_PHONE + "INTEGER, "
                + FIELD_IMAGE_URI + "TEXT" + ")";
        // TODO:  Primary key should auto increment
        // TODO:  Exceute the SQL statement
        database.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          int oldVersion,
                          int newVersion) {
        // TODO:  Execute the SQL statment to drop the table
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        // TODO:  Recreate the database
        onCreate(database);
    }

    //********** DATABASE OPERATIONS:  ADD, GETALL, EDIT, DELETE

    public void addPet(Pet pet) {

        // TODO:  Write the code to add a new game to the database
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(FIELD_NAME, pet.getName());
        values.put(FIELD_DETAILS, pet.getDetails());
        values.put(FIELD_PHONE, pet.getPhone());
        values.put(FIELD_IMAGE_URI, pet.getImageUri().toString());

        db.insert(DATABASE_TABLE, null, values);


        db.close();
    }

    public ArrayList<Pet> getAllPets() {
        ArrayList<Pet> petList = new ArrayList<>();

        // TODO:  Write the code to get all games from the database and return in ArrayList
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor results = db.query(DATABASE_TABLE, null, null, null, null, null, null);

        if (results.moveToFirst())
        {
            do {
                {
                    int id = results.getInt(0);
                    String name = results.getString(1);
                    String details = results.getString(2);
                    int phone = results.getInt(3);
                    String imageURI = results.getString(4);
                    petList.add(new Pet(name, details, phone, Uri.parse(imageURI)));
                }
            } while (results.moveToNext());
        }

        db.close();
        return petList;
    }

    public void deleteAllGames()
    {
        // TODO:  Write the code to delete all games from the database
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(DATABASE_TABLE, null, null);

        db.close();
    }

    public void updateGame(Pet pet){

        // TODO:  Write the code to update a game in the database.
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(FIELD_NAME, pet.getName());
        values.put(FIELD_DETAILS, pet.getDetails());
        values.put(FIELD_PHONE, pet.getPhone());
        values.put(FIELD_IMAGE_URI, pet.getImageUri().toString());
        db.update(DATABASE_TABLE,
                values,
                KEY_FIELD_ID + "=?",
                new String[] {String.valueOf(pet.getId())});

        db.close();
    }

    public Pet getPet(int id) {

        // TODO:  Write the code to get a specific game from the database
        // TODO:  Replace the return null statement below with the game from the database.
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();

        Cursor results = db.query(
                DATABASE_TABLE,
                new String[] {KEY_FIELD_ID, FIELD_DETAILS, FIELD_PHONE, FIELD_IMAGE_URI},
                KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null
        );

        if (results != null)
            results.moveToFirst();

        Pet pet = new Pet(
                results.getInt(0),
                results.getString(1),
                results.getString(2),
                results.getInt(3),
                Uri.parse(results.getString(4)));


        db.close();
        return pet;
    }





}

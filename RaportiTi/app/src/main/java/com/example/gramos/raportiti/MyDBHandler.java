package com.example.gramos.raportiti;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("ALL")
class MyDBHandler extends SQLiteOpenHelper {

    //All static variables

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Database name
    public static final String DATABASE_NAME = "gps_coordinates.db";

    // Database table name
    public static final String TABLE_COORDINATES = "coordinates";

    // database(gps_coordinates.db) table's columns names
    public static final String COLUMN_ID = "sid";
    public static final String COLUMN_LAT = "lat";
    public static final String COLUMN_LNG = "lng";



    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    //  Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_COORDINATES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_LAT + " FLOAT," +
                COLUMN_LNG + " FLOAT" +

                " ); ";
        db.execSQL(sql);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // not required until second version :)

    }

    //Adding new Coordinates
    public void insertRecord(double lat, double lng) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LAT, lat);
        values.put(COLUMN_LNG, lng);

        // Inserting Row
        db.insert(TABLE_COORDINATES, null, values);
        // Closing db connections
        db.close();
    }

    // Getting single Coordinates
    public DatabaseO get_DatabaseOb(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_COORDINATES, new String[]{COLUMN_ID, COLUMN_LAT,
                        COLUMN_LNG}, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        DatabaseO databaseO = new DatabaseO(Integer.parseInt(cursor != null ? cursor.getString(0) : null),
                Double.parseDouble(cursor != null ? cursor.getString(1) : null), Double.parseDouble(cursor.getString(2)));

        db.close();

        return databaseO;

    }

    // Getting all Coordinates
    public List<DatabaseO> getAllDataObCoo() {
        List<DatabaseO> coordinates = new ArrayList<>();
        //Select all query
        String selectQuery = "SELECT * FROM " + TABLE_COORDINATES;
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DatabaseO databaseO = new DatabaseO();
                databaseO.setID(Integer.parseInt(cursor.getString(0)));
                databaseO.setLat(Double.parseDouble(cursor.getString(1)));
                databaseO.setLng(Double.parseDouble(cursor.getString(2)));

                coordinates.add(databaseO);

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return coordinates;
    }

}







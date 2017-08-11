package com.example.admin.testweek2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Admin on 8/11/2017.
 */

public class DataBase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CarList";

    public static final String TABLE_NAME ="Car";
    public static final String CAR_ID ="ID";
    public static final String CAR_MODEL ="Model";
    public static final String CAR_TYPE ="Types";
    public static final String CAR_YEAR ="Year";

    private static final String TAG = "DataBase";
    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
                CAR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CAR_MODEL + " TEXT, " +
                CAR_TYPE + " TEXT, " +
                CAR_YEAR + " INTEGER, " +
                ")";
        Log.d(TAG, "onCreate: "+CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public void saveNewCars(Cars cars){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CAR_MODEL, cars.getModel());
        contentValues.put(CAR_TYPE, cars.getTypes());
        contentValues.put(CAR_YEAR, cars.getYear());

        database.insert(TABLE_NAME,null,contentValues);
    }

    public ArrayList<Cars> getCars(){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "";
        Cursor cursor;
//        if(id.equals("-1")){
//            query = "SELECT * FROM " + TABLE_NAME;
//            Log.d(TAG, "getACars: "+query + " " + id);
//            cursor = database.rawQuery(query, null);
//            //Log.d(TAG, "getArtists: " + cursor.getInt(0) +cursor.getString(1)+cursor.getString(2)+cursor.getString(3)+cursor.getBlob(4));
//        }
//        else {
            query = "SELECT * FROM " + TABLE_NAME + " WHERE " + CAR_ID + " = ?";
//            Log.d(TAG, "getACars: "+query + " " + id);
//            String parameters[] = {id};
            cursor = database.rawQuery(query, null);
        //}
        //Cursor cursor = database.rawQuery(query, null);
        ArrayList<Cars> car = new ArrayList();
        if(cursor.moveToFirst()){
            do{
                Log.d(TAG, "getArtists: Name:" + cursor.getString(1));
                car.add(new Cars(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3)));
            }while(cursor.moveToNext());
        }
        else{
            Log.d(TAG, "getArtists: empty");
        }
        Log.d(TAG, "getArtists: " + car);
        return car;

    }
}
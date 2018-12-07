package com.example.panaj.personalrestaurantguide.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RestaurantDbHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION=1;
    public static final String DB_NAME="restaurants.db";

    public RestaurantDbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    SQLiteDatabase db = this.getWritableDatabase();
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RestaurantContract.SQL_CREATE_RESTAURANTS);
        Log.d("DB-TEST","DATABASE CREATED!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Don't use this unless you use DB as a cache
        db.execSQL(RestaurantContract.SQL_DROP_RESTAURANTS);
        onCreate(db);
        Log.d("DB-TEST","DATABASE UPGRADED!");
    }

    public static long addPresenter(SQLiteDatabase db, Restaurant restaurant) {

        ContentValues values = new ContentValues();
        values.put(RestaurantContract.RestaurantEntry.COL_NAME_NAME, restaurant.getName());
        values.put(RestaurantContract.RestaurantEntry.COL_NAME_ADDRESS, restaurant.getAddress());
        values.put(RestaurantContract.RestaurantEntry.COL_NAME_PHONE, restaurant.getPhoneNumber());
        values.put(RestaurantContract.RestaurantEntry.COL_NAME_TAG, restaurant.getTag());
        values.put(RestaurantContract.RestaurantEntry.COL_NAME_DESCRIPTION,restaurant.getDescription());
        values.put(RestaurantContract.RestaurantEntry.COL_NAME_RATE, restaurant.getRate());

        return db.insert(RestaurantContract.RestaurantEntry.TABLE_NAME, null, values);
    }

    public Restaurant getRestaurantData(SQLiteDatabase db){
        String [] projection ={
                RestaurantContract.RestaurantEntry._ID,
                RestaurantContract.RestaurantEntry.COL_NAME_NAME,
                RestaurantContract.RestaurantEntry.COL_NAME_ADDRESS,
                RestaurantContract.RestaurantEntry.COL_NAME_PHONE,
                RestaurantContract.RestaurantEntry.COL_NAME_TAG,
                RestaurantContract.RestaurantEntry.COL_NAME_DESCRIPTION,
                RestaurantContract.RestaurantEntry.COL_NAME_RATE
        };

        Cursor cursor = db.query(
                RestaurantContract.RestaurantEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

    if(cursor.moveToNext()){
        String name = cursor.getString(cursor.getColumnIndexOrThrow(RestaurantContract.RestaurantEntry.COL_NAME_NAME));
        String address = cursor.getString(cursor.getColumnIndexOrThrow(RestaurantContract.RestaurantEntry.COL_NAME_ADDRESS));
        String phone = cursor.getString(cursor.getColumnIndexOrThrow(RestaurantContract.RestaurantEntry.COL_NAME_PHONE));
        String tag = cursor.getString(cursor.getColumnIndexOrThrow(RestaurantContract.RestaurantEntry.COL_NAME_TAG));
        String des = cursor.getString(cursor.getColumnIndexOrThrow(RestaurantContract.RestaurantEntry.COL_NAME_DESCRIPTION));
        int rate = cursor.getInt(cursor.getColumnIndexOrThrow(RestaurantContract.RestaurantEntry.COL_NAME_RATE));
        Restaurant restaurant = new Restaurant(name, address, phone, tag, des, rate);
        return restaurant;
    }
        cursor.close();
        return null;
}


    public Cursor getListContents (){
        Cursor data = db.rawQuery("SELECT * FROM "+ RestaurantContract.RestaurantEntry.TABLE_NAME,null);
        return data;
    }
    public Cursor getDetailInfo (int id){

        Cursor data = db.rawQuery("SELECT * FROM "+ RestaurantContract.RestaurantEntry.TABLE_NAME+" where "+
                RestaurantContract.RestaurantEntry._ID+" = "+id,null);
        return data;
    }

    public void delete(int id){
        db.execSQL("DELETE FROM "+RestaurantContract.RestaurantEntry.TABLE_NAME+" WHERE "+RestaurantContract.RestaurantEntry._ID+" ="+id);
    }

    public void edit(int id, String name, String address,String phone, String tag,String des, int rate){
        ContentValues cv = new ContentValues();
        cv.put(RestaurantContract.RestaurantEntry.COL_NAME_NAME,name);
        cv.put(RestaurantContract.RestaurantEntry.COL_NAME_ADDRESS,address);
        cv.put(RestaurantContract.RestaurantEntry.COL_NAME_PHONE,phone);
        cv.put(RestaurantContract.RestaurantEntry.COL_NAME_TAG,tag);
        cv.put(RestaurantContract.RestaurantEntry.COL_NAME_DESCRIPTION,des);
        cv.put(RestaurantContract.RestaurantEntry.COL_NAME_RATE,rate);
        db.update(RestaurantContract.RestaurantEntry.TABLE_NAME,cv,RestaurantContract.RestaurantEntry._ID+" ="+id,null);
    }
}



package com.example.panaj.personalrestaurantguide.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RestaurantDbHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION=1;
    public static final String DB_NAME="restaurants.db";

    public RestaurantDbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

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
}

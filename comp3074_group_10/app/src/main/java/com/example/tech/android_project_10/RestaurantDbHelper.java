package com.example.tech.android_project_10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RestaurantDbHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "resturant.db";

    public RestaurantDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RestaurantContract.SQL_CREATE_POSTS);
        Log.d("DB-TEST", "Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(RestaurantContract.SQL_DROP_POSTS);
        onCreate(db);
        Log.d("DB-TEST", "Database upgraded");
    }

    public long addPost(SQLiteDatabase db, Restaurant post){
        ContentValues values = new ContentValues();
        values.put(RestaurantContract.PostEntry.COL_NAME_NAME, post.getName());
        values.put(RestaurantContract.PostEntry.COL_NAME_ADDRESS, post.getAddress());
        values.put(RestaurantContract.PostEntry.COL_NAME_PHONE, post.getPhone());
        values.put(RestaurantContract.PostEntry.COL_NAME_DESCRIPTION, post.getDescription());
        values.put(RestaurantContract.PostEntry.COL_NAME_TAGS, post.getTags());
        values.put(RestaurantContract.PostEntry.COL_NAME_RATING, post.getRating());

        return db.insert(RestaurantContract.PostEntry.TABLE_NAME, null, values);
    }

    public int updatePost(SQLiteDatabase db, Restaurant post, int id){
        ContentValues updateValues = new ContentValues();
        updateValues.put(RestaurantContract.PostEntry.COL_NAME_NAME, post.getName());
        updateValues.put(RestaurantContract.PostEntry.COL_NAME_ADDRESS, post.getAddress());
        updateValues.put(RestaurantContract.PostEntry.COL_NAME_PHONE, post.getPhone());
        updateValues.put(RestaurantContract.PostEntry.COL_NAME_DESCRIPTION, post.getDescription());
        updateValues.put(RestaurantContract.PostEntry.COL_NAME_TAGS, post.getTags());
        updateValues.put(RestaurantContract.PostEntry.COL_NAME_RATING, post.getRating());

        return db.update(RestaurantContract.PostEntry.TABLE_NAME, updateValues, RestaurantContract.PostEntry._ID +"="+ id, null);
    }

    public int deletePost(SQLiteDatabase db, int id){
        return db.delete(RestaurantContract.PostEntry.TABLE_NAME, RestaurantContract.PostEntry._ID +"="+ id, null);
    }

    public Restaurant getPost(SQLiteDatabase db, int postId) {
        String[] projection = {
                RestaurantContract.PostEntry._ID,
                RestaurantContract.PostEntry.COL_NAME_NAME,
                RestaurantContract.PostEntry.COL_NAME_ADDRESS,
                RestaurantContract.PostEntry.COL_NAME_PHONE,
                RestaurantContract.PostEntry.COL_NAME_DESCRIPTION,
                RestaurantContract.PostEntry.COL_NAME_TAGS,
                RestaurantContract.PostEntry.COL_NAME_RATING
        };

        String selection = RestaurantContract.PostEntry._ID+"= ? ";
        String[] selectionArgs = {Integer.toString(postId)};
        Cursor cursor = db.query(
                RestaurantContract.PostEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(RestaurantContract.PostEntry._ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(RestaurantContract.PostEntry.COL_NAME_NAME));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(RestaurantContract.PostEntry.COL_NAME_ADDRESS));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(RestaurantContract.PostEntry.COL_NAME_PHONE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(RestaurantContract.PostEntry.COL_NAME_DESCRIPTION));
            String tags = cursor.getString(cursor.getColumnIndexOrThrow(RestaurantContract.PostEntry.COL_NAME_TAGS));
            float rating = cursor.getFloat(cursor.getColumnIndexOrThrow(RestaurantContract.PostEntry.COL_NAME_RATING));

            Restaurant post = new Restaurant(id, name, address, phone, description, tags, rating);
            return post;
        }

        return null;
    }

    public Restaurant getPost(SQLiteDatabase db, String qName) {
        String[] projection = {
                RestaurantContract.PostEntry._ID,
                RestaurantContract.PostEntry.COL_NAME_NAME,
                RestaurantContract.PostEntry.COL_NAME_ADDRESS,
                RestaurantContract.PostEntry.COL_NAME_PHONE,
                RestaurantContract.PostEntry.COL_NAME_DESCRIPTION,
                RestaurantContract.PostEntry.COL_NAME_TAGS,
                RestaurantContract.PostEntry.COL_NAME_RATING
        };

        String selection = RestaurantContract.PostEntry.COL_NAME_NAME+"= ? ";
        String[] selectionArgs = {qName};
        Cursor cursor = db.query(
                RestaurantContract.PostEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(RestaurantContract.PostEntry._ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(RestaurantContract.PostEntry.COL_NAME_NAME));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(RestaurantContract.PostEntry.COL_NAME_ADDRESS));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(RestaurantContract.PostEntry.COL_NAME_PHONE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(RestaurantContract.PostEntry.COL_NAME_DESCRIPTION));
            String tags = cursor.getString(cursor.getColumnIndexOrThrow(RestaurantContract.PostEntry.COL_NAME_TAGS));
            float rating = cursor.getFloat(cursor.getColumnIndexOrThrow(RestaurantContract.PostEntry.COL_NAME_RATING));

            Restaurant post = new Restaurant(id, name, address, phone, description, tags, rating);
            return post;
        }

        return null;
    }
}

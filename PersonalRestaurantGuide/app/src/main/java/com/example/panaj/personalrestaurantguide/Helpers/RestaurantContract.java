package com.example.panaj.personalrestaurantguide.Helpers;

import android.provider.BaseColumns;

public final class RestaurantContract {

    public RestaurantContract(){}

    public static class RestaurantEntry implements BaseColumns {

     public static final String TABLE_NAME = "RESTAURANTS";
     public static final String COL_NAME_NAME = "NAME";
     public static final String COL_NAME_ADDRESS = "ADDRESS";
     public static final String COL_NAME_PHONE = "PHONE";
     public static final String COL_NAME_TAG = "TAG";
     public static final String COL_NAME_DESCRIPTION = "DESCRIPTION";
     public static final String COL_NAME_RATE = "RATE";
     public static final String COL_TYPE_NAME = "TEXT";
     public static final String COL_TYPE_ADDRESS = "TEXT";
     public static final String COL_TYPE_PHONE = "TEXT";
     public static final String COL_TYPE_TAG = "TEXT";
     public static final String COL_TYPE_DESCRIPTION = "TEXT";
     public static final String COL_TYPE_RATE = "TEXT";
 }


    public static final String SQL_CREATE_RESTAURANTS =
            "CREATE TABLE "+RestaurantEntry.TABLE_NAME+" ("+
                    RestaurantEntry._ID+" INTEGER PRIMARY KEY, "+
                    RestaurantEntry.COL_NAME_NAME+" "+RestaurantEntry.COL_TYPE_NAME+", "+
                    RestaurantEntry.COL_NAME_ADDRESS+" "+RestaurantEntry.COL_TYPE_ADDRESS+", "+
                    RestaurantEntry.COL_NAME_PHONE+" "+RestaurantEntry.COL_TYPE_PHONE+", "+
                    RestaurantEntry.COL_NAME_TAG+" "+RestaurantEntry.COL_TYPE_TAG+", "+
                    RestaurantEntry.COL_NAME_DESCRIPTION+" "+RestaurantEntry.COL_TYPE_DESCRIPTION+", "+
                    RestaurantEntry.COL_NAME_RATE+" "+RestaurantEntry.COL_TYPE_RATE+")";

    public static final String SQL_DROP_RESTAURANTS =
            "DROP TABLE IF EXISTS "+RestaurantEntry.TABLE_NAME;
}

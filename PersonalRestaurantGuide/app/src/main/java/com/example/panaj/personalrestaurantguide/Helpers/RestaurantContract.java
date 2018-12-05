package com.example.panaj.personalrestaurantguide.Helpers;

public final class RestaurantContract {

    public RestaurantContract(){}

    public static final String TABLE_NAME = "RESTAURANTS";
    public static final String COL_NAME_NAME = "NAME";
    public static final String COL_NAME_ADDRESS= "ADDRESS";
    public static final String COL_NAME_PHONE = "PHONE";
    public static final String COL_NAME_TAG= "TAG";
    public static final String COL_NAME_RATE = "RATE";

    public static final String COL_TYPE_NAME = "TEXT";
    public static final String COL_TYPE_ADDRESS = "TEXT";
    public static final String COL_TYPE_PHONE = "TEXT";
    public static final String COL_TYPE_TAG = "TEXT";
    public static final String COL_TYPE_RATE = "TEXT";

    public static final String SQL_CREATE_RESTAURANTS =
            "CREATE TABLE "+TABLE_NAME+" ("+
                    "ID"+" INTEGER PRIMARY KEY, "+
                    COL_NAME_NAME+" "+COL_TYPE_NAME+", "+
                    COL_NAME_ADDRESS+" "+COL_TYPE_ADDRESS+", "+
                    COL_NAME_PHONE+" "+COL_TYPE_PHONE+", "+
                    COL_NAME_TAG+" "+COL_TYPE_TAG+", "+
                    COL_NAME_RATE+" "+COL_TYPE_RATE+")";

    public static final String SQL_DROP_RESTAURANTS =
            "DROP TABLE IF EXISTS "+TABLE_NAME;
}

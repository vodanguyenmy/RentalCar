package com.example.rentalcar.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class User extends SQLiteOpenHelper {

    //declare Const variable reference to Database
    private static final String DATABASE_NAME = "rental_car.db";
    private static final String TABLE = "user";
    private static final String COL_ID = "u_id";
    private static final String COL_PHONE = "u_phone";
    private static final String COL_PASSWORD = "u_password";
    private static final String COL_NAME = "u_name";
    private static final String COL_EMAIL = "u_email";
    private static final String COL_ADDRESS = "u_address";
    private static final String COL_ROLE = "u_role";
    private static final String COL_STATUS = "u_status";

    public User(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateUser = " CREATE TABLE IF NOT EXISTS user(u_id INTEGER PRIMARY KEY, u_phone TEXT, " +
                " u_password TEXT, u_name TEXT, u_email TEXT, u_address TEXT, u_identity TEXT, " +
                " u_status INTEGER); ";
        db.execSQL(queryCreateUser);

        String queryCreateVehicle = " CREATE TABLE IF NOT EXISTS vehicle(v_id INTEGER PRIMARY KEY, v_licensePlate TEXT, " +
                " v_seat INTEGER, v_costPerDate FLOAT, v_costPerKm FLOAT, v_status INTEGER, version INTEGER, " +
                " branch INTEGER); ";
        db.execSQL(queryCreateVehicle);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //region Generate table and data into database

    //endregion
}

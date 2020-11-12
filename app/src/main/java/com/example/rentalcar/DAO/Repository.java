package com.example.rentalcar.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.rentalcar.object.User;
import com.example.rentalcar.object.branch;
import com.example.rentalcar.object.vehicle;
import com.example.rentalcar.object.version;

import java.util.ArrayList;
import java.util.List;

public class Repository extends SQLiteOpenHelper {

    //declare Const variable reference to Database
    private static final String DATABASE_NAME = "rental_car.db";

    private Context context;
    public Repository(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        InitializeTable(db);
    }

    //region Generate table and data into database

    protected void InitializeTable(SQLiteDatabase db) {
        //Create table User
        String queryCreateUser = " CREATE TABLE IF NOT EXISTS user(u_id INTEGER PRIMARY KEY AUTOINCREMENT, u_phone TEXT, " +
                " u_name TEXT, u_email TEXT, u_address TEXT, u_identity TEXT, " +
                " u_status INTEGER); ";
        db.execSQL(queryCreateUser);

        //Create table vehicle
        String queryCreateVehicle = " CREATE TABLE IF NOT EXISTS vehicle(v_id INTEGER PRIMARY KEY AUTOINCREMENT, v_name TEXT, v_licensePlate TEXT, " +
                " v_seat INTEGER, v_costPerDate FLOAT, v_costPerKm FLOAT, v_image TEXT, v_status INTEGER, version INTEGER, " +
                " branch INTEGER, color INTEGER, fuel INTEGER, gear INTEGER); ";
        db.execSQL(queryCreateVehicle);

        //Create table branch
        String queryCreateBranch = " CREATE TABLE IF NOT EXISTS branch(br_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " br_name TEXT, br_logo TEXT); ";
        db.execSQL(queryCreateBranch);

        //Create table version
        String queryCreateVersion = " CREATE TABLE IF NOT EXISTS version(vs_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " version TEXT); ";
        db.execSQL(queryCreateVersion);

        //Create table color
        String queryCreateColor = " CREATE TABLE IF NOT EXISTS color(col_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " color TEXT); ";
        db.execSQL(queryCreateColor);

        //Create table fuel
        String queryCreateFuel = " CREATE TABLE IF NOT EXISTS fuel(f_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " fuel TEXT); ";
        db.execSQL(queryCreateFuel);

        //Create table gear
        String queryCreateGear = " CREATE TABLE IF NOT EXISTS gear(g_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " gear TEXT); ";
        db.execSQL(queryCreateGear);

        //Create table invoice
        String queryCreateInvoice = " CREATE TABLE IF NOT EXISTS invoice(i_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " i_dateStart TEXT, i_dateEnd TEXT, i_status INTEGER, u_id INTEGER, v_id INTEGER); ";
        db.execSQL(queryCreateInvoice);
    }

    //endregion

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Do nothing
    }

    //region Area code for Minh Tan

    /**
     * The method to get all student from database and add to list
     * @return List data from table User in database
     */
    public List<User> getAllStudent() {
        //khoi tao list student
        List<User> list = new ArrayList<>();
        String sqlQuery = " SELECT * FROM user";
        Cursor result = null;

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            result = db.rawQuery(sqlQuery, null);
            result.moveToFirst();

            if (result.moveToFirst()){
                do {
                    //add data to object
                    User user = new User();
                    user.setId(result.getInt(0));
                    user.setPhone(result.getString(1));
                    user.setName(result.getString(2));
                    user.setEmail(result.getString(3));
                    user.setAddress(result.getString(4));
                    user.setIdentity(result.getString(5));
                    user.setStatus(result.getInt(6));
                    list.add(user);
                } while (result.moveToNext());
            }
        } finally {
            result.close();
        }

        return list;
    }

    /**
     * The method to insert new user into database
     * @param user
     */
    public void insert(User user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //put values to context value
        values.put("u_id", user.getId());
        values.put("u_phone", user.getPhone());
        values.put("u_name", user.getName());
        values.put("u_email", user.getEmail());
        values.put("u_address", user.getAddress());
        values.put("u_identity", user.getIdentity());
        values.put("u_status", user.getStatus());


        //insert vao bang
        db.insert("user", null, values);

        db.close();
    }

    /**
     * The method to update user in database
     * @param user
     */
    public void update(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //put values to context value
        values.put("u_id", user.getId());
        values.put("u_phone", user.getPhone());
        values.put("u_name", user.getName());
        values.put("u_email", user.getEmail());
        values.put("u_address", user.getAddress());
        values.put("u_identity", user.getIdentity());
        values.put("u_status", user.getStatus());

        db.update("user", values, "id = ?", new String[] { String.valueOf(user.getId()) });
        db.close();
    }

    /**
     * The method to get a user from database by id
     * @param id
     * @return A user is match with passed param id
     */
    public User getUserById(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query("user", null, "id = ?", new String[] {String.valueOf(id)}, null, null, null);
            cursor.moveToFirst();
            User user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5),
                    cursor.getInt(6));
            return user;
        } finally {
            cursor.close();
        }
    }
    //endregion

    //region Area code for Truong Ky

    //endregion

    //region Area code for Uyen My

    /**
     * Write into DB
     * @param sqlString
     */
    public void writeData(String sqlString)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sqlString);
    }

    /**
     * read into DB
     * @param sqlString
     * @return
     */
    public Cursor getData(String sqlString)
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sqlString, null);
    }

    public void insertDataCar()
    {
        writeData("INSERT INTO branch VALUES('','BMW', 'bmw.png')");
        writeData("INSERT INTO branch VALUES('','Porscher', 'porsche.png')");
        writeData("INSERT INTO branch VALUES('','Toyota', 'toyota.png')");
        writeData("INSERT INTO branch VALUES('','Honda', 'honda.png')");

        writeData("INSERT INTO version VALUES('','2015')");
        writeData("INSERT INTO version VALUES('','2016')");
        writeData("INSERT INTO version VALUES('','2017')");
        writeData("INSERT INTO version VALUES('','2018')");
        writeData("INSERT INTO version VALUES('','2019')");
        writeData("INSERT INTO version VALUES('','2020')");

        writeData("INSERT INTO color VALUES('','Black')");
        writeData("INSERT INTO color VALUES('','White')");
        writeData("INSERT INTO color VALUES('','Silver')");
        writeData("INSERT INTO color VALUES('','Red')");
        writeData("INSERT INTO color VALUES('','Darkblue')");

        writeData("INSERT INTO fuel VALUES('','Gasoline')");
        writeData("INSERT INTO fuel VALUES('','Oil')");

        writeData("INSERT INTO gear VALUES('','Manual')");
        writeData("INSERT INTO gear VALUES('','Automatic')");
        //v_id INTEGER PRIMARY KEY AUTOINCREMENT, v_name TEXT ,v_licensePlate TEXT, " +
        //                " v_seat INTEGER, v_costPerDate FLOAT, v_costPerKm FLOAT, v_image TEXT, v_status INTEGER, version INTEGER, " +
        //                " branch INTEGER, color INTEGER, fuel INTEGER, gear INTEGER

        writeData("INSERT INTO vehicle VALUES('','Altis 1.8G CVT','65A1-262.95','4','50','3','Toyota_Altis_1.8G_CVT_2020_black.png','1','6','3','1','1','2')");
        writeData("INSERT INTO vehicle VALUES('','Altis 1.8G CVT','65A1-662.85','4','50','3','Toyota_Altis_1.8G_CVT_2020_red.png','1','6','3','2','1','2')");
        writeData("INSERT INTO vehicle VALUES('','Hiace','65A1-656.36','16','70','5','Toyota_Hiace_silver.png','1','4','3','3','2','1')");
        writeData("INSERT INTO vehicle VALUES('','Hiace','65A1-215.61','16','70','5','Toyota_Hiace_white.png','1','4','3','3','2','1')");
        writeData("INSERT INTO vehicle VALUES('','City','65B1-621.21','4','40','3.5','honda-city_darkblue.jpg','1','5','4','5','1','2')");
        writeData("INSERT INTO vehicle VALUES('','City','65B1-356.27','4','40','3.5','honda-city-silver.jpg','1','5','4','3','1','2')");
        writeData("INSERT INTO vehicle VALUES('','Civic','65B1-624.65','4','45','3.5','honda-civic-red.jpg','1','5','4','4','1','2')");
        writeData("INSERT INTO vehicle VALUES('','Civic','65B1-624.65','4','45','3.5','honda-civic-black.jpg','1','5','4','1','1','2')");

    }

    /**
     * list of car
     * @return
     */
    public List<vehicle> getCarList(){
        List<vehicle> lst = new ArrayList<>();
        Cursor cursor = getData("SELECT * FROM vehicle");
        while (cursor.moveToNext())
        {
            int v_id=cursor.getInt(0);
            String v_name=cursor.getString(1);
            String v_licensePlate=cursor.getString(2);
            int v_seat=cursor.getInt(3);
            float v_costPerDate=cursor.getFloat(4);
            float v_costPerKm=cursor.getFloat(5);
            String v_image=cursor.getString(6);
            int v_status=cursor.getInt(7);
            int version=cursor.getInt(8);
            int branch=cursor.getInt(9);
            int color=cursor.getInt(10);
            int fuel=cursor.getInt(11);
            int gear=cursor.getInt(12);;
            lst.add(new vehicle(v_id,v_name ,v_licensePlate,v_seat,v_costPerDate,v_costPerKm,v_image,v_status,version,branch,color,fuel,gear));
        }
        //adapter.notifyDataSetChanged();
        return lst;
    }

    /**
     * list of version
     * @return
     */
    public List<version> getVesionList(){
        List<version> lst = new ArrayList<>();
        Cursor cursor = getData("SELECT * FROM version");
        while (cursor.moveToNext())
        {
            int vs_id=cursor.getInt(0);
            String version = cursor.getString(1);

            lst.add(new version(vs_id,version));
        }
        //adapter.notifyDataSetChanged();
        return lst;
    }

    /**
     * list of branch
     * @return
     */
    public List<branch> getBranchList(){
        List<branch> lst = new ArrayList<>();
        Cursor cursor = getData("SELECT * FROM branch");
        while (cursor.moveToNext())
        {
            int br_id=cursor.getInt(0);
            String br_name = cursor.getString(1);
            String br_logo = cursor.getString(2);

            lst.add(new branch(br_id,br_name,br_logo));
        }
        //adapter.notifyDataSetChanged();
        return lst;
    }
    //endregion

    //region Area code for Thanh Nha

    //endregion

}
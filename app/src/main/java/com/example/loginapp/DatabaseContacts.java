package com.example.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.CountDownTimer;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseContacts extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Contact.db";
    public static final String TABLE_NAME = "Data";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "NUMBER";
    private static Object instance;
    private SQLiteOpenHelper sq;
    SQLiteDatabase db;
    Cursor c = null;


    public DatabaseContacts(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    public static Object getInstance(Context context) {

        if (instance == null) {
            instance = new DatabaseContacts(context);
        }
        return instance;
    }

    public void open() {
        this.db = sq.getWritableDatabase();
    }



    public String getdata(String mobile) {
        c = db.rawQuery("select NAME from Data where NUMBER=" + COL_3 + "", null);
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String name;
            name = c.getString(1);
            buffer.append("" + name);
            // String email=c.getString(2);
            //buffer.append(""+email);
        }
        return buffer.toString();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR,NUMBER VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

  /*  public boolean insertData(String name, String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }*/

    public Cursor getAllData() {
        String number = null;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        // Cursor res = db.rawQuery("select NAME EMAIL from Data where NUMBER="+COL_4 +"",null);
        return res;

    }

    public Cursor getData(String number) {

        SQLiteDatabase db = this.getWritableDatabase();
        //  Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);

        StringBuffer buffer = new StringBuffer();
        String query = "select * from " + TABLE_NAME + " where NUMBER='" + number + "'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public boolean updateData(String id, String name, String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, number);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return true;
    }

    public Integer deleteData(String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "NUMBER = ?", new String[]{number});
    }


    public void insertData(String name,String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
       contentValues.put("NUMBER", phonenumber);
        long startTime=0,EndTime=1000;
        startTime = System.currentTimeMillis();



        db.insert(TABLE_NAME, null, contentValues);
        EndTime=System.currentTimeMillis();



        db.close();
    //long result = db.insert(TABLE_NAME, null, contentValues);


    }

}
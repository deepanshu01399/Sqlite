package com.deepanshu.android.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import androidx.annotation.Nullable;

public class Vivzdatabaseadapter {
    Vivzhelper vivzhelper;

    Vivzdatabaseadapter(Context context) {
        vivzhelper = new Vivzhelper(context);
    }

    public long insertData(String name, String password) {
        SQLiteDatabase db = vivzhelper.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(vivzhelper.NAME, name);
        contentValue.put(vivzhelper.PASSWORD, password);
        long id = db.insert(vivzhelper.Table_Name, null, contentValue);
        return id;
    }

    public String getAllData() {
        SQLiteDatabase database = vivzhelper.getWritableDatabase();
        String[] columns = {vivzhelper.UID, vivzhelper.NAME, vivzhelper.PASSWORD};
        Cursor cursor = database.query(vivzhelper.Table_Name, columns, null, null, null, null, null);
        //seledt id,name,passwrod from vivztable
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
           /*int index= cursor.getColumnIndex(vivzhelper.UID);
            cursor.getInt(index);==0  */
            int cid = cursor.getInt(0);
            String name = cursor.getString(1);
            String password = cursor.getString(2);
            buffer.append("ID: "+cid + " NAME: " + name + " PASSWORD: " + password + ": \n");
        }
        return buffer.toString();
    }

    public String getData(String name) {
        //select name,password from vivtable where ename='deepanshu';
        SQLiteDatabase database = vivzhelper.getWritableDatabase();
        String[] columns = {vivzhelper.UID, vivzhelper.NAME, vivzhelper.PASSWORD};
        Cursor cursor = database.query(vivzhelper.Table_Name, columns, vivzhelper.NAME + " = '" + name + "'", null, null, null, null);
        //seledt id,name,passwrod from vivztable
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int index1=cursor.getColumnIndex(vivzhelper.NAME);//1
            int index2=cursor.getColumnIndex(vivzhelper.PASSWORD);//2
            String personname = cursor.getString(index1);
            String password = cursor.geString(index2);
            buffer.append("NAME: " + personname + ", Password : " + password + ": \n");
        }
        return buffer.toString();
    }
    public String getData(String name,String password) {//get particular data
        //select id from vivtable where name='deepanshu' and password='deepanshu';
        SQLiteDatabase database = vivzhelper.getWritableDatabase();
        String[] columns = {vivzhelper.UID};
        String []selectionArgs={name,password};
        Cursor cursor = database.query(vivzhelper.Table_Name, columns, vivzhelper.NAME + "=? AND "+vivzhelper.PASSWORD+"=?", selectionArgs, null, null, null);
        //seledt id,name,passwrod from vivztable
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int index0=cursor.getColumnIndex(vivzhelper.UID);
            int id  = cursor.getInt(index0);
            buffer.append("ID : " + id );
        }
        return buffer.toString();
    }

    public int upDateName(String oldName,String newName){
        SQLiteDatabase database=vivzhelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(vivzhelper.NAME,newName);
        String []whereArgs={oldName};
        int count=database.update(vivzhelper.Table_Name,contentValues,vivzhelper.NAME+"=?",whereArgs);
        return count;
    }
    public int deleteRow(String name){
        SQLiteDatabase database=vivzhelper.getWritableDatabase();
        //delete * form the vvivztabel wehre name='vivz'
        String []whereArgs={name};
        int count=database.delete(vivzhelper.Table_Name,vivzhelper.NAME+"=?",whereArgs);
        return count;
    }

    static class Vivzhelper extends SQLiteOpenHelper {//dbschema
        //it can accept only static varibales not nonsttaic
        private static final String DataBase_Name = "vivzdatabase.db";
        private static final String Table_Name = "VIVZTABLE";
        private static final String UID = "_id";
        private static final String NAME = "Name";
        private static final String PASSWORD = "Password";
        private static final int DataBaseVersion = 5;
        private Context context;
        public static final String Create_table = " CREATE TABLE " + Table_Name + "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + NAME + " VARCHAR(255)," + PASSWORD + " VARCHAR(255));";
        public static final String Drop_table = "DROP TABLE  IF EXISTS " + Table_Name;


        public Vivzhelper(Context context) {
            super(context, DataBase_Name, null, DataBaseVersion);
            this.context = context;
            Message.message(context, "constructor is called");
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                //db.execSQL("CREATE TABLE VIVZTABLE(_id INTEGER PRIMARY KEY AUTOINCREMENT ,NAME VARCHAR(255));");
                db.execSQL(Create_table);
                Message.message(context, "oncreate is called");

            } catch (SQLException e) {
                Message.message(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //call when something change in the db
            //db.execSQL("DROP TABLE IF EXISTS VIVZTABLE");
            try {
                db.execSQL(Drop_table);
                onCreate(db);
                Message.message(context, "upgrade is called");

            } catch (SQLException e) {
                Message.message(context, "" + e);
            }
        }
    }
}
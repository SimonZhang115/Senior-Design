package com.example.a11157.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.TabLayout;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyLife.db";
    public static final String TABLE_NAME_COURSE = "course_table";
    public static final String TABLE_NAME_ACCOUNT = "account_table";

    public static final String COURSE_COL_1 = "ID";
    public static final String COURSE_COL_2 = "NAME";
    public static final String COURSE_COL_3 = "CLASSROOM";
    public static final String COURSE_COL_4 = "DAYOFWEEK";
    public static final String COURSE_COL_5 = "FROMHOUR";
    public static final String COURSE_COL_6 = "FROMMINUTE";
    public static final String COURSE_COL_7 = "TOHOUR";
    public static final String COURSE_COL_8 = "TOMINUTE";

    public static final String ACCOUNT_COL_1 = "ID";
    public static final String ACCOUNT_COL_2 = "AMOUNT";
    public static final String ACCOUNT_COL_3 = "TYPEOFBILL";
    public static final String ACCOUNT_COL_4 = "TYPEOFMONEY";
    public static final String ACCOUNT_COL_5 = "COMMENT";
    public static final String ACCOUNT_COL_6 = "MONTH";
    public static final String ACCOUNT_COL_7 = "DAY";
    public static final String ACCOUNT_COL_8 = "YEAR";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME_COURSE +
                " (ID TEXT PRIMARY KEY," +
                "NAME TEXT," +
                "CLASSROOM TEXT," +
                "DAYOFWEEK TEXT," +
                "FROMHOUR INTEGER," +
                "FROMMINUTE INTEGER," +
                "TOHOUR INTEGER," +
                "TOMINUTE INTEGER)");

        db.execSQL("create table " + TABLE_NAME_ACCOUNT +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "AMOUNT DOUBLE," +
                "TYPEOFBILL TEXT," +
                "TYPEOFMONEY TEXT," +
                "COMMENT TEXT," +
                "MONTH INTEGER," +
                "DAY INTEGER," +
                "YEAR INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_COURSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ACCOUNT);
        onCreate(db);
    }

    public boolean insertCourseData(String ID, String name, String classroom, String dayOfWeek, int fromHour, int fromMinute, int toHour, int toMinute){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_COL_1, ID);
        contentValues.put(COURSE_COL_2, name);
        contentValues.put(COURSE_COL_3, classroom);
        contentValues.put(COURSE_COL_4, dayOfWeek);
        contentValues.put(COURSE_COL_5, fromHour);
        contentValues.put(COURSE_COL_6, fromMinute);
        contentValues.put(COURSE_COL_7, toHour);
        contentValues.put(COURSE_COL_8, toMinute);
        long result = db.insert(TABLE_NAME_COURSE,null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllCourseData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_COURSE, null);
        return res;
    }

    public boolean updateCourseData(String ID, String name, String classroom, String dayOfWeek, int fromHour, int fromMinute, int toHour, int toMinute){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_COL_1, ID);
        contentValues.put(COURSE_COL_2, name);
        contentValues.put(COURSE_COL_3, classroom);
        contentValues.put(COURSE_COL_4, dayOfWeek);
        contentValues.put(COURSE_COL_5, fromHour);
        contentValues.put(COURSE_COL_6, fromMinute);
        contentValues.put(COURSE_COL_7, toHour);
        contentValues.put(COURSE_COL_8, toMinute);
        db.update(TABLE_NAME_COURSE, contentValues, "ID = ?", new String[] { ID });
        return true;
    }

    public int deleteCourseData(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_COURSE, "ID = ?", new String[] { ID });
    }



    public boolean insertAccountData(double amount, String typeOfBill, String typeOfMoney, String comment, int month, int day, int year){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ACCOUNT_COL_2, amount);
        contentValues.put(ACCOUNT_COL_3, typeOfBill);
        contentValues.put(ACCOUNT_COL_4, typeOfMoney);
        contentValues.put(ACCOUNT_COL_5, comment);
        contentValues.put(ACCOUNT_COL_6, month);
        contentValues.put(ACCOUNT_COL_7, day);
        contentValues.put(ACCOUNT_COL_8, year);
        long result = db.insert(TABLE_NAME_ACCOUNT,null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllAccountData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_ACCOUNT, null);
        return res;
    }

    public boolean updateAccountData(int ID, double amount, String typeOfBill, String typeOfMoney, String comment, int month, int day, int year){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ACCOUNT_COL_1, ID);
        contentValues.put(ACCOUNT_COL_2, amount);
        contentValues.put(ACCOUNT_COL_3, typeOfBill);
        contentValues.put(ACCOUNT_COL_4, typeOfMoney);
        contentValues.put(ACCOUNT_COL_5, comment);
        contentValues.put(ACCOUNT_COL_6, month);
        contentValues.put(ACCOUNT_COL_7, day);
        contentValues.put(ACCOUNT_COL_8, year);
        db.update(TABLE_NAME_ACCOUNT, contentValues, "ID = ?", new String[] { String.valueOf(ID) });
        return true;
    }

    public int deleteAccountData(int ID){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_ACCOUNT, "ID = ?", new String[] { String.valueOf(ID) });
    }

}

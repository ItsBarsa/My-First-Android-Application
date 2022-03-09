package com.barsha.first_android_application.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.Nullable;

import com.barsha.first_android_application.helper.Userinfo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String name = "FAAdb";
    private static final int version = 2;

    String CreateTablesql = "CREATE TABLE IF NOT EXISTS \"user\" (\n" +
            "\t\"id\"\tINTEGER PRIMARY KEY AUTOINCREMENT, \n" +
            "\t\"username\"\tTEXT,\n" +
            "\t\"password\"\tINTEGER,\n" +
            "\t\"firstname\"\tTEXT,\n" +
            "\t\"lastname\"\tTEXT,\n" +
            "\t\"email\"\tTEXT,\n" +
            "\"image\"\tBLOB,\n"+
            "\t\"gender\"\tTEXT\n" +
            ")";



    public DatabaseHelper(@Nullable Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(CreateTablesql);

    }

    public void insertUser(ContentValues contentValues) {
        getWritableDatabase().insert("user", "", contentValues);

    }

    @SuppressLint("Range")
    public ArrayList<Userinfo> getUserList() { // we make list ready that takes the user information
        String sql = "Select * from user";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        ArrayList<Userinfo> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            Userinfo info = new Userinfo();
            info.id = cursor.getString(cursor.getColumnIndex("id"));
            info.username = cursor.getString(cursor.getColumnIndex("username"));
            info.password = cursor.getString(cursor.getColumnIndex("password"));
            info.firstname = cursor.getString(cursor.getColumnIndex("firstname"));
            info.lastname = cursor.getString(cursor.getColumnIndex("lastname"));
            info.email = cursor.getString(cursor.getColumnIndex("email"));
            info.gender = cursor.getString(cursor.getColumnIndex("gender"));
            info.image = cursor.getBlob(cursor.getColumnIndex("image"));



            list.add(info);

        }
        cursor.close();
        return list;

    }

    @SuppressLint("Range")
    public Userinfo getUserinfo(String id) {
        String sql = "Select * from user where id = " + id;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        Userinfo info = new Userinfo();
        while (cursor.moveToNext()) {
            info.id = cursor.getString(cursor.getColumnIndex("id"));
            info.username = cursor.getString(cursor.getColumnIndex("username"));
            info.password = cursor.getString(cursor.getColumnIndex("password"));
            info.firstname = cursor.getString(cursor.getColumnIndex("firstname"));
            info.lastname = cursor.getString(cursor.getColumnIndex("lastname"));
            info.email = cursor.getString(cursor.getColumnIndex("email"));
            info.gender = cursor.getString(cursor.getColumnIndex("gender"));


        }
        cursor.close();
        return info;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateTablesql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("alter table  user add image BLOB");

    }

    public void concept() {
        int a = 1;
        int[] ints = new int[10];
        ints[0] = 100;
        ints[1] = 200;

        a = ints[1];

        ArrayList<String> StringArrayList = new ArrayList<>();
        StringArrayList.add("asdf");
        StringArrayList.add("asdf");
        StringArrayList.add("asdf");
        StringArrayList.add("asdf");
        StringArrayList.add("asdf");

        StringArrayList.get(2);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key1", "barsha");
        hashMap.put("key2", "jharna");

        String value = hashMap.get("key1");

        ArrayList<Userinfo> userinfoArrayList = new ArrayList<>();
        Userinfo info = new Userinfo();
        info.id = "1";
        info.username = "barsha";
        info.email = "barsha@gmail.com";
        userinfoArrayList.add(info);

        ContentValues contentValues = new ContentValues();
        contentValues.put("username", "barsha");
        contentValues.put("password", "barsha12");

    }

   public static byte [] getBlob (Bitmap bitmap){
       ByteArrayOutputStream bos = new ByteArrayOutputStream();
       bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
       byte[] bArray = bos.toByteArray();
       return  bArray;
   }

   public static Bitmap getBitmap (byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
   }

}

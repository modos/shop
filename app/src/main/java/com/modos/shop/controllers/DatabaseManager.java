package com.modos.shop.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.modos.shop.models.Product;
import com.modos.shop.models.User;

public class DatabaseManager extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "shop.db";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(User.QUERY_TABLE);
        db.execSQL(User.QUERY_ADMIN);
        db.execSQL(Product.QUERY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT username FROM " + User.TABLE_NAME + " WHERE username = " + "'"  +
                        user.getUsername() + "'", null);

        if (cursor.getCount() > 0){
            return false;
        }

        ContentValues cv = new ContentValues();
        cv.put(User.USERNAME, user.getUsername());
        cv.put(User.PASSWORD, user.getPassword());
        cv.put(User.NAME, user.getName());
        cv.put(User.AGE, user.getAge());
        cv.put(User.MANAGER, 0);
        db.insert(User.TABLE_NAME, null, cv);
        cursor.close();
        db.close();

        return true;
    }

    public boolean auth(User user){

        Cursor cursor;

       try{
           SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery("SELECT id FROM " + User.TABLE_NAME + " WHERE username = " + "'"  +
                   user.getUsername() + "' AND password = " + "'" + user.getPassword() + "'", null);

           if (cursor.getCount() > 0){
               cursor.close();
               db.close();
               return true;
           }

       }catch (SQLiteException e){
           Log.i("developer" , e.toString());
       }
        return false;
    }

    public boolean isManager(User user){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;

        try{
            cursor = db.rawQuery("SELECT manager FROM " + User.TABLE_NAME + " WHERE username='" +
                user.getUsername() + "' AND manager='1'", null);

            if(cursor.getCount() > 0){
                return true;
            }

        }catch (Exception e){
            Log.i("developer", e.toString());
        }

        return false;
    }

    public boolean insertProduct(Product product){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;

        try {
            cursor = db.rawQuery("SELECT name FROM " + Product.TABLE_NAME + " WHERE name = " + "'"  +
                    product.getName() + "'", null);

            if (cursor.getCount() > 0){
                return false;
        }else {
                ContentValues cv = new ContentValues();
                cv.put(Product.NAME, product.getName());
                cv.put(Product.PRODUCE_DATE, product.getProduceDate());
                cv.put(Product.TYPE, product.getType());
                cv.put(Product.WIDTH, product.getWidth());
                cv.put(Product.HEIGHT, product.getHeight());
                cv.put(Product.WEIGHT, product.getWeight());
                cv.put(Product.COUNTS, product.getCounts());

                db.insert(Product.TABLE_NAME, null, cv);
                //cursor.close();
                db.close();
            }
        }catch (Exception e){
            Log.i("developer", e.toString());
        }

        return true;
    }
}

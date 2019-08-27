package com.modos.shop.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.util.EventLogTags;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.modos.shop.R;
import com.modos.shop.models.Product;
import com.modos.shop.views.Management;

import javax.crypto.spec.DESedeKeySpec;

public class ProductAdapter extends CursorAdapter {

    DatabaseManager dbm;
    SQLiteDatabase db;

    Button plus, minus;

    public ProductAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.row_list_management, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {

           TextView name = (TextView) view.findViewById(R.id.management_list_name);
           TextView type = (TextView) view.findViewById(R.id.management_list_type);
           TextView produceDate = (TextView) view.findViewById(R.id.management_list_produce_date);
           TextView width = (TextView) view.findViewById(R.id.management_list_width);
           TextView height = (TextView) view.findViewById(R.id.management_list_height);
           TextView weight = (TextView) view.findViewById(R.id.management_list_weight);
           TextView counts = (TextView) view.findViewById(R.id.management_list_count);

             dbm = new DatabaseManager(context);

           String mName = cursor.getString(cursor.getColumnIndexOrThrow(Product.NAME));
           String mType = cursor.getString(cursor.getColumnIndexOrThrow(Product.TYPE));
           String mProduceDate = cursor.getString(cursor.getColumnIndexOrThrow(Product.PRODUCE_DATE));
           int mCounts = cursor.getInt(cursor.getColumnIndexOrThrow(Product.COUNTS));
           int mWidth = cursor.getInt(cursor.getColumnIndexOrThrow(Product.WIDTH));
           int mHeight = cursor.getInt(cursor.getColumnIndexOrThrow(Product.HEIGHT));
           int mWeight = cursor.getInt(cursor.getColumnIndexOrThrow(Product.WEIGHT));

           name.setText(mName);
           type.setText(mType);
           produceDate.setText(mProduceDate);
           width.setText(String.valueOf(mWidth));
           height.setText(String.valueOf(mHeight));
           weight.setText(String.valueOf(mWeight));
           counts.setText(String.valueOf(mCounts));

           final int position = cursor.getPosition();

           plus = (Button) view.findViewById(R.id.row_product_button_plus);
           plus.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   try{

                       db = dbm.getWritableDatabase();
                       cursor.moveToPosition(position);

                       int newCount = cursor.getInt(cursor.getColumnIndex("counts")) + 1;

                       ContentValues cv = new ContentValues();
                       cv.put("counts", newCount);

                       db.update(Product.TABLE_NAME, cv, "_id=?",
                               new String[]{String.valueOf(cursor.getInt(0))});

                       Cursor newCursor = db.rawQuery("SELECT * FROM " + Product.TABLE_NAME, null);

                       Management.adapter.swapCursor(newCursor);

                       db.close();

                   }catch (Exception e){
                       Log.i("developer", e.toString());
                   }
               }
           });

           minus = (Button) view.findViewById(R.id.row_product_button_minus);
           minus.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   try{

                       db = dbm.getWritableDatabase();
                       cursor.moveToPosition(position);

                       int newCount = cursor.getInt(cursor.getColumnIndex("counts")) - 1;

                       if(newCount < 0){
                           newCount = 0;
                           Toast.makeText(context, "count is 0", Toast.LENGTH_SHORT).show();
                       }else {

                           ContentValues cv = new ContentValues();
                           cv.put("counts", newCount);

                           db.update(Product.TABLE_NAME, cv, "_id=?",
                                   new String[]{String.valueOf(cursor.getInt(0))});

                           Cursor newCursor = db.rawQuery("SELECT * FROM " + Product.TABLE_NAME, null);

                           Management.adapter.swapCursor(newCursor);

                           db.close();
                       }


                   }catch (Exception e){
                       Log.i("developer", e.toString());
                   }
               }
           });
       }

}

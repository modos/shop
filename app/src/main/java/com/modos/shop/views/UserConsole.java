package com.modos.shop.views;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.modos.shop.R;
import com.modos.shop.controllers.DatabaseManager;
import com.modos.shop.controllers.UserListAdapter;
import com.modos.shop.models.Product;

public class UserConsole extends AppCompatActivity {

    SQLiteDatabase db;
    Cursor cursor;
    DatabaseManager dbm;

    ListView list;
    public static UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_console);

        dbm = new DatabaseManager(this);
        db = dbm.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + Product.TABLE_NAME, null);

        list = (ListView) findViewById(R.id.user_list);
        adapter = new UserListAdapter(this, cursor);
        list.setAdapter(adapter);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}

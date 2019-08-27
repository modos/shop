package com.modos.shop.views;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.modos.shop.R;
import com.modos.shop.controllers.DatabaseManager;
import com.modos.shop.controllers.ProductAdapter;
import com.modos.shop.models.Product;

public class Management extends AppCompatActivity {

    SQLiteDatabase db;
    Cursor cursor;
    DatabaseManager dbm;

    ListView list;
    public static ProductAdapter adapter;

    //for add prodcut
    TextView name, produceDate, type, width, height, weight, count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);

        dbm = new DatabaseManager(this);
        db = dbm.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + Product.TABLE_NAME, null);

        list = (ListView) findViewById(R.id.list);
        adapter = new ProductAdapter(this, cursor);
        list.setAdapter(adapter);

        // for add product
        name = (TextView) findViewById(R.id.management_editText_name_of_product);
        produceDate = (TextView) findViewById(R.id.management_editText_produceDate_of_product);
        type = (TextView) findViewById(R.id.management_editText_type_of_product);
        width = (TextView) findViewById(R.id.management_editText_width_of_product);
        height = (TextView) findViewById(R.id.management_editText_height_of_product);
        weight = (TextView) findViewById(R.id.management_editText_weight_of_product);
        count = (TextView) findViewById(R.id.management_editText_count_of_product);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
    public void onAddClick(View view) {

        try{
            String mName = name.getText().toString();
            String mProduceDate = produceDate.getText().toString();
            String mType = type.getText().toString();
            String mWidth = width.getText().toString();
            String mHeight = height.getText().toString();
            String mWeight = weight.getText().toString();
            String mCount = count.getText().toString();

            Product product = new Product();

            product.setName(mName);
            product.setProduceDate(mProduceDate);
            product.setType(mType);
            product.setWidth(Integer.parseInt(mWidth));
            product.setHeight(Integer.parseInt(mHeight));
            product.setWeight(Integer.parseInt(mWeight));
            product.setCounts(Integer.parseInt(mCount));

                if (dbm.insertProduct(product)){
                    db = dbm.getReadableDatabase();
                    Cursor swapCursor = db.rawQuery("SELECT * FROM " + Product.TABLE_NAME , null);
                   adapter.swapCursor(swapCursor);
                }else{
                    Toast.makeText(this, "product is already exist", Toast.LENGTH_SHORT).show();
                }


        }catch (Exception e){
            Log.i("developer", e.toString());
            Toast.makeText(this, "invalid contents", Toast.LENGTH_SHORT).show();
        }

    }

}

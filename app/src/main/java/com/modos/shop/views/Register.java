package com.modos.shop.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.modos.shop.R;
import com.modos.shop.controllers.DatabaseManager;
import com.modos.shop.models.User;

public class Register extends AppCompatActivity {

    EditText username, password, name, age;
    DatabaseManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.register_editText_username);
        password = (EditText) findViewById(R.id.register_editText_password);
        name = (EditText) findViewById(R.id.register_editText_name);
        age = (EditText) findViewById(R.id.register_editText_age);

        db = new DatabaseManager(this);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void onSubmitClick(View view) {

        User user = new User();

        String mUsername = username.getText().toString();
        String mPassword = password.getText().toString();
        String mName = name.getText().toString();
        String mAge = age.getText().toString();

        user.setUsername(mUsername);
        user.setPassword(mPassword);
        user.setName(mName);
        user.setAge(mAge);

        if (mUsername.isEmpty() || mPassword.isEmpty() || mName.isEmpty() || mAge.isEmpty()){
            Toast.makeText(this, "please fill all the fields" , Toast.LENGTH_SHORT)
                    .show();
        }else{

            if (db.insertUser(user)) {
                Toast.makeText(this, user.getUsername() + " signed up successfully" , Toast.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }else {
                Toast.makeText(this, "username is already taken" , Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}

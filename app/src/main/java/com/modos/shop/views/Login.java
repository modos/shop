package com.modos.shop.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.modos.shop.R;
import com.modos.shop.controllers.DatabaseManager;
import com.modos.shop.models.User;

public class Login extends AppCompatActivity {

    EditText username, password;

    DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.login_editText_username);
        password = (EditText) findViewById(R.id.login_editText_password);
        db = new DatabaseManager(this);
    }

    @Override
    public void onStop(){
        super.onStop();
        username.setText("");
        password.setText("");

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void onSubmitClick(View view) {

        try{
            User user = new User();

            String mUsername = username.getText().toString();
            String mPassword = password.getText().toString();



            if (mUsername.isEmpty() || mPassword.isEmpty()){
                Toast.makeText(this, "please fill all the fields", Toast.LENGTH_SHORT).show();
            }else{

                user.setUsername(mUsername);
                user.setPassword(mPassword);

                if (db.auth(user)){
                    if (db.isManager(user)){
                        Toast.makeText(this, "welcome admin!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, Management.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }else{
                        Toast.makeText(this, "welcome, " + user.getUsername(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, UserConsole.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                }else{
                    Toast.makeText(this, "username or password is wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            Log.i("developer", e.toString());
        }

    }
}

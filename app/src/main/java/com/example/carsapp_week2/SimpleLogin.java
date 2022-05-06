package com.example.carsapp_week2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SimpleLogin extends AppCompatActivity {
    EditText edt_email, edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);

    }

    public void handleLoginBtn(View view) {
        if (view.getId() == R.id.btn_login) {

            String str_email = "", str_password = "";


            str_email = edt_email.getText().toString();
            str_password = edt_password.getText().toString();

            SharedPreferences getPre = getSharedPreferences(str_email.toUpperCase(),0);
            String email = getPre.getString("EMAIL","");
            String pass = getPre.getString("PASSWORD","");


            if (str_email.isEmpty() || str_password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Username and Password is required!", Toast.LENGTH_SHORT).show();
                return;
            }

            if ("root".equals(str_email) && "fit2081".equals(str_password)) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);


                intent.putExtra("username", str_email);
                // similarly set password with intent
                intent.putExtra("password", str_password);

                // start the user details activity using startActivity()
                startActivity(intent);

                // to end the current activity
                finish();

            }
            else if (email.equals(str_email) && pass.equals(str_password)) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                // start the user details activity using startActivity()
                startActivity(intent);
                // to end the current activity
                finish();

            }else {
                // invalid login, display toast message
                Toast.makeText(getApplicationContext(), "Invalid Username and Password!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void registerHandler(View view) {
        Intent intent_user_register = new Intent(getApplicationContext(), Register.class);
        startActivity(intent_user_register);
        finish();


    }
}
package com.example.carsapp_week2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText emailEditText, passEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEditText = findViewById(R.id.email_e);
        passEditText = findViewById(R.id.password_e);
    }

    public void handleRegister(View view) {

        if (view.getId() == R.id.done){
            String email = emailEditText.getText().toString();
            String pass = passEditText.getText().toString();

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(getApplicationContext(), "SETTING Email and Password is required!", Toast.LENGTH_SHORT).show();
                return;
            } else{
                SharedPreferences sharedPre = getSharedPreferences(email.toUpperCase(),0);
                SharedPreferences.Editor editor = sharedPre.edit();


                editor.putString("EMAIL",email);


                editor.putString("PASSWORD",pass);

                editor.apply();
                Toast.makeText(Register.this,"Successfully done",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(),SimpleLogin.class);

                startActivity(intent);
                finish();
            }


        }


    }
}
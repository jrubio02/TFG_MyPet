package com.example.tfg_mypet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginUsers extends AppCompatActivity {

    EditText loginEmail, loginPassword;
    Button loginButton, btnRegister;
    BBDD miBBDD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = (EditText) findViewById(R.id.loginEmail);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        loginButton = (Button) findViewById(R.id.loginButton);
        btnRegister = (Button) findViewById(R.id.btnRegistro);
        miBBDD = new BBDD(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(LoginUsers.this, registerUser.class);
                startActivity(register);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String email = loginEmail.getText().toString();
               String password = loginPassword.getText().toString();

               if(email.equals("")|| password.equals(""))
               {
                   Toast.makeText(LoginUsers.this, "Por favor, rellene los campos", Toast.LENGTH_SHORT).show();
               }
               else {

                   Boolean checkUserPass = miBBDD.comprobarEmailPassword(email, password);
                   if(checkUserPass == true)
                   {
                       Toast.makeText(LoginUsers.this, "Login correcto", Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                       startActivity(intent);
                   }
                   else
                   {
                       Toast.makeText(LoginUsers.this, "Login incorrecto", Toast.LENGTH_SHORT).show();
                   }
               }



            }
        });
    }
}
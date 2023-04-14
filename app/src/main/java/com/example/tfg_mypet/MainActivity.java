package com.example.tfg_mypet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public Button btnInicio;
    BBDD miBBDD;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInicio = (Button) findViewById(R.id.btnInicio);
        miBBDD = new BBDD(this);
        SQLiteDatabase db = miBBDD.getWritableDatabase();
        if(db != null){
            Toast.makeText(MainActivity.this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(MainActivity.this, "ERROR AL CREAR LA BBDD", Toast.LENGTH_LONG).show();

        }


        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginUsers.class);
                startActivity(intent);
            }
        });
    }
}
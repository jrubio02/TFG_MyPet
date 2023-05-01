package com.example.tfg_mypet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PantallaPpal<ImagenView> extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList <String> nombre, edad, genero, raza;
    BBDD miBBD;
    AnimalAdapter miAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_ppal);

        miBBD = new BBDD(this);
        nombre = new ArrayList<>();
        edad = new ArrayList<>();
        genero = new ArrayList<>();
        raza = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        miAdapter = new AnimalAdapter(this, nombre, raza, genero, edad);
        recyclerView.setAdapter(miAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        ImageView imgPerro = findViewById(R.id.categoriaPerro);
        ImageView imgGato = findViewById(R.id.categoriaGato);
        ImageView imgPez = findViewById(R.id.categoriaPez);
        ImageView imgAve = findViewById(R.id.categoriaPajaro);
        ImageView imgHamster = findViewById(R.id.categoriaHamster);
        TextView miCategoria = findViewById(R.id.txtCategoria);
        //primero ejecuto los perros
        displaydata("Perro");
        View.OnClickListener imageClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgPerro.setImageResource(R.drawable.perro_negro);
                imgGato.setImageResource(R.drawable.gato);
                imgAve.setImageResource(R.drawable.ave);
                imgHamster.setImageResource(R.drawable.hamster);
                imgPez.setImageResource(R.drawable.pez);
                switch (v.getId()) {
                    case R.id.categoriaPerro:
                        miCategoria.setText("Perros");
                        imgPerro.setImageResource(R.drawable.perro);
                        displaydata("Perro");

                        break;
                    case R.id.categoriaGato:
                        miCategoria.setText("Gatos");
                        imgGato.setImageResource(R.drawable.gato_naranja);
                        displaydata("Gato");
                        break;
                    case R.id.categoriaPez:
                        miCategoria.setText("Peces");
                        imgPez.setImageResource(R.drawable.pez_naranja);
                        displaydata("Pez");
                        break;
                    case R.id.categoriaPajaro:
                        miCategoria.setText("Aves");
                        imgAve.setImageResource(R.drawable.pajaro_naranja);
                        displaydata("Ave");
                        break;
                    case R.id.categoriaHamster:
                        miCategoria.setText("Hamsters");
                        imgHamster.setImageResource(R.drawable.hamster_naranja);
                        displaydata("Roedor");
                        break;
                }
            }
        };
        imgPerro.setOnClickListener(imageClickListener);
        imgGato.setOnClickListener(imageClickListener);
        imgPez.setOnClickListener(imageClickListener);
        imgAve.setOnClickListener(imageClickListener);
        imgHamster.setOnClickListener(imageClickListener);
    }

    private void displaydata(String tipoAnimal) {
        nombre.clear();
        edad.clear();
        genero.clear();
        raza.clear();
        Cursor cursor = miBBD.getData(tipoAnimal);
        if(cursor.getCount()==0)
        {
            Toast.makeText(this, "No hay datos", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {

            while(cursor.moveToNext())
            {
                nombre.add(cursor.getString(1));
                raza.add(cursor.getString(2));
                genero.add(cursor.getString(3));
                edad.add(cursor.getString(5));

            }
        }
        miAdapter.notifyDataSetChanged();
    }




}
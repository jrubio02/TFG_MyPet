package com.example.tfg_mypet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class DetallesAnimal extends AppCompatActivity {
    TextView nombre, edad, genero, raza, detalles, email;
    String Snombre, Sraza, Sedad, Sgenero, Sdetalles;
    int SidAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_animal);

        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        nombre = findViewById(R.id.perroNombre);
        edad = findViewById(R.id.perroEdad);
        genero = findViewById(R.id.perroGenero);
        raza = findViewById(R.id.perroRaza);
        detalles = findViewById(R.id.perroDescripcion);
        email = findViewById(R.id.email);

        getIntentData();

    }


    void getIntentData()
    {
        if(getIntent().hasExtra("nombre") && getIntent().hasExtra("raza") && getIntent().hasExtra("edad") && getIntent().hasExtra("genero") && getIntent().hasExtra("id"))
        {
            Snombre = getIntent().getStringExtra("nombre");
            Sraza = getIntent().getStringExtra("raza");
            Sedad = getIntent().getStringExtra("edad");
            Sgenero = getIntent().getStringExtra("genero");
            SidAnimal = getIntent().getIntExtra("id", -1);

            BBDD miBBDD = new BBDD(this);
            String emailDueño = miBBDD.getEmailDueño(SidAnimal);

            nombre.setText(Snombre);
            raza.setText("Raza: " + Sraza);
            edad.setText("Edad: " + Sedad + " años");
            genero.setText("Género: " + Sgenero);

            // También puedes mostrar el ID del dueño si lo necesitas
            email.setText(emailDueño);
        }
        else
            Toast.makeText(this, "No hay datos", Toast.LENGTH_SHORT).show();
    }

}
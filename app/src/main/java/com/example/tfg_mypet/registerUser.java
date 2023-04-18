package com.example.tfg_mypet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class registerUser extends AppCompatActivity {

    EditText registerNombre, registerApellidos, registerEmail, registerPassword, registerTelefono;
    Button registerButton;
    BBDD miBBDD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_register_user);

        registerNombre = (EditText) findViewById(R.id.registerNombre);
        registerApellidos = (EditText) findViewById(R.id.registerApellidos);
        registerEmail = (EditText) findViewById(R.id.registerEmail);
        registerPassword = (EditText) findViewById(R.id.registerPassword);
        registerTelefono = (EditText) findViewById(R.id.registerPhone);
        registerButton = (Button) findViewById(R.id.registerButton);
        TextView txtLogin =  findViewById(R.id.txtLogin);
        miBBDD = new BBDD(this);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(registerUser.this, LoginUsers.class);
                startActivity(register);
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = registerNombre.getText().toString();
                String apellidos = registerApellidos.getText().toString();
                String email = registerEmail.getText().toString();
                String password = registerPassword.getText().toString();
                String telefonoString = registerTelefono.getText().toString();
                int telefono = Integer.parseInt(telefonoString);

                if(nombre.equals("")|| apellidos.equals("") || email.equals("") || password.equals("")|| telefonoString.equals(""))
                {
                    Toast.makeText(registerUser.this, "Rellene los campos", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean emailRepetido = miBBDD.comprobarEmail(email);
                            if(emailRepetido == false)
                            {
                                Boolean insert = miBBDD.registerUser(nombre, apellidos, email, password, telefono);
                                if(insert==true)
                                {
                                    Toast.makeText(registerUser.this, "Registro Realizado", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), PantallaPpal.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(registerUser.this, "Fallo en el registro", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                                Toast.makeText(registerUser.this, "Correo ya en uso", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
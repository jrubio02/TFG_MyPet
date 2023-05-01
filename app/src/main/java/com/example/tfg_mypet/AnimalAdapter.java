package com.example.tfg_mypet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.MyViewHolder>
{

    private Context context;
    private ArrayList nombre, raza, genero, edad;

        public AnimalAdapter(Context context, ArrayList nombre, ArrayList raza, ArrayList genero, ArrayList edad) {
            this.context = context;
            this.nombre = nombre;
            this.raza = raza;
            this.genero = genero;
            this.edad = edad;

        }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.item_animal,  parent, false);


            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            holder.nombre.setText(String.valueOf(nombre.get(position)));
            holder.raza.setText(String.valueOf(raza.get(position)));
            holder.edad.setText(String.valueOf(edad.get(position)));
            holder.genero.setText(String.valueOf(genero.get(position)));
        }

        //saber tamaño
        @Override
        public int getItemCount() {
            return nombre.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView nombre, raza, edad, genero;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                nombre = itemView.findViewById(R.id.animalNombre);
                raza = itemView.findViewById(R.id.animalRaza);
                edad = itemView.findViewById(R.id.animalEdad);
                genero = itemView.findViewById(R.id.animalGenero);
            }
        }

}


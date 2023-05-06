package com.example.tfg_mypet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BBDD extends SQLiteOpenHelper {


    public static final String miBBDD = "BBDD.db";

    public BBDD(Context context) {
        super(context, "BBDD.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create Table IF NOT EXISTS Usuario" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre text NOT NULL," +
                "apellidos text NOT NULL," +
                "email text NOT NULL UNIQUE," +
                "password text NOT NULL," +
                "telefono integer NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS Animal (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idDueño INTEGER NOT NULL," +
                "nombre TEXT NOT NULL," +
                "raza TEXT," +
                "genero TEXT," +
                "tamaño TEXT NOT NULL," +
                "edad INTEGER," +
                "tipo TEXT NOT NULL," +
                "descripcion TEXT NOT NULL," +
                "FOREIGN KEY(idDueño) REFERENCES Usuario(id))");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop table if exists Usuario");

        db.execSQL("drop table if exists Animal");

    }

    public Boolean registerUser(String nombre, String apellidos, String email, String password, int telefono)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("nombre", nombre);
        valores.put("apellidos", apellidos);
        valores.put("email", email);
        valores.put("password", password);
        valores.put("telefono", telefono);

        long result = db.insert("Usuario", null, valores);
        if(result==-1)
        {
            return false;
        }
        else
            return true;

    }

    public Boolean comprobarEmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Usuario where email = ?", new String[]{email});
        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
            return false;
    }

    public Boolean comprobarEmailPassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Usuario where email = ? and password = ?", new String[]{email, password});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
            return false;
    }
    
    public Cursor getData(String tipoAnimal)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Animal where tipo = '" + tipoAnimal + "'", null);
        return cursor;
    }
    public String getEmailDueño(int idAnimal) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT Usuario.email " +
                "FROM Usuario " +
                "JOIN Animal ON Usuario.id = Animal.idDueño " +
                "WHERE Animal.Id = " + idAnimal + ";" ;

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String email = cursor.getString(0);
            cursor.close();
            return email;
        } else {
            cursor.close();
            return null;
        }
    }
}

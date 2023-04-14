package com.example.tfg_mypet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BBDD extends SQLiteOpenHelper {


    public static final String miBBDD = "Login.db";

    public BBDD(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create Table IF NOT EXISTS Usuario" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre text NOT NULL UNIQUE," +
                "apellidos text NOT NULL UNIQUE," +
                "email text NOT NULL UNIQUE," +
                "password text NOT NULL," +
                "telefono integer NOT NULL)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop table if exists usuarios");

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
        if(cursor.getColumnCount() > 0)
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
}

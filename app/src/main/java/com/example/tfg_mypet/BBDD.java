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

        db.execSQL("CREATE TABLE IF NOT EXISTS Favoritos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idUsuario INTEGER," +
                "idAnimal INTEGER," +
                "FOREIGN KEY (idUsuario) REFERENCES Usuario(id)," +
                "FOREIGN KEY (idAnimal) REFERENCES Animal(id));");

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
    
    public Cursor getData(String tipoAnimal, int idDueño)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Animal where tipo = '" + tipoAnimal + "' AND idDueño <> " + idDueño, null);
        return cursor;
    }

    public Cursor getPublicacionesDueño(String emailDueño)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT a.* " +
                "FROM Animal a " +
                "JOIN Usuario u " +
                "ON a.iddueño = u.id " +
                "WHERE u.email = '" + emailDueño + "'", null);

        return cursor;
    }

    public Cursor getFavoritos(int idDueño)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT a.id, a.iddueño, a.nombre, a.raza, a.genero, a.tamaño, a.edad, a.tipo, a.descripcion, a.imagen from Animal a join Favoritos f on a.id = f.idanimal where f.idUsuario =" + idDueño + ";";
        Cursor cursor = db.rawQuery(query, null);

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
    public int getIdUsuario(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select id from Usuario where email = '" + email + "';",null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            cursor.close();
            return id;
        } else {
            cursor.close();
            return 0;
        }
    }


    //favoritos
    public boolean añadirFavorito(int idUsuario, int idAnimal) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("INSERT into Favoritos (idUsuario, idAnimal)VALUES(" + idUsuario + ", " + idAnimal + ");", null);

        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
            return false;
    }

    public boolean eliminarFavorito(int idUsuario, int idAnimal) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("DELETE FROM Favoritos WHERE idAnimal = " + idAnimal + " AND idUsuario = " + idUsuario, null);

        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }
    public boolean eliminarPublicación(int idAnimal) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("DELETE FROM Animal WHERE id = " + idAnimal, null);

        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }


    public boolean isFavorito(int idUsuario, int idAnimal) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Favoritos WHERE idUsuario = " + idUsuario + " AND idAnimal =  " + idAnimal + ";", null);

        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
            return false;
    }
    public boolean isPublicacionPropia(int idUsuario, int idAnimal) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Animal where iddueño = " + idUsuario + "  and id =  " + idAnimal, null);

        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
            return false;
    }
    /*
    public Cursor listaFavoritos(String emailDueño)
    {


        return cursor;
    }
*/
}

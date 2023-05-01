package com.example.tfg_mypet;

public class Animal {

    private int id;
    private String nombre;
    private int edad;
    private String raza;
    private String descripcion;
    private String tamaño;
    private String genero;


    public Animal()
    {

    }
    public Animal(int id, String nombre, int edad, String raza, String descripcion, String tamaño, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.raza = raza;
        this.descripcion = descripcion;
        this.tamaño = tamaño;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", raza='" + raza + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tamaño='" + tamaño + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }
}

package com.example.experienciasuc.Entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Carreras {
    private Integer idcarrera;
    private String nombre;
    private String descripcion;
    private Bitmap imagen;
    private Integer numeroCiclos;

    public Carreras() {
    }

    public Carreras(Integer idcarrera, String nombre, String descripcion, Bitmap imagen, Integer numeroCiclos) {
        this.idcarrera = idcarrera;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.numeroCiclos = numeroCiclos;
    }

    public Integer getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(Integer idperro) {
        this.idcarrera = idperro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public Integer getNumeroCiclos() {
        return numeroCiclos;
    }

    public void setNumeroCiclos(Integer numeroCiclos) {
        this.numeroCiclos = numeroCiclos;
    }
//    public void setDataImagen(String data){
//        try{
//            byte[] bytecode = Base64.decode(data,Base64.DEFAULT);
//            this.imagen = BitmapFactory.decodeByteArray(bytecode,0,bytecode.length);
//
//
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//
//        }
//
//
//    }
    public void setDataImagen(String data){
        try{
            byte[] bytecode = Base64.decode(data,Base64.DEFAULT);
            this.imagen = BitmapFactory.decodeByteArray(bytecode,0,bytecode.length);


        }catch (Exception e)
        {
            e.printStackTrace();

        }


    }
    @Override
    public String toString() {
        return "Carreras{" +
                "idcarrera=" + idcarrera +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen=" + imagen +
                ", numeroCiclos=" + numeroCiclos +
                '}';
    }
}


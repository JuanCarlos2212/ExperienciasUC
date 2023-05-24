package com.example.experienciasuc.Entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class CiclosGreen {

    private String id_ciclo;
    private Bitmap imagen_ciclo;
    private String descripcion;
    private String Ruta;


    public CiclosGreen(){

    }

    public CiclosGreen(String id_ciclo, Bitmap imagen_ciclo, String descripcion, String ruta) {
        this.id_ciclo = id_ciclo;
        this.imagen_ciclo = imagen_ciclo;
        this.descripcion = descripcion;
        Ruta = ruta;
    }

    public String getId_ciclo() {
        return id_ciclo;
    }

    public void setId_ciclo(String id_ciclo) {
        this.id_ciclo = id_ciclo;
    }

    public Bitmap getImagen_ciclo() {
        return imagen_ciclo;
    }
    public void setDataImagen(String data){
        try {
            byte[] bytecode= Base64.decode(data,Base64.DEFAULT);
            this.imagen_ciclo= BitmapFactory.decodeByteArray(bytecode, 0, bytecode.length);
        }
        catch (Exception e){e.printStackTrace();}
    }
    public void setImagen_ciclo(Bitmap imagen_ciclo) {
        this.imagen_ciclo = imagen_ciclo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRuta() {
        return Ruta;
    }

    public void setRuta(String ruta) {
        Ruta = ruta;
    }

    @Override
    public String toString() {
        return "CiclosGreen{" +
                "id_ciclo='" + id_ciclo + '\'' +
                ", imagen_ciclo=" + imagen_ciclo +
                ", descripcion='" + descripcion + '\'' +
                ", Ruta='" + Ruta + '\'' +
                '}';
    }
}

package com.example.experienciasuc.Entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Carreras {
    private Integer idcarrera;
    private String nombre;
    private String descripcion;
    private String rutaimagen;
    private Bitmap imagen;
    private Integer numeroCiclos;

    private String plan_estudios;

    public Carreras() {
    }

    public Carreras(Integer idcarrera, String nombre, String descripcion, String rutaimagen, Bitmap imagen, Integer numeroCiclos, String plan_estudios) {
        this.idcarrera = idcarrera;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.rutaimagen = rutaimagen;
        this.imagen = imagen;
        this.numeroCiclos = numeroCiclos;
        this.plan_estudios = plan_estudios;
    }

    public Integer getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(Integer idcarrera) {
        this.idcarrera = idcarrera;
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

    public String getPlan_estudios() {
        return plan_estudios;
    }

    public void setPlan_estudios(String plan_estudios) {
        this.plan_estudios = plan_estudios;
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

    public String getRutaimagen() {
        return rutaimagen;
    }

    public void setRutaimagen(String rutaimagen) {
        this.rutaimagen = rutaimagen;
    }

    @Override
    public String toString() {
        return "Carreras{" +
                "idcarrera=" + idcarrera +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", rutaimagen='" + rutaimagen + '\'' +
                ", imagen=" + imagen +
                ", numeroCiclos=" + numeroCiclos +
                ", plan_estudios='" + plan_estudios + '\'' +
                '}';
    }
}


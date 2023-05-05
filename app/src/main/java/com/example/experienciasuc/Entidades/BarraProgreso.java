package com.example.experienciasuc.Entidades;

public class BarraProgreso {
    private String id_barra;
    private  String id_carrera;
    private String descripcion;


    public BarraProgreso() {

    }
    public BarraProgreso(String id_barra, String id_carrera, String descripcion) {

        this.id_barra = id_barra;
        this.id_carrera = id_carrera;
        this.descripcion = descripcion;
    }



    public String getId_barra() {
        return id_barra;
    }

    public void setId_barra(String id_barra) {
        this.id_barra = id_barra;
    }

    public String getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(String id_carrera) {
        this.id_carrera = id_carrera;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "BarraProgreso{" +
                "id_barra='" + id_barra + '\'' +
                ", id_carrera='" + id_carrera + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}

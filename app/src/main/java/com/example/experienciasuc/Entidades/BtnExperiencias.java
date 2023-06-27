package com.example.experienciasuc.Entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.dynamicanimation.animation.SpringAnimation;

public class BtnExperiencias {

    private Integer id_experiencia;
    private String icono_categoria;

    private String nombre_categoria;
    private int inicio;
    private int fin;

    public BtnExperiencias(){

    }


    public BtnExperiencias(Integer id_experiencia, String icono_categoria, String nombre_categoria, int inicio, int fin) {
        this.id_experiencia = id_experiencia;
        this.icono_categoria = icono_categoria;
        this.nombre_categoria = nombre_categoria;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Integer getId_experiencia() {
        return id_experiencia;
    }

    public void setId_experiencia(Integer id_experiencia) {
        this.id_experiencia = id_experiencia;
    }

    public String getIcono_categoria() {
        return icono_categoria;
    }

    public void setIcono_categoria(String icono_categoria) {
        this.icono_categoria = icono_categoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "BtnExperiencias{" +
                "id_experiencia=" + id_experiencia +
                ", icono_categoria='" + icono_categoria + '\'' +
                ", nombre_categoria='" + nombre_categoria + '\'' +
                ", inicio=" + inicio +
                ", fin=" + fin +
                '}';
    }
}

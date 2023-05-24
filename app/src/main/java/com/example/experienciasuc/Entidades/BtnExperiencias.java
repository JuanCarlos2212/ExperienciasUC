package com.example.experienciasuc.Entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class BtnExperiencias {

    private Integer id_experiencia;

    private Bitmap icono_categoria_blob;

    private String nombre_categoria;

    public BtnExperiencias(){

    }

    public BtnExperiencias(Integer id_experiencia, Bitmap icono_categoria_blob, String nombre_categoria) {
        this.id_experiencia = id_experiencia;
        this.icono_categoria_blob = icono_categoria_blob;
        this.nombre_categoria= nombre_categoria;
    }

    public Integer getId_experiencia() {
        return id_experiencia;
    }

    public void setId_experiencia(Integer id_experiencia) {
        this.id_experiencia = id_experiencia;
    }

    public Bitmap getIcono_categoria_blob() {
        return icono_categoria_blob;
    }

    public void setIcono_categoria_blob(Bitmap icono_categoria_blob) {
        this.icono_categoria_blob = icono_categoria_blob;
    }
    public void setDataImagenIcon(String data){
        try{
            byte[] bytecode = Base64.decode(data,Base64.DEFAULT);
            this.icono_categoria_blob = BitmapFactory.decodeByteArray(bytecode,0,bytecode.length);


        }catch (Exception e)
        {
            e.printStackTrace();

        }


    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    @Override
    public String toString() {
        return "BtnExperiencias{" +
                "id_experiencia=" + id_experiencia +
                ", icono_categoria_blob=" + icono_categoria_blob +
                ", nombre_categoria='" + nombre_categoria + '\'' +
                '}';
    }
}

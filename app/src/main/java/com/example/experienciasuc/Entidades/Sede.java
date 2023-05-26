package com.example.experienciasuc.Entidades;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Sede {

    private Integer ID;

    private String NombreSede;

    private String Ruta;

    //private Bitmap Imagen;

    public Sede(){

    }

    public Sede(Integer ID, String NombreSede, String Ruta)
    {
        this.ID = ID;
        this.NombreSede = NombreSede;
        this.Ruta = Ruta;
       // this.Imagen = Imagen;

    }



    public Integer getID() {
        return ID;
    }
    public void setId(Integer ID)
    {
        this.ID = ID;
    }

    public String getNombreSede() {return NombreSede; }
    public void setNombreSede(String NombreSede) {this.NombreSede =NombreSede; }

    public String getRuta() {return Ruta;}
    public void setRuta(String Ruta) {this.Ruta = Ruta; }

    //public Bitmap getImagen() { return Imagen;}
   // public void setImagen(Bitmap Imagen){this.Imagen = Imagen;}


   // public void setDataImagen(String data){
        //try{
          //  byte[] bytecode = Base64.decode(data,Base64.DEFAULT);
           // this.Imagen = BitmapFactory.decodeByteArray(bytecode,0,bytecode.length);


       // }catch (Exception e)
        //{
         //   e.printStackTrace();

       // }
   // }


    @Override
    public String toString() {
        return "Sede{" +
                "ID=" + ID +
                ", NombreSede='" + NombreSede + '\'' +
                ", Ruta='" + Ruta + '\'' +
                '}';
    }
}

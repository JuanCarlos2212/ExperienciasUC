package com.example.experienciasuc.Entidades;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.experienciasuc.MainActivity;
import com.example.experienciasuc.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CarrerasAdapter extends RecyclerView.Adapter<CarrerasAdapter.CarrerasHolder> implements View.OnClickListener {

    List<Carreras> listaCarreras;

    private View.OnClickListener listener;


    @Override
    public void onClick(View view) {

    }
    public CarrerasAdapter(List<Carreras> listaCarreras){
        this.listaCarreras = listaCarreras;
    }

    @NonNull
    @Override
    public CarrerasAdapter.CarrerasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carreras_universitarias,parent,false);
        RecyclerView.LayoutParams formaLayout = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        vista.setLayoutParams(formaLayout);

//esta linea es para escuchar el evento de seleccion
        vista.setOnClickListener(this);

        return new CarrerasHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull CarrerasAdapter.CarrerasHolder holder, int position) {


        holder.txtnombre.setText(listaCarreras.get(position).getNombre());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Bundle enviardatos = new Bundle();
//                enviardatos.putString("keyDatosidperro",holder.txtidPerro.getText().toString());
//                enviardatos.putString("keyDatosrazaperro", String.valueOf(holder.txtRaza.getText()));
                Intent intent = new Intent(view.getContext(), MainActivity.class);
//                intent.putExtras(enviardatos);
                view.getContext().startActivity(intent);
            }
        });

        if(listaCarreras.get(position).getImagen() != null)
            holder.imgCarrera.setImageBitmap(listaCarreras.get(position).getImagen());
        else
            holder.imgCarrera.setImageResource(R.drawable.img_base);

    }

    @Override
    public int getItemCount() {
        return listaCarreras.size();
    }

    public class CarrerasHolder extends RecyclerView.ViewHolder {

        TextView txtidcarrera,txtnombre, txtciclo, txtdescripciion;
        ImageView imgCarrera;
        public CarrerasHolder(@NonNull View itemView) {
            super(itemView);

            txtnombre = itemView.findViewById(R.id.txtNombredelaCarrera);
            imgCarrera = itemView.findViewById(R.id.imgImagenCarrera);
        }
    }
}

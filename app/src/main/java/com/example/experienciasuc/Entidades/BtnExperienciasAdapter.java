package com.example.experienciasuc.Entidades;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.experienciasuc.R;
import com.example.experienciasuc.experiencias_ciclo;

import java.util.List;

public class BtnExperienciasAdapter extends RecyclerView.Adapter<BtnExperienciasAdapter.ExperienciaHolder> implements View.OnClickListener {

    List<BtnExperiencias> listExperiencias;

    private View.OnClickListener listener;

    @Override
    public void onClick(View view) {

    }

    public BtnExperienciasAdapter(List<BtnExperiencias> listExperiencias) {this.listExperiencias = listExperiencias; }


    @NonNull
    @Override
    public ExperienciaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_experiencias_ciclos,parent, false);
        RecyclerView.LayoutParams formaLayout = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);



        vista.setLayoutParams(formaLayout);

        vista.setOnClickListener(this);

        return new ExperienciaHolder(vista);

    }

    @Override
    public void onBindViewHolder(@NonNull ExperienciaHolder holder, int position) {

        holder.nombre_categria.setText(listExperiencias.get(position).getNombre_categoria());

        if(listExperiencias.get(position).getIcono_categoria_blob() != null)
            holder.ImgIcono.setImageBitmap(listExperiencias.get(position).getIcono_categoria_blob());
        else
            holder.ImgIcono.setImageResource(R.drawable.img_base);
    }

    @Override
    public int getItemCount() {
        return listExperiencias.size();
    }

    public class ExperienciaHolder extends RecyclerView.ViewHolder {

        TextView nombre_categria;
        ImageView ImgIcono;
        public ExperienciaHolder(View itemView) {
            super(itemView);
           nombre_categria= itemView.findViewById(R.id.nombre_categoria);
            ImgIcono = itemView.findViewById(R.id.imgImagenIcono);

        }
    }
}

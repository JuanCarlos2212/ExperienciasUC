package com.example.experienciasuc.Entidades;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.experienciasuc.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class AdaptadorCiclos extends RecyclerView.Adapter<AdaptadorCiclos.CiclosHolder>  {

    /* LayoutInflater inflater;
    ArrayList<CiclosGreen> modelo;
    public AdaptadorCiclos(Context context,ArrayList<CiclosGreen>modelo){
        this.inflater=LayoutInflater.from(inflater.getContext());
        this.modelo=modelo;
    }
    public VistaHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View vista = inflater.inflate(R.layout.datos_ciclos , parent, false);
        RecyclerView.LayoutParams formaLayout = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,-2);
        vista.setLayoutParams(formaLayout);
        return new VistaHolder(vista);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull VistaHolder holder, int position) {
String descripcion=modelo.get(position).getDescripcion();

        if (lista_ciclos.get(position).getImagen_ciclo() != null)
            holder.imgCiclos.setImageBitmap(lista_ciclos.get(position).getImagen_ciclo());
        else
            holder.imgCiclos.setImageResource(R.drawable.img_base);
        holder.txtDesc.setText(lista_ciclos.get(position).getDescripcion());
    }
    @Override
    public int getItemCount() {
        return modelo.size();
    }

    public class VistaHolder extends RecyclerView.ViewHolder {
        TextView txtDesc;
        ImageView imgCiclos;
        public VistaHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            // txtId= itemView.findViewById(R.id.txt_descripcion);
            imgCiclos= itemView.findViewById(R.id.imagen_ciclos);
            txtDesc= itemView.findViewById(R.id.txt_descripcion);


        }
    }*/

    List<CiclosGreen> lista_ciclos;

//    private RecyclerView.OnScrollListener onScrollListener;
//
//    public void setOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
//        this.onScrollListener = onScrollListener;
//    }
//
//    @Override
//    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//        recyclerView.addOnScrollListener(onScrollListener);
//    }


    public AdaptadorCiclos(List<CiclosGreen> lista_ciclos) {
        this.lista_ciclos = lista_ciclos;
    }
    @NonNull
    @NotNull
    @Override
    public CiclosHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.datos_ciclos , parent, false);
        RecyclerView.LayoutParams formaLayout = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,-2);
        vista.setLayoutParams(formaLayout);

        return new CiclosHolder(vista);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull CiclosHolder holder, int position) {
        // holder.txtId.setText(lista_ciclos.get(position).getId_ciclo());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        if (lista_ciclos.get(position).getImagen_ciclo() != null)
            holder.imgCiclos.setImageBitmap(lista_ciclos.get(position).getImagen_ciclo());
        else
            holder.imgCiclos.setImageResource(R.drawable.img_base);
        holder.txtDesc.setText(lista_ciclos.get(position).getDescripcion());
    }
    @Override
    public int getItemCount() {
        return lista_ciclos.size();
    }

    public class CiclosHolder extends RecyclerView.ViewHolder {
        TextView txtDesc;
        ImageView imgCiclos;
        public CiclosHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            // txtId= itemView.findViewById(R.id.txt_descripcion);
            imgCiclos= itemView.findViewById(R.id.imagen_ciclos);
            txtDesc= itemView.findViewById(R.id.txt_descripcion);


        }


    }




}

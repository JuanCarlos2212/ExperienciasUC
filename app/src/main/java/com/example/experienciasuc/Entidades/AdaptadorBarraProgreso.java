package com.example.experienciasuc.Entidades;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.experienciasuc.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class AdaptadorBarraProgreso extends RecyclerView.Adapter<AdaptadorBarraProgreso.BarraProgresoHolder>{

    List<BarraProgreso> listaBarraProgreso;

    public AdaptadorBarraProgreso(List<BarraProgreso> listaBarraProgreso) {
        this.listaBarraProgreso = listaBarraProgreso;

    }


    @NonNull
    @NotNull
    @Override
    public BarraProgresoHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.datos_barras_progreso , parent, false);
        RecyclerView.LayoutParams formaLayout = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,-2);
        vista.setLayoutParams(formaLayout);

        return new BarraProgresoHolder(vista);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull BarraProgresoHolder holder, int position) {
        // holder.txtId.setText(lista_ciclos.get(position).getId_ciclo());

//        if (listaBarraProgreso.get(position).getImagen_ciclo() != null)
//            holder.imgCiclos.setImageBitmap(listaBarraProgreso.get(position).getImagen_ciclo());
//        else
//            holder.imgCiclos.setImageResource(R.drawable.img_base);
        holder.txtDescBarra.setText(listaBarraProgreso.get(position).getDescripcion());
    }
    @Override
    public int getItemCount() {
        return listaBarraProgreso.size();
    }

    public class BarraProgresoHolder extends RecyclerView.ViewHolder {
        TextView txtDescBarra;

        private ProgressBar progressBar;
        public BarraProgresoHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            // txtId= itemView.findViewById(R.id.txt_descripcion);

            txtDescBarra= itemView.findViewById(R.id.txtdescripcion_barraprogreso);

//            progressBar = itemView.findViewById(R.id.pbListaCiclos);

        }

//        public void updateProgress(int progress) {
//            progressBar.setProgress(progress);
//        }
    }






}

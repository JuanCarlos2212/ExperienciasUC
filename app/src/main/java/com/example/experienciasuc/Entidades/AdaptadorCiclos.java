package com.example.experienciasuc.Entidades;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.experienciasuc.MainActivity;
import com.example.experienciasuc.R;
import com.example.experienciasuc.experiencias_ciclo;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class AdaptadorCiclos extends RecyclerView.Adapter<AdaptadorCiclos.CiclosHolder> implements View.OnClickListener  {



    List<CiclosGreen> lista_ciclos;

    Context context;

    private View.OnClickListener listener;

    @Override
    public void onClick(View view) {

    }

    public AdaptadorCiclos(Context context, List<CiclosGreen> lista_ciclos) {
        this.context = context;
        this.lista_ciclos = lista_ciclos;
    }
    @NonNull
    @NotNull
    @Override
    public CiclosHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ciclos , parent, false);
        RecyclerView.LayoutParams formaLayout = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(formaLayout);

        vista.setOnClickListener(this);

        return new CiclosHolder(vista);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull CiclosHolder holder, int position) {

        holder.Ruta= lista_ciclos.get(position).getRuta();
        String idCiclo = lista_ciclos.get(position).getId_ciclo();
        holder.txtDesc.setText(lista_ciclos.get(position).getDescripcion());

        //para imagen de url
        String rutaImagenCiclo;
        rutaImagenCiclo = lista_ciclos.get(position).getRuta();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent = new Intent(view.getContext(), experiencias_ciclo.class);

                intent.putExtra("ciclo", idCiclo);
                view.getContext().startActivity(intent);
                // Obtener la instancia de SharedPreferences
                SharedPreferences sharedPreferences = holder.itemView.getContext().getSharedPreferences("ImgCiclo", Context.MODE_PRIVATE);
                // Obtener el editor de SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                // Guardar una variable
                editor.putString("keyImagenciclo",rutaImagenCiclo);
                editor.apply();

            }
        });

        Glide.with(context)
                .load(rutaImagenCiclo)
                .into(holder.imgCiclos);

    }


    @Override
    public int getItemCount() {
        return lista_ciclos.size();
    }

    public class CiclosHolder extends RecyclerView.ViewHolder {
        TextView txtDesc;
        ImageView imgCiclos;
        String Ruta;
        public CiclosHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            // txtId= itemView.findViewById(R.id.txt_descripcion);
            imgCiclos= itemView.findViewById(R.id.imagen_ciclos);
            txtDesc= itemView.findViewById(R.id.txt_descripcion);


        }


    }




}

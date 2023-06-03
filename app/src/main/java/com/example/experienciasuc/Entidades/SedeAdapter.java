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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.experienciasuc.R;
import com.example.experienciasuc.lista_carreras;

import java.util.List;

public class SedeAdapter extends RecyclerView.Adapter<SedeAdapter.CampusHolder> implements View.OnClickListener {

   // private Context mCtx;
    private List<Sede> listaSede;


    private View.OnClickListener listener;

    @Override
    public void onClick(View view) {
    }

    public SedeAdapter(List<Sede> listaCampus) {
        this.listaSede = listaCampus;
    }


    @NonNull
    @Override
    public CampusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_campus_universidad,parent, false);
        RecyclerView.LayoutParams formaLayout = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);



        vista.setLayoutParams(formaLayout);

        vista.setOnClickListener(this);

        return new CampusHolder(vista);

    }

    @Override
    public void onBindViewHolder(CampusHolder holder, int position) {

        String url;
        url=listaSede.get(position).getRuta();

        RequestOptions requestOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
        holder.txtNombre_sede.setText(listaSede.get(position).getNombreSede());
        //obtenemos nombre de lista de sedes
        String nombreSede =listaSede.get(position).getNombreSede();
        Integer idSede = listaSede.get(position).getID();
        Glide.with(holder.ImgCampus.getContext()).load(url).apply(requestOptions).into(holder.ImgCampus);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = holder.itemView.getContext().getSharedPreferences("PreferSede", Context.MODE_PRIVATE);
                // Obtener el editor de SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                // Guardar una variable
                editor.putString("keyNombreSede",nombreSede);
                editor.putInt("idSede",idSede);
                // Aplicar los cambios
                editor.apply();
                Intent intent = new Intent(v.getContext(), lista_carreras.class);
                v.getContext().startActivity(intent);



            }
        });

       // if(listaSede.get(position).getImagen() != null)
         //   holder.ImgCampus.setImageBitmap(listaSede.get(position).getImagen());
       // else
       //     holder.ImgCampus.setImageResource(R.drawable.img_base);
    }

    @Override
    public int getItemCount() {
        return listaSede.size();
    }

    public class CampusHolder extends RecyclerView.ViewHolder {

        TextView txtId_sede, txtNombre_sede;
        ImageView ImgCampus;
        public CampusHolder(View itemView) {
            super(itemView);
             txtNombre_sede= itemView.findViewById(R.id.txtNombreCampus);
             ImgCampus = itemView.findViewById(R.id.imgImagenSede);

        }
    }
}

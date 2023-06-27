package com.example.experienciasuc.Entidades;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.experienciasuc.MainActivity;
import com.example.experienciasuc.R;

import java.util.List;

public class ContenidoExpAdapter extends RecyclerView.Adapter<ContenidoExpAdapter.ContenidoExpHolder> implements View.OnClickListener {
    List<ContenidoExp> listaContenidoExp;
    private View.OnClickListener listener;
    Context context;

    public void onClick(View view) {

    }
    public ContenidoExpAdapter(List<Carreras> listaCarreras, Context context){
        this.listaContenidoExp = listaContenidoExp;
        this.context = context;
    }

    public ContenidoExpAdapter.ContenidoExpHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contenido_experiencia,parent,false);
        RecyclerView.LayoutParams formaLayout = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(formaLayout);
        vista.setOnClickListener(this);
        return new ContenidoExpAdapter.ContenidoExpHolder(vista);
    }

    public void onBindViewHolder(@NonNull ContenidoExpAdapter.ContenidoExpHolder holder, int position) {

//        String urlcontenido, tipo contenido;
//        String planstudios,rutaimagen,nombreCarrera ;
//        Integer idcarrera;
//        holder.urlcontenido.setText(listaContenidoExp).get(position).getUrlcontenido;
//        holder.tipocontenido.setText(listaContenidoExp).get(position).getTipocontenido;
////        holder.txtnombre.setText(listaContenidoExp.get(position).getNombre());
//        planstudios = listaCarreras.get(position).getPlan_estudios();work
//        idcarrera = listaCarreras.get(position).getIdcarrera();
//        rutaimagen = listaCarreras.get(position).getRutaimagen();
//        nombreCarrera = listaCarreras.get(position).getNombre();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), MainActivity.class);
                // Obtener la instancia de SharedPreferences
                SharedPreferences sharedPreferences = holder.itemView.getContext().getSharedPreferences("MiPref", Context.MODE_PRIVATE);
//                // Obtener el editor de SharedPreferences
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                // Guardar una variable
//                editor.putString("keyplanestudios", planstudios);
//                editor.putString("keynombreCarrera", nombreCarrera);
//                editor.putInt("keytidcarrera", idcarrera);
//                // Aplicar los cambios
//                editor.apply();
//                intent.putExtras(enviardatos);
                view.getContext().startActivity(intent);
            }
        });

//        if(listaCarreras.get(position).getImagen() != null)
//            holder.imgCarrera.setImageBitmap(listaCarreras.get(position).getImagen());
//        else
//            holder.imgCarrera.setImageResource(R.drawable.img_base);



//        Glide.with(context)
//                .load(rutaimagen)
//                .into(holder.imgCarrera);

    }

    @Override
    public int getItemCount() {
        return listaContenidoExp.size();
    }



    public class ContenidoExpHolder extends RecyclerView.ViewHolder{
        TextView txtidcarrera,txtnombre, txtciclo, txtdescripciion;
        ImageView imgCarrera;
        String planstudios,tidcarrera ;


        public ContenidoExpHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

package com.example.experienciasuc.Entidades;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.experienciasuc.ContenidoExperiencia;
import com.example.experienciasuc.MainActivity;
import com.example.experienciasuc.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContenidoExpAdapter extends RecyclerView.Adapter<ContenidoExpAdapter.ContenidoExpHolder> implements View.OnClickListener {
    List<ContenidoExp> listaContenidoExp;
    private View.OnClickListener listener;
    Context context;

    public void onClick(View view) {

    }
    public ContenidoExpAdapter(List<ContenidoExp> listaContenidoExp, Context context){
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

        String urlcontenido, tipocontenido;

//        int idExperiencia = listaContenidoExp.get(position).getId_experiencia();

        urlcontenido = listaContenidoExp.get(position).getUrlcontenido();
        tipocontenido = listaContenidoExp.get(position).getTipocontenido();

        holder.setTipocontenido(tipocontenido,urlcontenido);


        // Remplazar boton Full
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(view.getContext(), MainActivity.class);
//
//                view.getContext().startActivity(intent);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return listaContenidoExp.size();
    }



    public class ContenidoExpHolder extends RecyclerView.ViewHolder{

        String urlcontenido,tipocontenido ;

        WebView webViewContenido;
        YouTubePlayerView youTubePlayerViewContenido;
        ImageView imageViewContenido;


        public ContenidoExpHolder(@NonNull View itemView) {
            super(itemView);

            webViewContenido =itemView.findViewById(R.id.wvContenidoExperiencia);
            youTubePlayerViewContenido = itemView.findViewById(R.id.ypvContenidoExperiencia);
            imageViewContenido = itemView.findViewById(R.id.ivContenidoExperiencia);
        }

        public void setTipocontenido(String tipocontenido, String urlcontenido){
            switch (tipocontenido) {
                case "imagen360":
                    webViewContenido.setVisibility(View.VISIBLE);
                    webViewContenido.getSettings().setJavaScriptEnabled(true);
                    webViewContenido.loadUrl(urlcontenido);

                    break;
                case "video":
                    youTubePlayerViewContenido.setVisibility(View.VISIBLE);
//                    getLifecycle().addObserver(youTubePlayerViewContenido);
                    ((ContenidoExperiencia) context).getLifecycle().addObserver(youTubePlayerViewContenido);


                    youTubePlayerViewContenido.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady(YouTubePlayer youTubePlayer) {
                            String videoId = getYoutubeId(urlcontenido);
                            youTubePlayer.loadVideo(videoId, 0);
                            // Carga el video y lo reproduce automáticamente
                        }
                    });

                    break;
                case "imagen":

                    imageViewContenido.setVisibility(View.VISIBLE);
                    Glide.with(context).load(urlcontenido).into(imageViewContenido);

                    break;
            }
        }
    }

    private String getYoutubeId(String videoUrl) {
        String videoId = null;
        if (videoUrl != null && videoUrl.trim().length() > 0 && (videoUrl.contains("youtube.com") || videoUrl.contains("youtu.be"))) {
            String expression = "^.*((youtu.be\\/)|(v\\/)|(\\/u\\/\\w\\/)|(embed\\/)|(watch\\?))\\??v?=?([^#&?]*).*";
            CharSequence input = videoUrl;
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                videoId = matcher.group(7);
            }
        }
        return videoId;
    }
}

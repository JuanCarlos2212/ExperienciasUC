package com.example.experienciasuc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FullContenido extends AppCompatActivity {


    WebView webViewContenido;
    YouTubePlayerView youTubePlayerViewContenido;
    ImageView imageViewContenido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Oculta la barra de título
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Hace que la actividad sea de pantalla completa
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_full_contenido);
        webViewContenido =findViewById(R.id.wvContenidoExperienciafull);
        youTubePlayerViewContenido =findViewById(R.id.ypvContenidoExperienciafull);
        imageViewContenido =findViewById(R.id.ivContenidoExperienciafull);

        cargar();

    }

    private void cargar() {

        String urlcontenido = getIntent().getStringExtra("ruta_contenido");
        String tipocontenido = getIntent().getStringExtra("tipo_contenido");


        switch (tipocontenido) {
            case "imagen360":
                webViewContenido.setVisibility(View.VISIBLE);
                webViewContenido.getSettings().setJavaScriptEnabled(true);
                webViewContenido.loadUrl(urlcontenido);

                break;
            case "video":
                youTubePlayerViewContenido.setVisibility(View.VISIBLE);
                getLifecycle().addObserver(youTubePlayerViewContenido);


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
                Glide.with(FullContenido.this).load(urlcontenido).into(imageViewContenido);

                break;
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
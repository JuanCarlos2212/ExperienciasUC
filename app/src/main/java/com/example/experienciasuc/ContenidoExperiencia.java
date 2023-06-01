package com.example.experienciasuc;

import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import UtilExperiencias.Utilidades;

public class ContenidoExperiencia extends AppCompatActivity {

    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonArrayRequest jsonArrayRequest;
    TextView txtTitulo, txtSubTitulo, txtParrafo;

    WebView webViewContenido;
    YouTubePlayerView youTubePlayerViewContenido;
    ImageView imageViewContenido,btnFullScreen,btnvolver;
    String id_experiencia;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        // Oculta la barra de navegación
//        View decorView = getWindow().getDecorView();
//        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
//                SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
//        decorView.setSystemUiVisibility(uiOptions);
//        //------
//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
//                View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
//            @Override
//            public void onSystemUiVisibilityChange(int visibility) {
//                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
//                    hideSystemUI();
//                }
//            }
//        });
//        hideSystemUI();





        setContentView(R.layout.activity_contenido_experiencia);


        txtTitulo = findViewById(R.id.txtTituloExperiencia);
        txtSubTitulo = findViewById(R.id.txtSubtituloExperiencia);
        txtParrafo = findViewById(R.id.txtParrafoExperiencia);


        webViewContenido =findViewById(R.id.wvContenidoExperiencia);
        youTubePlayerViewContenido = findViewById(R.id.ypvContenidoExperiencia);
        imageViewContenido = findViewById(R.id.ivContenidoExperiencia);

        btnFullScreen = findViewById(R.id.full);
        btnvolver = findViewById(R.id.volverlistabotones);

        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        requestQueue = Volley.newRequestQueue(this);


        SharedPreferences sharedPreferences= getSharedPreferences("id_experiencia", Context.MODE_PRIVATE);
        int valor = sharedPreferences.getInt("keyidExperiencia",0);
        id_experiencia = String.valueOf(valor);

        consumirAPI();

    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void consumirAPI() {

//        String url = "https://restapi-production-575c.up.railway.app/api/contenidoExperienciaMovil?id_experiencia=5";


        String id= id_experiencia;

        String url = Utilidades.RUTA+ "contenidoExperienciaMovil?id_experiencia="+ id;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = new JSONObject(jsonArray.getString(0));
//                    int idexperiencia = jsonObject.getInt("idexperiencia");
                    String titulo = jsonObject.getString("titulo");
                    String subtitulo = jsonObject.getString("subtitulo");
                    String parrafo = jsonObject.getString("parrafo");
                    String tipocontenido = jsonObject.getString("tipo_contenido");
                    String urlcontenido = jsonObject.getString("ruta_contenido");
                    txtTitulo.setText(titulo);
                    txtSubTitulo.setText(subtitulo);
                    txtParrafo.setText(parrafo);


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
                            Glide.with(ContenidoExperiencia.this).load(urlcontenido).into(imageViewContenido);

                            break;
                    }
                    btnFullScreen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(ContenidoExperiencia.this, FullContenido.class);
                            intent.putExtra("ruta_contenido",urlcontenido);
                            intent.putExtra("tipo_contenido",tipocontenido);
                            startActivity(intent);

                        }
                    });



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
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
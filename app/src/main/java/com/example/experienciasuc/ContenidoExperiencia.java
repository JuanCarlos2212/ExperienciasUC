package com.example.experienciasuc;

import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
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
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.experienciasuc.Entidades.AdaptadorCiclos;
import com.example.experienciasuc.Entidades.CiclosGreen;
import com.example.experienciasuc.Entidades.ContenidoExp;
import com.example.experienciasuc.Entidades.ContenidoExpAdapter;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import UtilExperiencias.Utilidades;


public class ContenidoExperiencia extends AppCompatActivity {
    ProgressDialog progreso;
    ProgressDialog progreso2;
    RequestQueue requestQueue;
    JsonArrayRequest jsonArrayRequest;
    TextView txtTitulo, txtSubTitulo, txtParrafo;
    SliderLayout imageSlider;

    LinearLayout indicadorPuntos;

    private List<ClipData.Item> itemList;

    RecyclerView sliderContenido;
    ArrayList<ContenidoExp> listContenidoExp;
    WebView webViewContenido;
    YouTubePlayerView youTubePlayerViewContenido;
    ImageView imageViewContenido, btnFullScreen, btnvolver;
    String id_experiencia;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido_experiencia);

        sliderContenido = findViewById(R.id.rv_slider_contenido);
        listContenidoExp = new ArrayList<>();

        // Configuración del LayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        sliderContenido.setLayoutManager(layoutManager);
        layoutManager.setSmoothScrollbarEnabled(true); // Para un desplazamiento más suave
        sliderContenido.setHasFixedSize(true);

        txtTitulo = findViewById(R.id.txtTituloExperiencia);
        txtSubTitulo = findViewById(R.id.txtSubtituloExperiencia);
        txtParrafo = findViewById(R.id.txtParrafoExperiencia);
        txtParrafo.setMovementMethod(new ScrollingMovementMethod());


        btnvolver = findViewById(R.id.volverlistabotones);

        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        requestQueue = Volley.newRequestQueue(this);

        SharedPreferences sharedPreferences = getSharedPreferences("id_experiencia", Context.MODE_PRIVATE);
        int valor = sharedPreferences.getInt("keyidExperiencia", 0);
        id_experiencia = String.valueOf(valor);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(sliderContenido);

        ContenidoExpAdapter adapter = new ContenidoExpAdapter(listContenidoExp, ContenidoExperiencia.this);
        sliderContenido.setAdapter(adapter);

        ProgressBar pbCantidadExperiencias = findViewById(R.id.pbCantidadExperiencias);
        sliderContenido.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // Obtén el número total de elementos en la lista de datos
                int totalItems = recyclerView.getAdapter().getItemCount();

                // Obtén el número de elementos actualmente visibles en el RecyclerView
                int itemsVisible = layoutManager.getChildCount();

                // Obtén la posición del primer elemento visible
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                // Calcula el progreso actual en función de los elementos visibles y el total
                float progress = (firstVisibleItemPosition + itemsVisible) / (float) totalItems * 100;

                // Aplica una interpolación lineal al progreso
                float interpolatedProgress = Math.min(progress, 100);

                // Actualiza la ProgressBar con el valor de progreso interpolado
                pbCantidadExperiencias.setProgress((int) interpolatedProgress);
            }
        });


// Obtén los colores de inicio y fin del degradado
//        int colorInicio = getResources().getColor(R.color.iniciio);
//        int colorFin = getResources().getColor(R.color.fin);
//
//// Crea un objeto LinearGradient para definir el degradado de colores
//        LinearGradient gradient = new LinearGradient(
//                0, 0, txtParrafo.getPaint().measureText(txtParrafo.getText().toString()), 0,
//                colorInicio, colorFin,
//                Shader.TileMode.CLAMP
//        );
//
//// Aplica el LinearGradient al Shader del TextView
//        txtParrafo.getPaint().setShader(gradient);
        cargarContenidocomoTal();
        cargarTextosContenido();
    }


    private void cargarContenidocomoTal() {


        progreso2=new ProgressDialog(ContenidoExperiencia.this);
        progreso2.setMessage("Buscando Contenidos... ");
        progreso2.show();
        String id= id_experiencia;

        String url = Utilidades.RUTA+ "contenidoExperienciav2?id_experiencia="+ id;


        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                ContenidoExp contenidoE = null;
                progreso2.hide();

//                JSONArray json = response.optJSONArray(0);
//
                try {
                    for (int i=0;i<response.length();i++) {
                        contenidoE = new ContenidoExp();

                        JSONObject jsonObject= null;
                        jsonObject = response.getJSONObject(i);


                        contenidoE.setTipocontenido(jsonObject.getString("tipo_contenido"));
                        contenidoE.setUrlcontenido(jsonObject.getString("ruta_contenido"));


                        listContenidoExp.add(contenidoE);


                    }
                    ContenidoExpAdapter adapter = new ContenidoExpAdapter(listContenidoExp,ContenidoExperiencia.this);
                    sliderContenido.setAdapter(adapter);
//                    progreso2.dismiss();
                }
                catch (Exception e){e.printStackTrace();}

            }
        },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error Voley", error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    private void cargarTextosContenido() {


            progreso=new ProgressDialog(this);
            progreso.setMessage("Buscando Textos... ");
            progreso.show();
//

            String id= id_experiencia;

//        String url = Utilidades.RUTA+ "contenidoExperienciaMovil?id_experiencia="+ id;
//            String url = Utilidades.RUTA+ "contenidoExperienciav2?id_experiencia="+ id;
            String url = Utilidades.RUTA+ "textoExperiencia?id_experiencia="+ id;


        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                ContenidoExp contenidoE = null;
                progreso.hide();

//                JSONArray json = response.optJSONArray(0);
//
                try {
                    for (int i=0;i<response.length();i++) {
                        contenidoE = new ContenidoExp();

                        JSONObject jsonObject= null;
                        jsonObject = response.getJSONObject(i);

                        String titulo = jsonObject.getString("titulo");
                        String subtitulo = jsonObject.getString("subtitulo");
                        String parrafo = jsonObject.getString("parrafo");

                        txtTitulo.setText(titulo);
                        txtSubTitulo.setText(subtitulo);
                        txtParrafo.setText(parrafo);


                        contenidoE.setTipocontenido(jsonObject.getString("urlcontenido"));
                        contenidoE.setUrlcontenido(jsonObject.getString("tipocontenido"));


                        listContenidoExp.add(contenidoE);

                        btnFullScreen.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(ContenidoExperiencia.this, FullContenido.class);
//                                intent.putExtra("ruta_contenido",urlcontenido);
//                                intent.putExtra("tipo_contenido",tipocontenido);
                                startActivity(intent);

                            }
                        });
                    }
                    ContenidoExpAdapter adapter = new ContenidoExpAdapter(listContenidoExp,ContenidoExperiencia.this);
                    sliderContenido.setAdapter(adapter);
                }
                catch (Exception e){e.printStackTrace();}

            }
        },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error Voley", error.toString());
            }
        });

        requestQueue.add(jsonArrayRequest);


    }
    
    

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
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
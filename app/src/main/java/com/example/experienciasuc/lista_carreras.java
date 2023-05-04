package com.example.experienciasuc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.experienciasuc.Entidades.Carreras;
import com.example.experienciasuc.Entidades.CarrerasAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import UtilExperiencias.Utilidades;

public class lista_carreras extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    RecyclerView recyclerCarreras;

    ArrayList<Carreras> listaCarreras;

    ProgressDialog progreso;

    RequestQueue requestQueue;

    JsonObjectRequest jsonObjectRequest;
    JsonArrayRequest jsonArrayRequest;

    public lista_carreras() {
        // Required empty public constructor
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_carreras);
//
//
//        listaCarreras = new ArrayList<>();
//        recyclerCarreras = findViewById(R.id.recyCarreras);
//        recyclerCarreras.setLayoutManager(new LinearLayoutManager(this));
//        recyclerCarreras.setHasFixedSize(true);

// Inicializar la lista de carreras y el RecyclerView
        listaCarreras = new ArrayList<>();
        recyclerCarreras = findViewById(R.id.recyCarreras);
        recyclerCarreras.setLayoutManager(new LinearLayoutManager(this));
        recyclerCarreras.setHasFixedSize(true);

// Crear una instancia del adaptador y pasarlo al RecyclerView
// por esta ecepcion E/RecyclerView: No adapter attached; skipping layout
        CarrerasAdapter carrerasAdapter = new CarrerasAdapter(listaCarreras);
        recyclerCarreras.setAdapter(carrerasAdapter);


        requestQueue = Volley.newRequestQueue(this);

        cargarListaCarreras();
    }

    private void cargarListaCarreras() {
        progreso = new ProgressDialog(this);
        progreso.setMessage("Buscando Carreras");
        progreso.show();
        String url = Utilidades.RUTA + "listarCarreras" ;
//        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
//        requestQueue.add(jsonObjectRequest);

        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Carreras carreras = null;
                progreso.hide();

                try {
                    for(int i=0; i<response.length();i++){
                        carreras = new Carreras();

                        JSONObject jsonObject = null;
                        jsonObject = response.getJSONObject(i);

                        carreras.setNombre(jsonObject.getString("Nombre"));
                        carreras.setDataImagen(jsonObject.getString("Imagen"));

                        listaCarreras.add(carreras);
                    }
                    CarrerasAdapter adapter = new CarrerasAdapter(listaCarreras);
                    recyclerCarreras.setAdapter(adapter);

                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error en Volleyxx",error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
//        Carreras carreras = null;
//        progreso.hide();
//        JSONArray json = response.optJSONArray("carrera");
//
//        try {
//            for(int i=0; i<json.length();i++){
//                carreras = new Carreras();
//
//                JSONObject jsonObject = null;
//                jsonObject = json.getJSONObject(i);
//
//                carreras.setNombre(jsonObject.getString("nombre_carrera"));
//                carreras.setDataImagen(jsonObject.getString("blob_carrera"));
//
//                listaCarreras.add(carreras);
//            }
//            CarrerasAdapter adapter = new CarrerasAdapter(listaCarreras);
//            recyclerCarreras.setAdapter(adapter);
//
//        }catch (Exception e){ e.printStackTrace();}
    }

    @Override
    public void onErrorResponse(VolleyError error) {
//        Log.i("error en Volley",error.toString());
    }
}
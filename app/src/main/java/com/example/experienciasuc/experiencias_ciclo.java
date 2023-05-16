package com.example.experienciasuc;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.experienciasuc.Entidades.BtnExperiencias;
import com.example.experienciasuc.Entidades.BtnExperienciasAdapter;
import com.example.experienciasuc.R;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import UtilExperiencias.Utilidades;


public class experiencias_ciclo extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    RecyclerView recyclerExperiencia;

    ArrayList<BtnExperiencias> listExperiencia;

    ProgressDialog progreso;

    RequestQueue requestQueue;

    JsonArrayRequest jsonArrayRequest;

    JsonObjectRequest jsonObjectRequest;

    private RecyclerView.LayoutManager mLayoutMager;

    public experiencias_ciclo() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiencias_ciclo);

        listExperiencia = new ArrayList<>();
        recyclerExperiencia = findViewById(R.id.recyclerExperiencias);
        recyclerExperiencia.setLayoutManager(new LinearLayoutManager(this));
        recyclerExperiencia.setHasFixedSize(true);

        requestQueue = Volley.newRequestQueue(this);

        mLayoutMager = new GridLayoutManager(this, getSpanCount());
        recyclerExperiencia.setLayoutManager(mLayoutMager);

        SubirListaBotones();
    }

    private int getSpanCount() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int anchuraPantalla = metrics.widthPixels;

        float anchuraMinimaElemento = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                150,
                metrics
        );
        return (int)(anchuraPantalla / anchuraMinimaElemento);
    }







    private void SubirListaBotones(){
        progreso =new ProgressDialog(this);
        progreso.setMessage("Buscando Experiencias");
        progreso.show();
        String url = Utilidades.RUTA + "llamarBotonExp?id_carrera=1&id_sede=1";
//        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,this,this);
//        requestQueue.add(jsonObjectRequest);
        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                BtnExperiencias experiencia = null;
                progreso.hide();

                try {
                    for(int i=0; i<response.length();i++){
                        experiencia = new BtnExperiencias();

                        JSONObject jsonObject = null;
                        jsonObject = response.getJSONObject(i);

                       // experiencia.setNombreSede(jsonObject.getString("NombreSede"));
                       // String id_carrera = jsonObject.getString("id_carrera");
                       // String id_sede = jsonObject.getString("id_sede");
                        experiencia.setDataImagenIcon(jsonObject.getString("icono_categoria_blob"));

                        listExperiencia.add(experiencia);
                    }
                    BtnExperienciasAdapter adapter = new BtnExperienciasAdapter(listExperiencia);
                    recyclerExperiencia.setAdapter(adapter);

                }catch (Exception e){ e.printStackTrace();}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error en Volley",error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    @Override
    public void onResponse(JSONObject response) {

//        Sede campus = null;
//        prog.hide();
//        JSONArray json = response.optJSONArray("Sede");
//
//        try {
//            for(int i=0; i<json.length();i++){
//                campus = new Sede();
//
//                JSONObject jsonObject = null;
//                jsonObject = json.getJSONObject(i);
//
//                campus.setNombreSede(jsonObject.getString("nombre"));
//                campus.setDataImagen(jsonObject.getString("imagen"));
//
//                listaSede.add(campus);
//            }
//            SedeAdapter adapter = new SedeAdapter(listaSede);
//            recyclerSede.setAdapter(adapter);
//
//        }catch (Exception e){ e.printStackTrace();}

    }


    @Override
    public void onErrorResponse(VolleyError error) {
//        Log.i("error en Volley",error.toString());
    }
}
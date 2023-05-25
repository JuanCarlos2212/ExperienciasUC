package com.example.experienciasuc;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.experienciasuc.Entidades.Carreras;
import com.example.experienciasuc.Entidades.CarrerasAdapter;
import com.example.experienciasuc.Entidades.Sede;
import com.example.experienciasuc.Entidades.SedeAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import UtilExperiencias.Utilidades;


public class lista_campus extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    Context mCtx;
    RecyclerView recyclerSede;

    ArrayList<Sede> listaSede;

    ProgressDialog prog;

    RequestQueue requestQueue;

    JsonArrayRequest jsonArrayRequest;

    JsonObjectRequest jsonObjectRequest;

    public lista_campus(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_campus);

        listaSede = new ArrayList<>();
        recyclerSede = findViewById(R.id.recyclerCampus);
        recyclerSede.setLayoutManager(new LinearLayoutManager(this));
        recyclerSede.setHasFixedSize(true);

        SedeAdapter sedeAdapter = new SedeAdapter(listaSede);
        recyclerSede.setAdapter(sedeAdapter);
        requestQueue = Volley.newRequestQueue(this);

        SubirListaSede();
    }

    private void SubirListaSede(){
        prog =new ProgressDialog(this);
        prog.setMessage("Buscando Sedes");
        prog.show();
        String url = Utilidades.RUTA + "listarSedes";
//        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,this,this);
//        requestQueue.add(jsonObjectRequest);
        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Sede campus = null;
                prog.hide();

                try {
                    for(int i=0; i<response.length();i++){
                        campus = new Sede();

                        JSONObject jsonObject = null;
                        jsonObject = response.getJSONObject(i);

                        campus.setId(jsonObject.getInt("ID"));
                        campus.setNombreSede(jsonObject.getString("NombreSede"));
                        campus.setRuta(jsonObject.getString("Imagen"));

                        listaSede.add(campus);
                    }
                    SedeAdapter adapter = new SedeAdapter(listaSede);
                    recyclerSede.setAdapter(adapter);

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
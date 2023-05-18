package com.example.experienciasuc.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.experienciasuc.Entidades.AdaptadorBarraProgreso;
import com.example.experienciasuc.Entidades.AdaptadorCiclos;
import com.example.experienciasuc.Entidades.BarraProgreso;
import com.example.experienciasuc.Entidades.CiclosGreen;
import com.example.experienciasuc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import UtilExperiencias.Utilidades;

public class fragment_lista_ciclos extends Fragment {

    //implements Response.ErrorListener

//    , Response.Listener<JSONObject>

    RecyclerView recycleCiclos;
    RecyclerView recycleBarraProgreso;
    ArrayList<CiclosGreen> lista_Ciclos;
    ArrayList<BarraProgreso> listaBarraProgreso;

    ProgressDialog progreso;
    ProgressDialog progreso2;
    RequestQueue requestQueue;
    RequestQueue requestQueue2;

    JsonObjectRequest jsonObjectRequest;
    JsonObjectRequest jsonObjectRequest2;
    JsonArrayRequest jsonArrayRequest;
    JsonArrayRequest jsonArrayRequest2;


    ProgressBar progressBar;

    public fragment_lista_ciclos() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =inflater.inflate(R.layout.fragment_lista_ciclos, container, false);
        lista_Ciclos = new ArrayList<>();
        listaBarraProgreso = new ArrayList<>();

        recycleCiclos = vista.findViewById(R.id.recyclerCiclos);
        recycleCiclos.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleCiclos.setHasFixedSize(true);



        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue2 = Volley.newRequestQueue(getContext());

        recycleBarraProgreso=vista.findViewById(R.id.recyclerBarraProgreso);
        recycleBarraProgreso.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleBarraProgreso.setHasFixedSize(true);


//        progressBar = vista.findViewById(R.id.progressBar6);



        cargarListaCiclos();

        cargarListaBarraProgreso();



//        recycleCiclos.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                // Calculate the percentage of the list that has been scrolled
//                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                int visibleItemCount = layoutManager.getChildCount();
//                int totalItemCount = layoutManager.getItemCount();
//                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
//                int percentage = (int) (((float) (firstVisibleItemPosition + visibleItemCount) / totalItemCount) * 100);
//
//                // Update the progress bar with the percentage
//                progressBar.setProgress(percentage);
//            }
//        });
        recycleCiclos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // Calculamos el porcentaje de la lista que se ha desplazado que en este caso es la "lista de ciclos"
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                int percentage = (int) (((float) (firstVisibleItemPosition + visibleItemCount) / totalItemCount) * 100);

                // Actualizamos el progreso de cada ProgressBar en el RecyclerView "recycleBarraProgreso"
                for (int i = 0; i < recycleBarraProgreso.getChildCount(); i++) {
                    View itemView = recycleBarraProgreso.getChildAt(i);
                    ProgressBar progressBar = itemView.findViewById(R.id.pbListaCiclos);
                    progressBar.setProgress(percentage);
                }
            }
        });
        return vista;

    }

    private void cargarListaBarraProgreso() {
        progreso2 = new ProgressDialog(getContext());
        progreso2.setMessage("Buscando...");
        progreso2.show();
        String url = Utilidades.RUTA + "barraCarrera?id_carrera=1";

        jsonArrayRequest2 = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response2) {
                progreso2.hide();
                try {
                    for (int i = 0; i < response2.length(); i++) {
                        JSONObject objeto = response2.getJSONObject(i);
                        String idCarrera = objeto.getString("id_carrera");
                        String idBarra = objeto.getString("id_barra");
                        String descripcionBarra = objeto.getString("descripcion_barra");

                        BarraProgreso datos = new BarraProgreso(idCarrera, idBarra, descripcionBarra);
                        listaBarraProgreso.add(datos);
                    }

                    AdaptadorBarraProgreso adapter2 = new AdaptadorBarraProgreso(listaBarraProgreso);
                    recycleBarraProgreso.setAdapter(adapter2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error Voley", error.toString());
            }
        });

        requestQueue2.add(jsonArrayRequest2);
    }


    private void cargarListaCiclos() {
        progreso=new ProgressDialog(getContext());
        progreso.setMessage("Buscando ");
        progreso.show();
        String url = Utilidades.RUTA+ "listarCiclos";
//        String url = Util.RUTA + "ConsultarListaCiclos.php";
//        url=url.replace(" ","%20");
//        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null, this, this);
//        requestQueue.add(jsonObjectRequest);



        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                CiclosGreen ciclos = null;
                progreso.hide();

//                JSONArray json = response.optJSONArray(0);
//
                try {
                    for (int i=0;i<response.length();i++) {
                        ciclos = new CiclosGreen();

                        JSONObject jsonObject= null;
                        jsonObject = response.getJSONObject(i);


                        ciclos.setDataImagen(jsonObject.getString("Imagen"));
                        ciclos.setDescripcion(jsonObject.getString("Tag"));



                        lista_Ciclos.add(ciclos);

                    }
                    AdaptadorCiclos adapter = new AdaptadorCiclos(lista_Ciclos);
                    recycleCiclos.setAdapter(adapter);
                }
                catch (Exception e){e.printStackTrace();}
                progreso.hide();
            }
        },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error Voley", error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);

    }

//    @Override
//    public void onResponse(JSONObject response) {
//        // Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
//        progreso.hide();
//        CiclosGreen ciclos = new CiclosGreen();
//
////        JSONArray json = response.optJSONArray("ciclos");
//
//        try {
//            for (int i=0;i<response.length();i++) {
//                ciclos = new CiclosGreen();
//
//                JSONObject jsonObject= null;
//                jsonObject = response.getJSONObject(i);
//
////                ciclos.setDataImagen(jsonObject.getString("imagen_ciclo"));
////                ciclos.setDescripcion(jsonObject.getString("descripcion"));
//                ciclos.setDataImagen(jsonObject.getString("Imagen"));
//                ciclos.setDescripcion(jsonObject.getString("Tag"));
//
//                lista_Ciclos.add(ciclos);
//
//            }
//            AdaptadorCiclos adapter = new AdaptadorCiclos(lista_Ciclos);
//            recycleCiclos.setAdapter(adapter);
//        }
//        catch (Exception e){e.printStackTrace();}
//    }

//    private void filtrarPedidos(String tipo){
//        DatabaseReference pedidosReference = mDatabase.child("Pedidos"); // que el objeto pedidosreference
//
//
//        Query pedidofiltrados = pedidosReference.orderByChild("tipo").equalTo(tipo); //<<--- implementar filtro por distrito
//
//
//
//        pedidofiltrados.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                listaPedido.clear();
//
//                for(DataSnapshot objSnapshot : snapshot.getChildren()){
//                    Pedido p = objSnapshot.getValue(Pedido.class);
//                    listaPedido.add(p);
//
//                }
//                arrayAdapterPedido = new ArrayAdapter<Pedido>(Entrega.this,android.R.layout.simple_list_item_1,listaPedido);
//                listViewPedidosEntregar.setAdapter(arrayAdapterPedido);
//
//
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });
//
//    }








}

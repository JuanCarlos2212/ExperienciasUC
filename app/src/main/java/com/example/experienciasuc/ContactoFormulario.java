package com.example.experienciasuc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import UtilExperiencias.Utilidades;

public class ContactoFormulario extends AppCompatActivity {

TextInputEditText edtapePaterno, tdtnombre,edtapellidomaterno,tdtCorreo,tdtCelular,edtfechanac;
    Spinner spinnerModalidad;
Button btnEnviarFormulario;
    ProgressDialog progreso;
    JsonObjectRequest jsonObjectRequest;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_formulario);
        edtapePaterno = findViewById(R.id.edtApellidoPaterno);
        tdtnombre = findViewById(R.id.edtNombresContacto);
        edtapellidomaterno = findViewById(R.id.edtApellidos);
        tdtCorreo = findViewById(R.id.edtCorreo);
        tdtCelular = findViewById(R.id.edtcelular);
        edtfechanac = findViewById(R.id.edtfechNacimiento);
        btnEnviarFormulario = findViewById(R.id.btnEnviarFormulario);
        spinnerModalidad = findViewById(R.id.spinnerModalidad);



        requestQueue = Volley.newRequestQueue(this);

        btnEnviarFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String apellidoMaterno = edtapellidomaterno.getText().toString();
                String nombres = tdtnombre.getText().toString();
                String correo = tdtCorreo.getText().toString();
                String celular = tdtCelular.getText().toString();
                String apellidoPaterno = edtapePaterno.getText().toString();
                String fecha = edtfechanac.getText().toString();
                String modalidad = spinnerModalidad.getSelectedItem().toString();


                if(     apellidoMaterno.isEmpty() ||
                        nombres.isEmpty() ||
                        correo.isEmpty() ||
                        celular.isEmpty() ||
                        apellidoPaterno.isEmpty() ||
                        fecha.isEmpty() ||
                        modalidad.isEmpty()
                ){
                    Toast.makeText(ContactoFormulario.this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    RegistrarInteresado(apellidoMaterno,nombres,correo,celular,apellidoPaterno,fecha,modalidad);
                }
            }
        });



    }
//private void RegistrarInteresado(String apellidoMaterno, String nombres, String correo, String celular, String apellidoPaterno, String fecha, String modalidad) {
//    ProgressDialog progreso = new ProgressDialog(this);
//    progreso.setMessage("Registrando");
//    progreso.show();
//
//    String url = Utilidades.RUTA + "insertarSolicitudInformacion";
//
//    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//            new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    Toast.makeText(ContactoFormulario.this, "Registro Correcto", Toast.LENGTH_SHORT).show();
//                    progreso.hide();
//                }
//            },
//            new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(ContactoFormulario.this, "No se pudo enviar tus datos, intentelo más tarde", Toast.LENGTH_SHORT).show();
//                    progreso.hide();
//                    Log.i("error", error.toString());
//                }
//            }) {
//        @Override
//        public String getBodyContentType() {
//            return "application/json";
//        }
//
//        @Override
//        public byte[] getBody() throws AuthFailureError {
//            try {
//                JSONObject jsonBody = new JSONObject();
//                jsonBody.put("id_carrera", "1");
//                jsonBody.put("nombres", nombres);
//                jsonBody.put("apellido_paterno", apellidoPaterno);
//                jsonBody.put("apellido_materno", apellidoMaterno);
//                jsonBody.put("correo", correo);
//                jsonBody.put("telefono", celular);
//                jsonBody.put("fecha_nacimiento", fecha);
//                jsonBody.put("modalidad", modalidad);
//                return jsonBody.toString().getBytes("utf-8");
//            } catch (JSONException | UnsupportedEncodingException e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//    };
//
//    requestQueue.add(stringRequest);
//}


    private void RegistrarInteresado(String apellidoMaterno, String nombres, String correo, String celular, String apellidoPaterno, String fecha, String modalidad) {
        ProgressDialog progreso = new ProgressDialog(this);
        progreso.setMessage("Registrando");
        progreso.show();

        String url = Utilidades.RUTA + "insertarSolicitudInformacion?" +
                "id_carrera=1" +
                "&nombres=" + nombres +
                "&apellido_paterno=" + apellidoPaterno +
                "&apellido_materno=" + apellidoMaterno +
                "&correo=" + correo +
                "&telefono=" + celular +
                "&fecha_nacimiento=" + fecha +
                "&modalidad=" + modalidad;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        edtapePaterno.setText("");
                        tdtnombre.setText("");
                        edtapellidomaterno.setText("");
                        tdtCorreo.setText("");
                        tdtCelular.setText("");
                        edtfechanac.setText("");
                        btnEnviarFormulario.setText("");
                        Toast.makeText(ContactoFormulario.this, "Registrado Correctamente", Toast.LENGTH_SHORT).show();
                        progreso.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ContactoFormulario.this, "No se pudo enviar tus datos, intentelo más tarde", Toast.LENGTH_SHORT).show();
                        progreso.hide();
                        Log.i("error", error.toString());
                    }
                });

        requestQueue.add(stringRequest);
    }


}
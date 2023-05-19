package com.example.experienciasuc.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.experienciasuc.ContactoFormulario;
import com.example.experienciasuc.R;
import android.content.pm.PackageManager;

public class fragment_contactanos extends Fragment {


    CardView cardWhatsapp, cardReunion, cardFormulario;
    public fragment_contactanos() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_contactanos, container, false);

        cardWhatsapp = (CardView) vista.findViewById(R.id.cardWhatsapp);
        cardReunion = (CardView) vista.findViewById(R.id.cardReunion);
        cardFormulario = (CardView) vista.findViewById(R.id.cardFormulario);

        cardWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirWhatsAppChat();
            }
        });
        cardReunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Muy pronto estará disponible", Toast.LENGTH_SHORT).show();
            }
        });
        cardFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(getContext(), ContactoFormulario.class);
                startActivity(intent);
            }
        });
        return vista;
    }
    private void abrirWhatsAppChat() {

        String numeroEnviarMensaje = "939264247";
        String mensaje = "*¡Hola!* Estoy interesado en una de las carreras que conoci a travez del aplicativo *Experiencias UC :D*";
        Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=" + numeroEnviarMensaje+ "&text=" + Uri.encode(mensaje));

        // Creamos el Intent con la acción "ACTION_VIEW" y el URI de WhatsApp
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

        // Verificando si WhatsApp está instalado en el dispositivo, me da un pequeño error
//        if (intent.resolveActivity(getActivity().getPackageManager()) == null) {
//
//        } else {
//            Toast.makeText(getContext(), "Instale WhatsApp en este dispositivo", Toast.LENGTH_LONG).show();
//        }
    }

}
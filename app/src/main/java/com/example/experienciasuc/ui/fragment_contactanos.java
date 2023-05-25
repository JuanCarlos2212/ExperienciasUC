package com.example.experienciasuc.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.experienciasuc.ContactoFormulario;
import com.example.experienciasuc.R;
import android.content.pm.PackageManager;

import java.util.Calendar;

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
                abrirSalaMeet();
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
    private void abrirSalaMeet() {
        // Obtenemos la hora actual y minutos actuales
        Calendar calendar = Calendar.getInstance();
        int horaActual = calendar.get(Calendar.HOUR_OF_DAY);
        int minutosActuales = calendar.get(Calendar.MINUTE);

        // Verificar si está dentro del horario de atención
        if (horaActual >= 8 && horaActual < 11) {
            // url de la sala de 8 a 1
            String url = "https://meet.google.com/dan-bcvy-bbi";

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
        else if (horaActual >= 15 && horaActual < 18) {
            // url de la sala de 3 a 6
            String url = "https://meet.google.com/dan-bcvy-bbi";

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            // Establecemos el diseño personalizado del dialog
            View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_advertencia_atencion, null);
            builder.setView(view);
            Button btnSiguienteContactanos = view.findViewById(R.id.btnsiguienteContactanos);
            AlertDialog dialog = builder.create();
            //linea 119 hace de que se ponga transparente el fondo para mostrar el corner del dialog personalizado
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

            btnSiguienteContactanos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss(); // Cerramos el primer dialog

                    AlertDialog.Builder secondBuilder = new AlertDialog.Builder(getContext());
                    View secondView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_horario_atencion, null);
                    secondBuilder.setView(secondView);

                    // Crear y mostrar el segundo diálogo
                    AlertDialog secondDialog = secondBuilder.create();
                    secondDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    secondDialog.show();

                    Button btnAceptar = secondView.findViewById(R.id.btnAceptardialog);
                    btnAceptar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            secondDialog.dismiss(); // Cerrar el segundo diálogo
                        }
                    });
                }
            });
        }
    }

}
package com.example.experienciasuc.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.experienciasuc.R;

public class fragment_plan_estudios extends Fragment {
//    TextView txtTexto;
    String urlPlanEstudios;
    WebView webView;
    String url;
    public fragment_plan_estudios() {
        // Required empty public constructor


    }

    public interface OnCarreraClickListener {
        void onCarreraClick(String planEstudios);
    }
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            url = outState.getString("miVariable");
        }

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
//
//            url = args.getString("keyplanEstudios");
//            Toast.makeText(getContext(), "siiii"+url, Toast.LENGTH_SHORT).show();
//            Log.d("MiTag", "El valor de miVariable es: " + url);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_plan_estudios, container, false);

//        Bundle args = getArguments();
//        if (args != null) {
//            urlPlanEstudios = args.getString("keyplanEstudios");
//        } else {
//            Toast.makeText(getContext(), "fallo", Toast.LENGTH_SHORT).show();
//        }

//        textView = view.findViewById(R.id.plan_estudios);
//        textView.setText(urlPlanEstudios);

//        txtTexto = (TextView) view.findViewById(R.id.pruebafragment_plan_estudios);
//        txtTexto.setText(url);
//        Toast.makeText(getContext(), url, Toast.LENGTH_SHORT).show();

//        txtTexto = (TextView) view.findViewById(R.id.pruebafragment_plan_estudios);
        webView = (WebView) view.findViewById(R.id.wvplandeestudiospdf);
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MiPref", getContext().MODE_PRIVATE);
        String planestudios = sharedPreferences.getString("keyplanestudios", "no esta agarrando");

// Habilitar JavaScript si es necesario
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String googleDocsUrl = "https://drive.google.com/viewerng/viewer?embedded=true&url=";
//        Ten en cuenta que la URL se carga usando el visor de Google Drive, que es una forma común de cargar archivos PDF en un WebView.
        webView.loadUrl(googleDocsUrl + planestudios);

//        txtTexto.setText(planestudios);

        return view;
    }

}
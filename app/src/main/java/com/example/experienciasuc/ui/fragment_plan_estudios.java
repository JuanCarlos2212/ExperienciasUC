package com.example.experienciasuc.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.experienciasuc.R;

public class fragment_plan_estudios extends Fragment {

    String urlPlanEstudios;
    WebView webView;
    ProgressDialog progress;
    RoundCornerProgressDialog progress2;
    String url;

    public fragment_plan_estudios() {
        // Required empty public constructor
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_plan_estudios, container, false);

        // ...

        webView = (WebView) view.findViewById(R.id.wvplandeestudiospdf);
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MiPref", getContext().MODE_PRIVATE);
        String planestudios = sharedPreferences.getString("keyplanestudios", "no esta agarrando");

        // Habilitar JavaScript si es necesario
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String googleDocsUrl = "https://drive.google.com/viewerng/viewer?embedded=true&url=";
        webView.loadUrl(googleDocsUrl + planestudios);

        // Configurar WebViewClient para detectar cuando se ha terminado de cargar el PDF
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                // Ocultar el ProgressDialog una vez que el PDF se haya cargado completamente
                progress.dismiss();
            }
        });

        // Inicializar y mostrar el ProgressDialog con esquinas redondeadas
        progress = new RoundCornerProgressDialog(getContext());
        progress.setMessage("Cargando Plan de Estudios");
        progress.setCancelable(false);
        progress.show();

        return view;
    }

    public class RoundCornerProgressDialog extends ProgressDialog {

        public RoundCornerProgressDialog(Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Aplicar esquinas redondeadas al contenedor del ProgressDialog
            getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            getWindow().setDimAmount(0f);
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80000000")));
            getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            getWindow().setGravity(Gravity.CENTER);
            getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.round_corner_dialog));

        }

        private class RoundCornerDrawable extends ColorDrawable {

            private final float radius;
            private Paint roundCornerPaint;

            public RoundCornerDrawable(int color, float radius) {
                super(color);
                this.radius = radius;
                roundCornerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                roundCornerPaint.setColor(color);
                roundCornerPaint.setStyle(Paint.Style.FILL);
            }

            @Override
            public void draw(Canvas canvas) {
                RectF rect = new RectF(getBounds());
                canvas.drawRoundRect(rect, radius, radius, roundCornerPaint);
            }
        }
    }
}

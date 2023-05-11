package com.example.experienciasuc.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.ButtonBarLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.example.experienciasuc.R;
import com.example.experienciasuc.lista_carreras;
import com.example.experienciasuc.lista_campus;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_presentacion2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_presentacion2 extends Fragment {
    Button btnsiguiente;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_presentacion2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_presentacion2.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_presentacion2 newInstance(String param1, String param2) {
        fragment_presentacion2 fragment = new fragment_presentacion2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        View vista = inflater.inflate(R.layout.fragment_presentacion2, container, false);

        btnsiguiente = vista.findViewById(R.id.btnSiguiente);
        btnsiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), lista_campus.class);
                startActivity(i);
            }
        });
        LottieAnimationView animationView = vista.findViewById(R.id.animationView2);
        animationView.postDelayed(new Runnable() {
            @Override
            public void run() {
                animationView.playAnimation();
                animationView.setVisibility(View.VISIBLE);;
                btnsiguiente.setVisibility(View.VISIBLE);
            }
        }, 8000); // Retraso de 3000 milisegundos (3 segundos)


        return vista;
    }
}
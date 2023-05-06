package com.example.experienciasuc.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.example.experienciasuc.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_presentacion1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_presentacion1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public fragment_presentacion1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_presentacion1.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_presentacion1 newInstance(String param1, String param2) {
        fragment_presentacion1 fragment = new fragment_presentacion1();
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
        View view = inflater.inflate(R.layout.fragment_presentacion1, container, false);

        LottieAnimationView animationView = view.findViewById(R.id.animationView);
        animationView.setVisibility(View.INVISIBLE);
        animationView.postDelayed(new Runnable() {
            @Override
            public void run() {
                animationView.playAnimation();
                animationView.setVisibility(View.VISIBLE);;
            }
        }, 2500); // Retraso de 3000 milisegundos (3 segundos)


        return view;
    }

}
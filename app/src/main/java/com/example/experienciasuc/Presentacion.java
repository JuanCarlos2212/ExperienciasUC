package com.example.experienciasuc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.experienciasuc.Entidades.SliderAdapter;
import com.example.experienciasuc.ui.fragment_presentacion1;
import com.example.experienciasuc.ui.fragment_presentacion2;

import java.util.ArrayList;
import java.util.List;

public class Presentacion extends AppCompatActivity {
    ViewPager viewPager;
    SliderAdapter adapter;

    private List<Fragment> fragmentList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentacion);

        fragmentList.add(new fragment_presentacion1());
        fragmentList.add(new fragment_presentacion2());

        ViewPager viewPager = findViewById(R.id.view_pager);
        SliderAdapter sliderAdapter = new SliderAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(sliderAdapter);

    }
}
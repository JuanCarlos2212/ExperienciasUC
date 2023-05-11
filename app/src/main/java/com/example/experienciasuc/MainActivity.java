package com.example.experienciasuc;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.experienciasuc.ui.fragment_plan_estudios;


import com.example.experienciasuc.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        //binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
           // }
        //});
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_plan, R.id.nav_contactanos)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

//        Bundle recibedato = getIntent().getExtras();
//        String url = recibedato.getString("keyplanEstudios");
//        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
//        fragment_plan_estudios fragment = new fragment_plan_estudios();
//        Bundle args = new Bundle();
//        args.putString("keyplanEstudios", "dato a enviar");
//        fragment.setArguments(args);

//        fragment_plan_estudios fragmentPlanEstudios = new fragment_plan_estudios();
//        Bundle args = new Bundle();
//        args.putString("keyplanEstudios",url);
//        fragmentPlanEstudios.setArguments(args);
//        Log.d("TAG", "Valor de urlPlanEstudios: " + args);


//                FragmentTransaction transaction = getFragmentManager().beginTransaction();

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        Fragment fragmentPlanEstudioss = new fragment_plan_estudios();
//                Bundle args = new Bundle();
//                args.putString("keyplanEstudios",url);
//                fragmentPlanEstudioss.setArguments(args);
//
//        transaction.replace(R.id.fragment_container, fragmentPlanEstudioss);
//        transaction.addToBackStack(null); // para permitir volver al fragmento anterior
//        transaction.commit();

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        Fragment fragmentPlanEstudioss = new Fragment();
//        Bundle args = new Bundle();
//        args.putString("keyplanEstudios",url);
//        fragmentPlanEstudioss.setArguments(args);
//        transaction.add(R.id.nav_host_fragment_content_main,fragmentPlanEstudioss);
//        transaction.addToBackStack(null); // para permitir volver al fragmento anterior
//        transaction.commit();
//


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
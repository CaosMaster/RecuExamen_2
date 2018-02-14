package com.example.dm2.recuexamen_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void salir(View v){
        finish();
    }
    public void datos(View v){
        Intent dattos = new Intent(MainActivity.this, BaseDatos.class);
        startActivity(dattos);
    }
    public void web(View v){
        Intent webb = new Intent(MainActivity.this, WebService.class);
        startActivity(webb);
    }
    public void multi(View v){

        Intent multim = new Intent(MainActivity.this, Multimedia.class);
        startActivity(multim);

    }
}

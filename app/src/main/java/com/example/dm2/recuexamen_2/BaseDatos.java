package com.example.dm2.recuexamen_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BaseDatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_datos);
    }

    public void nuevo(View view){
        Intent nuevo= new Intent(this, NuevoLibro.class);
        startActivity(nuevo);
    }

    public void listar(View view){
        Intent list= new Intent(this, ListadoLibros.class);
        startActivity(list);
    }

    public void volver(View v){
        finish();
    }
}

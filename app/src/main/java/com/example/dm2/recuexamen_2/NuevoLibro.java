package com.example.dm2.recuexamen_2;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class NuevoLibro extends AppCompatActivity {

    private EditText titulo;
    private EditText autor;
    private EditText isbn;
    private EditText editorial;
    private EditText num_paginas;
    private CheckBox leido;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_libro);

        titulo=(EditText)findViewById(R.id.txttitulo);
        autor=(EditText)findViewById(R.id.txtautor);
        isbn=(EditText)findViewById(R.id.txtisbn);
        editorial=(EditText)findViewById(R.id.txteditorial);
        num_paginas=(EditText)findViewById(R.id.txtNumpaginas);
        leido=(CheckBox) findViewById(R.id.checkleido);
    }

    public void volver(View v){
        finish();
    }

    public void cancelar(View view){
        titulo.setText("");
        autor.setText("");
        isbn.setText("");
        editorial.setText("");
        num_paginas.setText("");
        leido.setChecked(false);
    }

    public void insertar(View view){
        String porleer="";

        if (!(titulo.getText().toString().equals("") || autor.getText().toString().equals("") || isbn.getText().toString().equals("") || editorial.getText().toString().equals("") || num_paginas.getText().toString().equals(""))) {
            //abrimos la base de datos en modo escritura
            LibrosSqlite agdbh = new LibrosSqlite(this, "DBHlibros2", null, 1);

            db = agdbh.getWritableDatabase();

            //si hemos abierto correctamente la base de datos
            if (db != null) {
                //insertamos los libros

                if (leido.isChecked()) {
                    porleer = "leido";
                }else{
                    porleer = "por leer";
                }

                try {
                    db.execSQL("INSERT INTO libros2 (titulo,autor,isbn,editorial,numPaginas,leido)" + " VALUES ('" + titulo.getText().toString() + "','" + autor.getText().toString() + "','"+ isbn.getText().toString() + "','"+editorial.getText().toString() + "',"+Integer.parseInt(num_paginas.getText().toString()) + ",'"+porleer.toString()+"')");
                    Toast.makeText(this, "libro insertado correctamente", Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    Toast.makeText(this, "el isbn introducido ya existe", Toast.LENGTH_SHORT).show();
                }
            }


            db.close();
            titulo.setText("");
            autor.setText("");
            isbn.setText("");
            editorial.setText("");
            num_paginas.setText("");
            leido.setChecked(false);
        }else {
            Toast.makeText(this, "Introduzca todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}

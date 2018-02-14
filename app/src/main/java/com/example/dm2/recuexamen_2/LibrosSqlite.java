package com.example.dm2.recuexamen_2;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LibrosSqlite extends SQLiteOpenHelper{

    //sentencia SQL para crear la tabla de usuarios
    String sqlCreate = "CREATE TABLE libros2 (titulo TEXT,"+"autor TEXT," + " isbn TEXT PRIMARY KEY," + "editorial TEXT," + "numPaginas NUMBER," + "leido TEXT)";

    public LibrosSqlite (Context contexto, String nombre, SQLiteDatabase.CursorFactory factory,int version){
        super(contexto,nombre,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //se ejecuta la sentencia SQL de creacion de la tabla
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //se elimina la varsion anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS libros2");

        //se crea la nueva version de la tabla
        db.execSQL(sqlCreate);
    }
}

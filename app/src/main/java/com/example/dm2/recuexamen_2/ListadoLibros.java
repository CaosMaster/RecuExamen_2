package com.example.dm2.recuexamen_2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListadoLibros extends AppCompatActivity {

    private ListView lista;
    SQLiteDatabase db;
    private TextView pr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_libros);

        lista=(ListView) findViewById(R.id.listaview);
        pr=(TextView) findViewById( R.id.txtpru);

        //sacamos cada libro de la BBDD
            //abrimos la base de datos en modo escritura
            LibrosSqlite lidbh= new LibrosSqlite(this,"DBHlibros2",null,1);

            db=lidbh.getWritableDatabase();
            final ArrayList<Libro> array=new ArrayList<>();

        class AdaptadorLibro extends ArrayAdapter<Libro> {

            public AdaptadorLibro(Context context, ArrayList<Libro> libro)  {
                super(context, R.layout.items_libros,libro);
            }

            public View getView (int position,View convertView,ViewGroup parent){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View item = inflater.inflate(R.layout.items_libros,null);


                TextView titulo= (TextView) findViewById(R.id.titulo);
                titulo.setText(array.get(position).getTitulo());

                //TextView autor= (TextView) findViewById(R.id.autor);
               // autor.setText(array.get(position).getAutor());

                /*TextView editorial= (TextView) findViewById(R.id.editorial);
                editorial.setText(array.get(position).getEditorial());

                TextView numpag= (TextView) findViewById(R.id.paginas);
                numpag.setText(array.get(position).getTitulo());

                TextView leido= (TextView) findViewById(R.id.leido);
                leido.setText(array.get(position).getLeido());*/
                return (item);
            }
       }

            //si hemos abierto correctamente la base de datos
            if (db!=null){
                //insertamos los usuarios
                Cursor c=db.rawQuery("SELECT titulo,autor,editorial,isbn,numPaginas,leido FROM libros2",null);
                if (c.moveToFirst()){

                    do{
                        String titulo=c.getString(0);
                        String autor=c.getString(1);
                        String editorial=c.getString(2);
                        String isbn=c.getString(3);
                        int numPaginas=c.getInt(4);
                        String leido=c.getString(5);

                        Libro li= new Libro(titulo,autor,editorial,isbn,numPaginas,leido);

                        array.add(li);

                    }while(c.moveToNext());
                }
                AdaptadorLibro adaptador= new AdaptadorLibro(this,array);
                lista.setAdapter(adaptador);
            }

        db.close();
    }



    public void volver(View v){
        finish();
    }
}

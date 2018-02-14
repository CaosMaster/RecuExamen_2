package com.example.dm2.recuexamen_2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

public class WebService extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

        Spinner cmbOpcionesOri;
        Spinner cmbOpcionesDes;
        EditText cantidad;
        TextView resul;
        String resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);

        cantidad=(EditText) findViewById(R.id.txtUnidades);
        resul=(TextView) findViewById(R.id.resultado);

        ArrayList<String> datosSpinner = new ArrayList<String>();
        try {
            InputStream fraw = getResources().openRawResource(R.raw.unidades);
            BufferedReader brin = new BufferedReader( new InputStreamReader(fraw));
            String linea= brin.readLine();
            while(linea!=null){
                datosSpinner.add(linea);
                linea = brin.readLine();
            }
            fraw.close();
            cmbOpcionesOri =(Spinner) findViewById(R.id.cmbOpcionesOrigen);
            cmbOpcionesDes =(Spinner) findViewById(R.id.cmbOpcionesDestino);
            ArrayAdapter<String> adaptador = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, datosSpinner);
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            cmbOpcionesOri.setAdapter(adaptador);
            cmbOpcionesOri.setOnItemSelectedListener(this);
            cmbOpcionesDes.setAdapter(adaptador);
            cmbOpcionesDes.setOnItemSelectedListener(this);
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }

    private class AsyncPost extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {
                HttpURLConnection conn;
                URL url = new URL("http://www.webserviceX.NET/length.asmx/ChangeLengthUnit");
                String param ="LengthValue="+ URLEncoder.encode(params[0],"UTF-8");
                param += "&fromLengthUnit="+URLEncoder.encode(params[1],"UTF-8");
                param += "&toLengthUnit="+URLEncoder.encode(params[2],"UTF-8");
                conn = (HttpURLConnection)url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setFixedLengthStreamingMode(param.getBytes().length);
                conn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                out.print(param);
                out.close();
                String result ="";
                resultado = "";

                Scanner inStream = new Scanner(conn.getInputStream());
                while (inStream.hasNextLine()) {
                    result = inStream.nextLine();
                    if (result.indexOf("double") > 0)
                        resultado = result.replace
                                ("<double xmlns=\"http://www.webserviceX.NET/\">", "").replace("</double>", "");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            resul.setText( cantidad.getText()+" "+cmbOpcionesOri.getSelectedItem().toString()+" SON "+resultado+" "+cmbOpcionesDes.getSelectedItem().toString());
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void calcular(View view){
        String cant = cantidad.getText().toString();
        String deMed = cmbOpcionesOri.getSelectedItem().toString();
        String aMed = cmbOpcionesDes.getSelectedItem().toString();
        AsyncPost task = new AsyncPost();
        task.execute(cant, deMed, aMed);
    }

    public void volver(View v){
        finish();
    }

}

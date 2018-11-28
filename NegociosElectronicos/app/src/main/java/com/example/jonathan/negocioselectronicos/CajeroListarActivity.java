package com.example.jonathan.negocioselectronicos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jonathan.negocioselectronicos.entidades.Cajero;
import com.example.jonathan.negocioselectronicos.utilidades.Utilidades;

import java.util.ArrayList;

public class CajeroListarActivity extends AppCompatActivity {
    ListView CajeroLV;
    ArrayList<String> listaCajerosInformacion;
    ArrayList<Cajero> listaCajeros;
    ConexionSQLiteHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cajero_listar);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"db_cajero",null,1);

        CajeroLV= (ListView)findViewById(R.id.LV_Cajero);
        consultarListaCajeros();

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaCajerosInformacion);
        CajeroLV.setAdapter(adaptador);

    }

    private void consultarListaCajeros() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Cajero cajero=null;
        listaCajeros=new ArrayList<Cajero>();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_CAJERO,null);
        
        while (cursor.moveToNext()){
            cajero=new Cajero();
            cajero.setCodigo(cursor.getInt(0));
            cajero.setNombre(cursor.getString(1));
            cajero.setEstadoRegistro(cursor.getString(2));
            listaCajeros.add(cajero);

        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaCajerosInformacion=new ArrayList<String>();

        for(int i=0;i<listaCajeros.size();i++){
            listaCajerosInformacion.add(listaCajeros.get(i).getCodigo()+" - - "+listaCajeros.get(i).getNombre()+" - - "+listaCajeros.get(i).getEstadoRegistro());

        }
    }
}

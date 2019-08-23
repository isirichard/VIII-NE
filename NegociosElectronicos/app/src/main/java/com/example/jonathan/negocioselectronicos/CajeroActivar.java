package com.example.jonathan.negocioselectronicos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jonathan.negocioselectronicos.entidades.Cajero;
import com.example.jonathan.negocioselectronicos.utilidades.Utilidades;

public class CajeroActivar extends AppCompatActivity {
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cajero_activar);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"db_cajero",null,1);

        Bundle objetoEnviado=getIntent().getExtras();
        Cajero cajero=null;

        if(objetoEnviado!=null){
            cajero= (Cajero) objetoEnviado.getSerializable("cajero2");

            SQLiteDatabase db=conn.getWritableDatabase();
            String[] parametros8={cajero.getCodigo().toString()};
            ContentValues values=new ContentValues();
            values.put(Utilidades.CAMPO_ESTADO_REGISTRO,"A");

            db.update(Utilidades.TABLA_CAJERO,values,Utilidades.CAMPO_CODIGO +"=?",parametros8);
            Toast.makeText(getApplicationContext(),"SE ACTIVO",Toast.LENGTH_LONG).show();
            db.close();
        }
        Intent intent =new Intent(CajeroActivar.this,CajeroListViewActivity.class);
        startActivity(intent);
    }
}

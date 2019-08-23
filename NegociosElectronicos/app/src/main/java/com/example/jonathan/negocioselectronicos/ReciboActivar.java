package com.example.jonathan.negocioselectronicos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jonathan.negocioselectronicos.entidades.Recibo;
import com.example.jonathan.negocioselectronicos.utilidades.UtilidadesRecibo;

public class ReciboActivar extends AppCompatActivity {
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo_activar);

        Bundle objetoEnviado=getIntent().getExtras();
        Recibo recibo=null;

        conn=new ConexionSQLiteHelper(getApplicationContext(),"db_recibo",null,1);

        if(objetoEnviado!=null){
            recibo= (Recibo) objetoEnviado.getSerializable("recibo2");

            SQLiteDatabase db=conn.getWritableDatabase();
            String[] parametros8={recibo.getCodigoRecibo().toString()};
            ContentValues values=new ContentValues();
            values.put(UtilidadesRecibo.CAMPO_ESTADO_REGISTRO3,"A");

            db.update(UtilidadesRecibo.TABLA_RECIBO,values,UtilidadesRecibo.CAMPO_CODIGO_RECIBO+"=?",parametros8);
            Toast.makeText(getApplicationContext(),"SE ACTIVO",Toast.LENGTH_LONG).show();
            db.close();
        }
        Intent intent =new Intent(ReciboActivar.this,ReciboListViewActivity.class);
        startActivity(intent);
    }
}

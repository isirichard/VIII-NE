package com.example.jonathan.negocioselectronicos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jonathan.negocioselectronicos.entidades.Cliente;
import com.example.jonathan.negocioselectronicos.utilidades.UtilidadesCliente;

public class ClienteInactivar extends AppCompatActivity {
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_inactivar);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"db_cliente",null,1);

        Bundle objetoEnviado=getIntent().getExtras();
        Cliente cliente=null;
        if(objetoEnviado!=null){
            cliente= (Cliente) objetoEnviado.getSerializable("cliente2");

            SQLiteDatabase db=conn.getWritableDatabase();
            String[] parametros8={cliente.getCodigo().toString()};
            ContentValues values=new ContentValues();
            values.put(UtilidadesCliente.CAMPO_ESTADO_REGISTRO2,"I");

            db.update(UtilidadesCliente.TABLA_CLIENTE,values,UtilidadesCliente.CAMPO_CODIGO2 +"=?",parametros8);
            Toast.makeText(getApplicationContext(),"SE INACTIVO",Toast.LENGTH_LONG).show();
            db.close();
        }
        Intent intent =new Intent(ClienteInactivar.this,ClienteListViewActivity.class);
        startActivity(intent);
    }
}

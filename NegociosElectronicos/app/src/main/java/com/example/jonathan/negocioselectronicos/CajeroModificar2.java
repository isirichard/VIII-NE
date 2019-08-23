package com.example.jonathan.negocioselectronicos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonathan.negocioselectronicos.entidades.Cajero;
import com.example.jonathan.negocioselectronicos.entidades.Cliente;
import com.example.jonathan.negocioselectronicos.utilidades.Utilidades;
import com.example.jonathan.negocioselectronicos.utilidades.UtilidadesCliente;

public class CajeroModificar2 extends AppCompatActivity {
    EditText Cajero_Modificar_Nombre;
    TextView Cajero_Modificar_ER1,Cajero_Modificar_Codigo;
    Button Cajero_Modificar_Atras,Cajero_Modificar_Modificar;
    ConexionSQLiteHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cajero_modificar2);
        conn = new ConexionSQLiteHelper(getApplicationContext(), "db_cajero", null, 1);

        Cajero_Modificar_Codigo = (TextView) findViewById(R.id.ET_Cajero_Modificar2_Codigo);
        Cajero_Modificar_ER1 = (TextView) findViewById(R.id.TV_Cajero_Modificar2_ER);

        Cajero_Modificar_Nombre = (EditText) findViewById(R.id.ET_Cajero_Modificar2_Nombre);

        Cajero_Modificar_Atras = (Button) findViewById(R.id.B_Cajero_Modificar2_Atras);
        Cajero_Modificar_Modificar = (Button) findViewById(R.id.B_Cajero_Modificar2_Modificar);

        Bundle objetoEnviado = getIntent().getExtras();
        Cajero cajero = null;

        if (objetoEnviado != null) {

            cajero = (Cajero) objetoEnviado.getSerializable("cajero2");
            Cajero_Modificar_Codigo.setText(cajero.getCodigo().toString());

            SQLiteDatabase db = conn.getReadableDatabase();
            String[] parametros = {cajero.getCodigo().toString()};

            String[] campos = {Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_ESTADO_REGISTRO};

            try {
                Cursor cursor = db.query(Utilidades.TABLA_CAJERO, campos, Utilidades.CAMPO_CODIGO + "=?", parametros, null, null, null);
                cursor.moveToFirst();
                Cajero_Modificar_Nombre.setText(cursor.getString(0));
                Cajero_Modificar_ER1.setText(cursor.getString(1));
                cursor.close();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "El codigo no existe", Toast.LENGTH_SHORT).show();
            }
        }

        Cajero_Modificar_Modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db=conn.getWritableDatabase();
                String[] parametros2={Cajero_Modificar_Codigo.getText().toString()};
                ContentValues values=new ContentValues();
                values.put(Utilidades.CAMPO_NOMBRE,Cajero_Modificar_Nombre.getText().toString());
                values.put(Utilidades.CAMPO_ESTADO_REGISTRO,Cajero_Modificar_ER1.getText().toString());

                db.update(Utilidades.TABLA_CAJERO,values,Utilidades.CAMPO_CODIGO+"=?",parametros2);
                Toast.makeText(getApplicationContext(),"SE MODIFICO",Toast.LENGTH_LONG).show();
                db.close();
                Intent intent=new Intent(CajeroModificar2.this,CajeroListViewActivity.class);
                startActivity(intent);
            }
        });

        Cajero_Modificar_Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CajeroModificar2.this,CajeroListViewActivity.class);
                startActivity(intent);
            }
        });


    }
}

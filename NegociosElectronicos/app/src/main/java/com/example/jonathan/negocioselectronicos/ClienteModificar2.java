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

import com.example.jonathan.negocioselectronicos.entidades.Cliente;
import com.example.jonathan.negocioselectronicos.entidades.Recibo;
import com.example.jonathan.negocioselectronicos.utilidades.UtilidadesCliente;
import com.example.jonathan.negocioselectronicos.utilidades.UtilidadesRecibo;

public class ClienteModificar2 extends AppCompatActivity {
    EditText Cliente_Modificar_Nombre;
    TextView Cliente_Modificar_ER1,Cliente_Modificar_Codigo;
    Button Cliente_Modificar_Atras,Cliente_Modificar_Modificar;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_modificar2);
        conn = new ConexionSQLiteHelper(getApplicationContext(), "db_cliente", null, 1);

        Cliente_Modificar_Codigo = (TextView) findViewById(R.id.ET_Cliente_Modificar2_Codigo);
        Cliente_Modificar_ER1 = (TextView) findViewById(R.id.TV_Cliente_Modificar2_ER);

        Cliente_Modificar_Nombre = (EditText) findViewById(R.id.ET_Cliente_Modificar2_Nombre);

        Cliente_Modificar_Atras = (Button) findViewById(R.id.B_Cliente_Modificar2_Atras);
        Cliente_Modificar_Modificar = (Button) findViewById(R.id.B_Cliente_Modificar2_Modificar);

        Bundle objetoEnviado = getIntent().getExtras();
        Cliente cliente = null;

        if (objetoEnviado != null) {
            cliente = (Cliente) objetoEnviado.getSerializable("cliente2");
            Cliente_Modificar_Codigo.setText(cliente.getCodigo().toString());

            SQLiteDatabase db = conn.getReadableDatabase();
            String[] parametros = {cliente.getCodigo().toString()};

            String[] campos = {UtilidadesCliente.CAMPO_NOMBRE2, UtilidadesCliente.CAMPO_ESTADO_REGISTRO2};

            try {
                Cursor cursor = db.query(UtilidadesCliente.TABLA_CLIENTE, campos, UtilidadesCliente.CAMPO_CODIGO2 + "=?", parametros, null, null, null);
                cursor.moveToFirst();
                Cliente_Modificar_Nombre.setText(cursor.getString(0));
                Cliente_Modificar_ER1.setText(cursor.getString(1));
                cursor.close();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "El codigo no existe", Toast.LENGTH_SHORT).show();
            }
        }

        Cliente_Modificar_Modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db=conn.getWritableDatabase();
                String[] parametros2={Cliente_Modificar_Codigo.getText().toString()};
                ContentValues values=new ContentValues();
                values.put(UtilidadesCliente.CAMPO_NOMBRE2,Cliente_Modificar_Nombre.getText().toString());
                values.put(UtilidadesCliente.CAMPO_ESTADO_REGISTRO2,Cliente_Modificar_ER1.getText().toString());

                db.update(UtilidadesCliente.TABLA_CLIENTE,values,UtilidadesCliente.CAMPO_CODIGO2+"=?",parametros2);
                Toast.makeText(getApplicationContext(),"SE MODIFICO",Toast.LENGTH_LONG).show();
                db.close();
                Intent intent=new Intent(ClienteModificar2.this,ClienteListViewActivity.class);
                startActivity(intent);
            }
        });

        Cliente_Modificar_Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ClienteModificar2.this,ClienteListViewActivity.class);
                startActivity(intent);
            }
        });

    }

}

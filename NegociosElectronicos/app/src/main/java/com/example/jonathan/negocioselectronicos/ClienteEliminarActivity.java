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
import com.example.jonathan.negocioselectronicos.utilidades.Utilidades;
import com.example.jonathan.negocioselectronicos.utilidades.UtilidadesCliente;

public class ClienteEliminarActivity extends AppCompatActivity {

    EditText Cliente_Eliminar_Codigo;
    TextView Cliente_Eliminar_Nombre,Cliente_Eliminar_ER;
    Button Cliente_Eliminar_Atras,Cliente_Eliminar_Eliminar,Cliente_Eliminar_Buscar;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_eliminar);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"db_cliente",null,1);

        Cliente_Eliminar_Codigo=(EditText)findViewById(R.id.ET_Cliente_Eliminar_Codigo);

        Cliente_Eliminar_Nombre=(TextView)findViewById(R.id.TV_Cliente_Eliminar_Nombre);
        Cliente_Eliminar_ER=(TextView)findViewById(R.id.TV_Cliente_Eliminar_ER);

        Cliente_Eliminar_Atras=(Button)findViewById(R.id.B_Cliente_Eliminar_Atras);
        Cliente_Eliminar_Buscar=(Button)findViewById(R.id.B_Cliente_Eliminar_Buscar);
        Cliente_Eliminar_Eliminar=(Button)findViewById(R.id.B_Cliente_Eliminar_Eliminar);

        Cliente_Eliminar_Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=conn.getReadableDatabase();
                String[] parametros2={Cliente_Eliminar_Codigo.getText().toString()};
                String[]campos={UtilidadesCliente.CAMPO_NOMBRE2, UtilidadesCliente.CAMPO_ESTADO_REGISTRO2};
                try{
                    Cursor cursor=db.query(UtilidadesCliente.TABLA_CLIENTE,campos,UtilidadesCliente.CAMPO_CODIGO2+"=?",parametros2,null,null,null);
                    cursor.moveToFirst();
                    Cliente_Eliminar_Nombre.setText(cursor.getString(0));
                    Cliente_Eliminar_ER.setText(cursor.getString(1));
                    cursor.close();

                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"El codigo no existe",Toast.LENGTH_SHORT).show();
                    Cliente_Eliminar_Codigo.setText("");
                }

            }
        });

        Cliente_Eliminar_Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=conn.getWritableDatabase();
                String[] parametros2={Cliente_Eliminar_Codigo.getText().toString()};
                ContentValues values=new ContentValues();

                values.put(UtilidadesCliente.CAMPO_NOMBRE2,Cliente_Eliminar_Nombre.getText().toString());
                values.put(UtilidadesCliente.CAMPO_ESTADO_REGISTRO2,"*");

                db.update(UtilidadesCliente.TABLA_CLIENTE,values,UtilidadesCliente.CAMPO_CODIGO2+"=?",parametros2);
                Toast.makeText(getApplicationContext(),"SE ELIMINO",Toast.LENGTH_LONG).show();
                db.close();
            }
        });


        Cliente_Eliminar_Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ClienteEliminarActivity.this,ClienteMenuActivity.class);
                startActivity(intent);
            }
        });



    }
}

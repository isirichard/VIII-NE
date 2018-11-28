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

import com.example.jonathan.negocioselectronicos.utilidades.Utilidades;

public class CajeroEliminarActivity extends AppCompatActivity {
    EditText Cajero_Eliminar_Codigo;
    TextView Cajero_Eliminar_Nombre,Cajero_Eliminar_ER;
    Button Cajero_Eliminar_Atras,Cajero_Eliminar_Eliminar,Cajero_Eliminar_Buscar;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cajero_eliminar);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"db_cajero",null,1);

        Cajero_Eliminar_Codigo=(EditText)findViewById(R.id.ET_Cajero_Eliminar_Codigo);

        Cajero_Eliminar_Nombre=(TextView)findViewById(R.id.TV_Cajero_Eliminar_Nombre);
        Cajero_Eliminar_ER=(TextView)findViewById(R.id.TV_Cajero_Eliminar_ER);

        Cajero_Eliminar_Atras=(Button)findViewById(R.id.B_Cajero_Eliminar_Atras);
        Cajero_Eliminar_Buscar=(Button)findViewById(R.id.B_Cajero_Eliminar_Buscar);
        Cajero_Eliminar_Eliminar=(Button)findViewById(R.id.B_Cajero_Eliminar_Eliminar);

        Cajero_Eliminar_Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=conn.getReadableDatabase();
                String[] parametros={Cajero_Eliminar_Codigo.getText().toString()};
                String[]campos={Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_ESTADO_REGISTRO};
                try{
                    Cursor cursor=db.query(Utilidades.TABLA_CAJERO,campos,Utilidades.CAMPO_CODIGO+"=?",parametros,null,null,null);
                    cursor.moveToFirst();
                    Cajero_Eliminar_Nombre.setText(cursor.getString(0));
                    Cajero_Eliminar_ER.setText(cursor.getString(1));
                    cursor.close();

                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"El codigo no existe",Toast.LENGTH_SHORT).show();
                    Cajero_Eliminar_Codigo.setText("");
                }

            }
        });

        Cajero_Eliminar_Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=conn.getWritableDatabase();
                String[] parametros={Cajero_Eliminar_Codigo.getText().toString()};
                ContentValues values=new ContentValues();

                values.put(Utilidades.CAMPO_NOMBRE,Cajero_Eliminar_Nombre.getText().toString());
                values.put(Utilidades.CAMPO_ESTADO_REGISTRO,"*");

                db.update(Utilidades.TABLA_CAJERO,values,Utilidades.CAMPO_CODIGO+"=?",parametros);
                Toast.makeText(getApplicationContext(),"SE ELIMINO",Toast.LENGTH_LONG).show();
                db.close();
            }
        });


        Cajero_Eliminar_Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CajeroEliminarActivity.this,CajeroMenuActivity.class);
                startActivity(intent);
            }
        });

    }
}

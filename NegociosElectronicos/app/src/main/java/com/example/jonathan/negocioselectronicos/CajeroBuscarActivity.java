package com.example.jonathan.negocioselectronicos;

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
import com.example.jonathan.negocioselectronicos.utilidades.Utilidades;

public class CajeroBuscarActivity extends AppCompatActivity {
    EditText Cajero_Buscar_Codigo;
    Button Cajero_Buscar_Atras,Cajero_Buscar_Buscar,Cajero_Buscar_Listar;
    TextView Cajero_Buscar_Nombre,Cajero_Buscar_ER;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cajero_buscar);

       conn=new ConexionSQLiteHelper(getApplicationContext(),"db_cajero",null,1);


        Cajero_Buscar_Codigo=(EditText)findViewById(R.id.ET_Cajero_Buscar_Codigo);
        Cajero_Buscar_Atras=(Button)findViewById(R.id.B_Cajero_Buscar_Atras);
        Cajero_Buscar_Buscar=(Button)findViewById(R.id.B_Cajero_Buscar_Buscar);
        Cajero_Buscar_Listar=(Button)findViewById(R.id.B_Cajero_Buscar_Listar);


        Cajero_Buscar_Nombre=(TextView)findViewById(R.id.TV_Cajero_Buscar_Nombre);
        Cajero_Buscar_ER=(TextView)findViewById(R.id.TV_Cajero_Buscar_ER);



        Cajero_Buscar_Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CajeroBuscarActivity.this,CajeroMenuActivity.class);
                startActivity(intent);
            }
        });

        Cajero_Buscar_Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db=conn.getReadableDatabase();
                String[] parametros={Cajero_Buscar_Codigo.getText().toString()};
                String[]campos={Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_ESTADO_REGISTRO};


                try{
                    Cursor cursor=db.query(Utilidades.TABLA_CAJERO,campos,Utilidades.CAMPO_CODIGO+"=?",parametros,null,null,null);
                    cursor.moveToFirst();
                    Cajero_Buscar_Nombre.setText(cursor.getString(0));
                    Cajero_Buscar_ER.setText(cursor.getString(1));
                    cursor.close();

                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"El codigo no existe",Toast.LENGTH_SHORT).show();
                    Cajero_Buscar_Codigo.setText("");
                }
            }
        });

        Cajero_Buscar_Listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CajeroBuscarActivity.this,CajeroListarActivity.class);
                startActivity(intent);
            }
        });


    }



}

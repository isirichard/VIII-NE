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

import com.example.jonathan.negocioselectronicos.utilidades.Utilidades;
import com.example.jonathan.negocioselectronicos.utilidades.UtilidadesCliente;

public class ClienteBuscarActivity extends AppCompatActivity {
    EditText Cliente_Buscar_Codigo;
    Button Cliente_Buscar_Atras,Cliente_Buscar_Buscar,Cliente_Buscar_Listar;
    TextView Cliente_Buscar_Nombre,Cliente_Buscar_ER;
    ConexionSQLiteHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_buscar);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"db_cliente",null,1);


        Cliente_Buscar_Codigo=(EditText)findViewById(R.id.ET_Cliente_Buscar_Codigo);
        Cliente_Buscar_Atras=(Button)findViewById(R.id.B_Cliente_Buscar_Atras);
        Cliente_Buscar_Buscar=(Button)findViewById(R.id.B_Cliente_Buscar_Buscar);
        Cliente_Buscar_Listar=(Button)findViewById(R.id.B_Cliente_Buscar_Listar);

        Cliente_Buscar_Nombre=(TextView)findViewById(R.id.TV_Cliente_Buscar_Nombre);
        Cliente_Buscar_ER=(TextView)findViewById(R.id.TV_Cliente_Buscar_ER);

        Cliente_Buscar_Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ClienteBuscarActivity.this,ClienteMenuActivity.class);
                startActivity(intent);
            }
        });

        Cliente_Buscar_Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db=conn.getReadableDatabase();
                String[] parametros2={Cliente_Buscar_Codigo.getText().toString()};
                String[]campos={UtilidadesCliente.CAMPO_NOMBRE2, UtilidadesCliente.CAMPO_ESTADO_REGISTRO2};


                try{
                    Cursor cursor=db.query(UtilidadesCliente.TABLA_CLIENTE,campos,UtilidadesCliente.CAMPO_CODIGO2+"=?",parametros2,null,null,null);
                    cursor.moveToFirst();
                    Cliente_Buscar_Nombre.setText(cursor.getString(0));
                    Cliente_Buscar_ER.setText(cursor.getString(1));
                    cursor.close();

                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"El codigo no existe",Toast.LENGTH_SHORT).show();
                    Cliente_Buscar_Codigo.setText("");
                }
            }
        });
        Cliente_Buscar_Listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ClienteBuscarActivity.this,ClienteListarActivity.class);
                startActivity(intent);
            }
        });



    }
}

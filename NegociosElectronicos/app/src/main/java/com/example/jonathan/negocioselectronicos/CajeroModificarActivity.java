package com.example.jonathan.negocioselectronicos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonathan.negocioselectronicos.utilidades.Utilidades;

public class CajeroModificarActivity extends AppCompatActivity {
    EditText Cajero_Modificar_Codigo,Cajero_Modificar_Nombre;
    Spinner Cajero_Modifcar_ER;
    TextView Cajero_Modificar_ER1;
    Button Cajero_Modificar_Buscar,Cajero_Modificar_Atras,Cajero_Modificar_Modificar;
    ConexionSQLiteHelper conn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cajero_modificar);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"db_cajero",null,1);

        Cajero_Modificar_Codigo=(EditText)findViewById(R.id.ET_Cajero_Modificar_Codigo);

        Cajero_Modificar_Buscar=(Button)findViewById(R.id.B_Cajero_Modificar_Buscar);
        Cajero_Modificar_Atras=(Button)findViewById(R.id.B_Cajero_Modificar_Atras);
        Cajero_Modificar_Modificar=(Button)findViewById(R.id.B_Cajero_Modificar_Modificar);

        Cajero_Modificar_Nombre=(EditText)findViewById(R.id.ET_Cajero_Modificar_Nombre);
        Cajero_Modifcar_ER=(Spinner)findViewById(R.id.S_Cajero_Modificar_ER);
        Cajero_Modificar_ER1=(TextView)findViewById(R.id.TV_Cajero_Modificar_ER);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.combo_opciones,android.R.layout.simple_spinner_item);
        Cajero_Modifcar_ER.setAdapter(adapter);




        Cajero_Modificar_Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db=conn.getReadableDatabase();
                String[] parametros={Cajero_Modificar_Codigo.getText().toString()};
                String[]campos={Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_ESTADO_REGISTRO};


                try{
                    Cursor cursor=db.query(Utilidades.TABLA_CAJERO,campos,Utilidades.CAMPO_CODIGO+"=?",parametros,null,null,null);
                    cursor.moveToFirst();
                    Cajero_Modificar_Nombre.setText(cursor.getString(0));
                    Cajero_Modificar_ER1.setText(cursor.getString(1));
                    cursor.close();

                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"El codigo no existe",Toast.LENGTH_SHORT).show();
                    Cajero_Modificar_Codigo.setText("");
                    Cajero_Modificar_Nombre.setText("");
                    Cajero_Modificar_ER1.setText("");

                }

            }
        });

        Cajero_Modificar_Modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db=conn.getWritableDatabase();
                String[] parametros={Cajero_Modificar_Codigo.getText().toString()};
                ContentValues values=new ContentValues();
                values.put(Utilidades.CAMPO_NOMBRE,Cajero_Modificar_Nombre.getText().toString());
                values.put(Utilidades.CAMPO_ESTADO_REGISTRO,Cajero_Modifcar_ER.getSelectedItem().toString());

                db.update(Utilidades.TABLA_CAJERO,values,Utilidades.CAMPO_CODIGO+"=?",parametros);
                Toast.makeText(getApplicationContext(),"SE MODIFICO",Toast.LENGTH_LONG).show();
                db.close();
            }
        });

        Cajero_Modificar_Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CajeroModificarActivity.this,CajeroMenuActivity.class);
                startActivity(intent);
            }
        });



    }

}

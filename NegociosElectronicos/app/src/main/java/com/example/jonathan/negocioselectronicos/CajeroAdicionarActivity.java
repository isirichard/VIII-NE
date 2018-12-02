package com.example.jonathan.negocioselectronicos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jonathan.negocioselectronicos.utilidades.Utilidades;


public class CajeroAdicionarActivity extends AppCompatActivity {
    Spinner campoOpcionesER;
    EditText campoCodigo, campoNombre;
    Button B_Cancelar,B_Guardar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cajero_adicionar);

        B_Cancelar=(Button)findViewById(R.id.B_Cajero_Adicionar_Cancelar);
        B_Guardar=(Button)findViewById((R.id.B_Cajero_Adicionar_Guardar));
        campoCodigo=(EditText) findViewById(R.id.ET_Cajero_Adicionar_Codigo);
        campoNombre=(EditText) findViewById(R.id.ET_Cajero_Adicionar_Nombre);


        campoOpcionesER=(Spinner) findViewById(R.id.S_Cajero_Adicionar_ER);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.combo_opciones,android.R.layout.simple_spinner_item);

        campoOpcionesER.setAdapter(adapter);

        B_Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(CajeroAdicionarActivity.this,CajeroListViewActivity.class);
                startActivity(intent1);
            }
        });
        B_Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarCajero();
            }
        });

    }

    public void registrarCajero(){

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"db_cajero",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_CODIGO,campoCodigo.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_ESTADO_REGISTRO,campoOpcionesER.getSelectedItem().toString());


        Long idResultante=db.insert(Utilidades.TABLA_CAJERO,Utilidades.CAMPO_CODIGO,values);
        Toast.makeText(getApplicationContext(),"Id Registro:"+idResultante,Toast.LENGTH_SHORT).show();
        db.close();
    }

}

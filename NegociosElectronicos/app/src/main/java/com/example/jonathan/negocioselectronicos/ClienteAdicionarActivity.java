package com.example.jonathan.negocioselectronicos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jonathan.negocioselectronicos.utilidades.Utilidades;
import com.example.jonathan.negocioselectronicos.utilidades.UtilidadesCliente;

public class ClienteAdicionarActivity extends AppCompatActivity {
    Spinner campoOpcionesER;
    EditText campoCodigo, campoNombre;
    Button B_Cancelar,B_Guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_adicionar);

        B_Cancelar=(Button)findViewById(R.id.B_Cliente_Adicionar_Cancelar);
        B_Guardar=(Button)findViewById((R.id.B_Cliente_Adicionar_Guardar));
        campoCodigo=(EditText) findViewById(R.id.ET_Cliente_Adicionar_Codigo);
        campoNombre=(EditText) findViewById(R.id.ET_Cliente_Adicionar_Nombre);


        campoOpcionesER=(Spinner) findViewById(R.id.S_Cliente_Adicionar_ER);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.combo_opciones,android.R.layout.simple_spinner_item);

        campoOpcionesER.setAdapter(adapter);

        B_Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ClienteAdicionarActivity.this,ClienteMenuActivity.class);
                startActivity(intent1);
            }
        });
        B_Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarCliente();
            }
        });
    }

    public void registrarCliente(){

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"db_cliente",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(UtilidadesCliente.CAMPO_CODIGO2,campoCodigo.getText().toString());
        values.put(UtilidadesCliente.CAMPO_NOMBRE2,campoNombre.getText().toString());
        values.put(UtilidadesCliente.CAMPO_ESTADO_REGISTRO2,campoOpcionesER.getSelectedItem().toString());


        Long idResultante=db.insert(UtilidadesCliente.TABLA_CLIENTE,UtilidadesCliente.CAMPO_CODIGO2,values);
        Toast.makeText(getApplicationContext(),"Id Registro:"+idResultante,Toast.LENGTH_SHORT).show();
        db.close();
    }
}

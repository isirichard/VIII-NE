package com.example.jonathan.negocioselectronicos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CajeroMenuActivity extends AppCompatActivity {
    Button B_Adicionar,B_Atras,B_Buscar,B_Modificar,B_Eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cajero_menu);

        B_Adicionar=(Button)findViewById(R.id.B_CajeroMenu_Adiccionar);
        B_Atras=(Button)findViewById(R.id.B_CajeroMenu_Atras);
        B_Buscar=(Button) findViewById(R.id.B_CajeroMenu_Buscar);
        B_Modificar=(Button)findViewById(R.id.B_CajeroMenu_Modificar);
        B_Eliminar=(Button)findViewById(R.id.B_CajeroMenu_Eliminar);


        B_Adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(CajeroMenuActivity.this,CajeroAdicionarActivity.class);
                startActivity(intent1);
            }
        });

        B_Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(CajeroMenuActivity.this,MainActivity.class);
                startActivity(intent2);

            }
        });
        B_Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3=new Intent(CajeroMenuActivity.this,CajeroBuscarActivity.class);
                startActivity(intent3);
            }
        });
        B_Modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4=new Intent(CajeroMenuActivity.this,CajeroModificarActivity.class);
                startActivity(intent4);
            }
        });

        B_Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5=new Intent(CajeroMenuActivity.this,CajeroEliminarActivity.class);
                startActivity(intent5);
            }
        });



    }





}

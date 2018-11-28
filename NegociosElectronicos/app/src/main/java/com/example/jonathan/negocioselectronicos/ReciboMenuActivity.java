package com.example.jonathan.negocioselectronicos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReciboMenuActivity extends AppCompatActivity {
    Button B_Adicionar,B_Atras,B_Buscar,B_Modificar,B_Eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo_menu);

        B_Adicionar=(Button)findViewById(R.id.B_ReciboMenu_Adiccionar);
        B_Atras=(Button)findViewById(R.id.B_ReciboMenu_Atras);
        B_Buscar=(Button) findViewById(R.id.B_ReciboMenu_Buscar);
        B_Modificar=(Button)findViewById(R.id.B_ReciboMenu_Modificar);
        B_Eliminar=(Button)findViewById(R.id.B_ReciboMenu_Eliminar);

        B_Adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ReciboMenuActivity.this,ReciboAdicionarActivity.class);
                startActivity(intent1);
            }
        });
        B_Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ReciboMenuActivity.this,ReciboBuscarActivity.class);
                startActivity(intent1);
            }
        });
        B_Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ReciboMenuActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });
        B_Modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ReciboMenuActivity.this,ReciboModificarActivity.class);
                startActivity(intent1);
            }
        });
        B_Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ReciboMenuActivity.this,ReciboEliminarActivity.class);
                startActivity(intent1);
            }
        });



    }
}

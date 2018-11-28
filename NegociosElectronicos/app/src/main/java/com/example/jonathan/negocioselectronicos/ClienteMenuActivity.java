package com.example.jonathan.negocioselectronicos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jonathan.negocioselectronicos.entidades.Cliente;

public class ClienteMenuActivity extends AppCompatActivity {
    Button B_Adicionar,B_Atras,B_Buscar,B_Modificar,B_Eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_menu);

        B_Adicionar=(Button)findViewById(R.id.B_ClienteMenu_Adiccionar);
        B_Atras=(Button)findViewById(R.id.B_ClienteMenu_Atras);
        B_Buscar=(Button) findViewById(R.id.B_ClienteMenu_Buscar);
        B_Modificar=(Button)findViewById(R.id.B_ClienteMenu_Modificar);
        B_Eliminar=(Button)findViewById(R.id.B_ClienteMenu_Eliminar);


        B_Adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ClienteMenuActivity.this,ClienteAdicionarActivity.class);
                startActivity(intent1);
            }
        });

        B_Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(ClienteMenuActivity.this,MainActivity.class);
                startActivity(intent2);

            }
        });
        B_Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3=new Intent(ClienteMenuActivity.this,ClienteBuscarActivity.class);
                startActivity(intent3);
            }
        });

        B_Modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4=new Intent(ClienteMenuActivity.this,ClienteModificarActivity.class);
                startActivity(intent4);
            }
        });


        B_Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5=new Intent(ClienteMenuActivity.this,ClienteEliminarActivity.class);
                startActivity(intent5);
            }
        });

    }
}

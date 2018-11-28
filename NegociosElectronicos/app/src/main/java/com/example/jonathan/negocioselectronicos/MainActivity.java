package com.example.jonathan.negocioselectronicos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button B_Cajero,B_Cliente,B_Recibo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        B_Cajero=(Button)findViewById(R.id.B_Cajero);
        B_Cliente=(Button)findViewById(R.id.B_Cliente);
        B_Recibo=(Button)findViewById(R.id.B_Recibo);


        B_Cajero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1=new Intent(MainActivity.this,CajeroMenuActivity.class);
                startActivity(intent1);
            }
        });
        B_Cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(MainActivity.this,ClienteMenuActivity.class);
                startActivity(intent2);
            }
        });
        B_Recibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3=new Intent(MainActivity.this,ReciboMenuActivity.class);
                startActivity(intent3);
            }
        });







    }

}

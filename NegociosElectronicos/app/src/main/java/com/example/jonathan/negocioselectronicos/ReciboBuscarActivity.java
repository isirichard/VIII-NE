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

import com.example.jonathan.negocioselectronicos.utilidades.UtilidadesCliente;
import com.example.jonathan.negocioselectronicos.utilidades.UtilidadesRecibo;

public class ReciboBuscarActivity extends AppCompatActivity {
    EditText Recibo_Codigo;
    Button Recibo_Buscar_Atras,Recibo_Buscar_Buscar,Recibo_Buscar_Listar;
    TextView Recibo_Cajero,Recibo_Cliente,Recibo_Monto,Recibo_ER;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo_buscar);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"db_recibo",null,1);

        Recibo_Codigo=(EditText)findViewById(R.id.ET_Recibo_Buscar_Codigo);
        Recibo_Cajero=(TextView)findViewById(R.id.TV_ReciboCajero_Buscar);
        Recibo_Cliente=(TextView)findViewById(R.id.TV_ReciboCliente_Buscar);
        Recibo_Monto=(TextView)findViewById(R.id.TV_Recibo_Buscar_Monto);
        Recibo_ER=(TextView)findViewById(R.id.TV_Recibo_Buscar_ER);

        Recibo_Buscar_Buscar=(Button)findViewById(R.id.B_Recibo_Buscar_Buscar);
        Recibo_Buscar_Atras=(Button)findViewById(R.id.B_Recibo_Buscar_Atras);


        Recibo_Buscar_Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ReciboBuscarActivity.this,ReciboListViewActivity.class);
                startActivity(intent1);
            }
        });
        Recibo_Buscar_Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db=conn.getReadableDatabase();
                String[] parametros3={Recibo_Codigo.getText().toString()};
                String[]campos={UtilidadesRecibo.CAMPO_CODIGO_CAJERO, UtilidadesRecibo.CAMPO_CODIGO_CLIENTE,UtilidadesRecibo.CAMPO_MONTO,UtilidadesRecibo.CAMPO_ESTADO_REGISTRO3};

                try{
                    Cursor cursor=db.query(UtilidadesRecibo.TABLA_RECIBO,campos,UtilidadesRecibo.CAMPO_CODIGO_RECIBO+"=?",parametros3,null,null,null);
                    cursor.moveToFirst();
                    Recibo_Cajero.setText(cursor.getString(0));
                    Recibo_Cliente.setText(cursor.getString(1));
                    Recibo_Monto.setText(cursor.getString(2));
                    Recibo_ER.setText(cursor.getString(3));
                    cursor.close();

                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"El codigo no existe",Toast.LENGTH_SHORT).show();
                    Recibo_Codigo.setText("");
                }
            }
        });
    }
}

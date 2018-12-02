package com.example.jonathan.negocioselectronicos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonathan.negocioselectronicos.entidades.Cajero;
import com.example.jonathan.negocioselectronicos.entidades.Cliente;
import com.example.jonathan.negocioselectronicos.entidades.Recibo;
import com.example.jonathan.negocioselectronicos.utilidades.Utilidades;
import com.example.jonathan.negocioselectronicos.utilidades.UtilidadesCliente;
import com.example.jonathan.negocioselectronicos.utilidades.UtilidadesRecibo;

import java.util.ArrayList;

public class ReciboModificar2 extends AppCompatActivity {
    EditText campoMonto;
    Spinner campoCodigoCajero,campoCodigoCliente;
    TextView TV_Codigo_Recibo, TV_ER;
    Button B_Modificar,B_Cancelar;

    ArrayList<String> listaCajeros;
    ArrayList<Cajero> cajerosList;

    ArrayList<String> listaClientes;
    ArrayList<Cliente> clientesList;

    ConexionSQLiteHelper conn,conn1,conn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo_modificar2);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"db_cajero",null,1);
        conn1=new ConexionSQLiteHelper(getApplicationContext(),"db_cliente",null,1);
        conn2=new ConexionSQLiteHelper(getApplicationContext(),"db_recibo",null,1);

        TV_Codigo_Recibo=(TextView)findViewById(R.id.TV_Recibo_Modificar_Codigo2);
        TV_ER=(TextView)findViewById(R.id.TV_Recibo_Modificar2_ER);

        campoMonto=(EditText)findViewById(R.id.ET_Recibo_Modificar2_Monto);

        campoCodigoCajero=(Spinner)findViewById(R.id.S_ReciboCajero_Modificar2);
        campoCodigoCliente=(Spinner)findViewById(R.id.S_ReciboCliente_Modificar2);

        B_Cancelar=(Button)findViewById(R.id.B_Recibo_Modificar2_Cancelar);
        B_Modificar=(Button)findViewById(R.id.B_Recibo_Modificar2_Modificar);

        consultarListaCajeros();
        consultarListaClientes();

        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaCajeros);
        campoCodigoCajero.setAdapter(adaptador);

        ArrayAdapter<CharSequence>adptador2=new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaClientes);
        campoCodigoCliente.setAdapter(adptador2);

        Bundle objetoEnviado=getIntent().getExtras();
        Recibo recibo=null;

        if(objetoEnviado!=null){
            recibo= (Recibo) objetoEnviado.getSerializable("recibo2");
            TV_Codigo_Recibo.setText(recibo.getCodigoRecibo().toString());

            SQLiteDatabase db=conn2.getReadableDatabase();
            String[] parametros={recibo.getCodigoRecibo().toString()};

            String[]campos={UtilidadesRecibo.CAMPO_MONTO,UtilidadesRecibo.CAMPO_ESTADO_REGISTRO3};

            try{
                Cursor cursor=db.query(UtilidadesRecibo.TABLA_RECIBO,campos,UtilidadesRecibo.CAMPO_CODIGO_RECIBO+"=?",parametros,null,null,null);
                cursor.moveToFirst();
                campoMonto.setText(cursor.getString(0));
                TV_ER.setText(cursor.getString(1));
                cursor.close();
            }catch (Exception e) {
                Toast.makeText(getApplicationContext(),"El codigo no existe",Toast.LENGTH_SHORT).show();
            }
        }

        B_Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ReciboModificar2.this,ReciboListViewActivity.class);
                startActivity(intent);

            }
        });

        B_Modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=conn2.getWritableDatabase();
                String[] parametros_8={TV_Codigo_Recibo.getText().toString()};
                ContentValues values=new ContentValues();
                values.put(UtilidadesRecibo.CAMPO_CODIGO_CAJERO,campoCodigoCajero.getSelectedItem().toString());
                values.put(UtilidadesRecibo.CAMPO_CODIGO_CLIENTE,campoCodigoCliente.getSelectedItem().toString());
                values.put(UtilidadesRecibo.CAMPO_MONTO,campoMonto.getText().toString());
                values.put(UtilidadesRecibo.CAMPO_ESTADO_REGISTRO3,TV_ER.getText().toString());

                db.update(UtilidadesRecibo.TABLA_RECIBO,values,UtilidadesRecibo.CAMPO_CODIGO_RECIBO+"=?",parametros_8);
                Toast.makeText(getApplicationContext(),"SE MODIFICO",Toast.LENGTH_LONG).show();
                db.close();
                Intent intent=new Intent(ReciboModificar2.this,ReciboListViewActivity.class);
                startActivity(intent);
            }
        });

    }


    private void consultarListaCajeros() {
        SQLiteDatabase db=conn.getReadableDatabase();
        Cajero cajero=null;
        cajerosList=new ArrayList<Cajero>();

        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_CAJERO,null);

        while (cursor.moveToNext()){
            cajero=new Cajero();
            cajero.setCodigo(cursor.getInt(0));
            cajero.setNombre(cursor.getString(1));
            cajero.setEstadoRegistro(cursor.getString(2));

            Log.i("CodigoCajero", cajero.getCodigo().toString());
            Log.i("NombreCajero", cajero.getNombre().toString());
            Log.i("ERCajero", cajero.getEstadoRegistro().toString());

            cajerosList.add(cajero);
        }
        obtenerListaCajeros();
    }

    private void obtenerListaCajeros() {
        listaCajeros=new ArrayList<String>();
        //listaCajeros.add("Seleccione Cajero");

        for (int i=0; i<cajerosList.size();i++){
            listaCajeros.add(cajerosList.get(i).getCodigo()+"-"+cajerosList.get(i).getNombre());


        }
    }
    private void consultarListaClientes() {
        SQLiteDatabase db=conn1.getReadableDatabase();
        Cliente cliente=null;
        clientesList=new ArrayList<Cliente>();

        Cursor cursor=db.rawQuery("SELECT * FROM "+UtilidadesCliente.TABLA_CLIENTE,null);

        while (cursor.moveToNext()){
            cliente=new Cliente();
            cliente.setCodigo(cursor.getInt(0));
            cliente.setNombre(cursor.getString(1));
            cliente.setEstadoRegistro(cursor.getString(2));

            Log.i("CodigoCliente", cliente.getCodigo().toString());
            Log.i("NombreCliente", cliente.getNombre().toString());
            Log.i("ERCliente", cliente.getEstadoRegistro().toString());

            clientesList.add(cliente);
        }
        obtenerListaClientes();
    }
    private void obtenerListaClientes() {
        listaClientes=new ArrayList<String>();
        //listaClientes.add("Seleccione cliente");

        for (int i=0; i<clientesList.size();i++) {
            listaClientes.add(clientesList.get(i).getCodigo() + "-" +clientesList.get(i).getNombre());

        }
    }
}

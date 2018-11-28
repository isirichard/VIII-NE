package com.example.jonathan.negocioselectronicos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jonathan.negocioselectronicos.entidades.Cajero;
import com.example.jonathan.negocioselectronicos.entidades.Cliente;
import com.example.jonathan.negocioselectronicos.entidades.Recibo;
import com.example.jonathan.negocioselectronicos.utilidades.Utilidades;
import com.example.jonathan.negocioselectronicos.utilidades.UtilidadesCliente;
import com.example.jonathan.negocioselectronicos.utilidades.UtilidadesRecibo;

import java.util.ArrayList;

public class ReciboAdicionarActivity extends AppCompatActivity {

    EditText campoCodigo,campoMonto;
    Spinner campoCodigoCajero,campoCodigoCliente,campoOpcionesER;
    Button B_Cancelar,B_Guardar;

    ArrayList<String> listaCajeros;
    ArrayList<Cajero> cajerosList;

    ArrayList<String> listaClientes;
    ArrayList<Cliente> clientesList;

    ConexionSQLiteHelper conn,conn1,conn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo_adicionar);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"db_cajero",null,1);
        conn1=new ConexionSQLiteHelper(getApplicationContext(),"db_cliente",null,1);
        conn2=new ConexionSQLiteHelper(getApplicationContext(),"db_recibo",null,1);

        campoCodigo=(EditText)findViewById(R.id.ET_Recibo_Adicionar_Codigo);
        campoMonto=(EditText)findViewById(R.id.ET_Recibo_Adicionar_Monto);

        campoCodigoCajero=(Spinner)findViewById(R.id.S_ReciboCajero_Adicionar_ER);
        campoCodigoCliente=(Spinner)findViewById(R.id.S_ReciboCliente_Adicionar_ER);

        B_Guardar=(Button)findViewById(R.id.B_Recibo_Adicionar_Guardar);
        B_Cancelar=(Button)findViewById(R.id.B_Recibo_Adicionar_Cancelar);


        campoOpcionesER=(Spinner) findViewById(R.id.S_Recibo_Adicionar_ER);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.combo_opciones,android.R.layout.simple_spinner_item);
        campoOpcionesER.setAdapter(adapter);


        consultarListaCajeros();
        consultarListaClientes();

        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaCajeros);
        campoCodigoCajero.setAdapter(adaptador);

        ArrayAdapter<CharSequence>adptador2=new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaClientes);
        campoCodigoCliente.setAdapter(adptador2);

        B_Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarRecibo();
            }
        });
        B_Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ReciboAdicionarActivity.this,ReciboMenuActivity.class);
                startActivity(intent);

            }
        });


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
        listaClientes.add("Seleccione cliente");

        for (int i=0; i<clientesList.size();i++) {
            listaClientes.add(clientesList.get(i).getCodigo() + "-" + clientesList.get(i).getNombre() + "-" + clientesList.get(i).getEstadoRegistro());
        }

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
            listaCajeros.add("Seleccione Cajero");

            for (int i=0; i<cajerosList.size();i++){
                listaCajeros.add(cajerosList.get(i).getCodigo()+"-"+cajerosList.get(i).getNombre()+"-"+cajerosList.get(i).getEstadoRegistro());

            }
    }

     private void registrarRecibo(){
        SQLiteDatabase db=conn2.getWritableDatabase();
        ContentValues values=new ContentValues();
         ContentValues values2=new ContentValues();

        values.put(UtilidadesRecibo.CAMPO_CODIGO_RECIBO,campoCodigo.getText().toString());
        values.put(UtilidadesRecibo.CAMPO_MONTO,campoMonto.getText().toString());
        values.put(UtilidadesRecibo.CAMPO_ESTADO_REGISTRO3,campoOpcionesER.getSelectedItem().toString());

        int idComboCajero=(int) campoCodigoCajero.getSelectedItemId();
        int idComboCliente=(int) campoCodigoCliente.getSelectedItemId();

        if(idComboCajero!=0 && idComboCliente!=0){
            Log.i("TAMAÑO_CAJERO",cajerosList.size()+"");
            Log.i("id Combo Cajero",idComboCajero+"");
            Log.i("id Combo Cajero - 1",(idComboCajero-1)+"");
            int codigoCajero=cajerosList.get(idComboCajero-1).getCodigo();
            Log.i("id CAJERO",codigoCajero+"");

            values.put(UtilidadesRecibo.CAMPO_CODIGO_CAJERO,codigoCajero);

            Log.i("TAMAÑO_CLIENTE",clientesList.size()+"");
            Log.i("id ComboCL",idComboCliente+"");
            Log.i("id ComboCL - 1",(idComboCliente-1)+"");
            int idCliente=clientesList.get(idComboCliente-1).getCodigo();
            Log.i("id CLIENTE",idCliente+"");

            values.put(UtilidadesRecibo.CAMPO_CODIGO_CLIENTE,idCliente);

            Long idResultante=db.insert(UtilidadesRecibo.TABLA_RECIBO,UtilidadesRecibo.CAMPO_CODIGO_RECIBO,values);

            Toast.makeText(getApplicationContext(),"Id Registro:"+idResultante,Toast.LENGTH_SHORT).show();
            db.close();

         }else{
             Toast.makeText(getApplicationContext(),"Debe de seleccionar datos",Toast.LENGTH_SHORT).show();
         }

    }

}

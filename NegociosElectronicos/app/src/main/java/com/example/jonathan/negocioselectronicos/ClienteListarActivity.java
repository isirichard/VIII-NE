package com.example.jonathan.negocioselectronicos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jonathan.negocioselectronicos.entidades.Cliente;
import com.example.jonathan.negocioselectronicos.utilidades.UtilidadesCliente;

import java.util.ArrayList;

public class ClienteListarActivity extends AppCompatActivity {
    ListView ClienteLV;

    ArrayList<String> listaClienteInformacion;
    ArrayList<Cliente> listaCliente;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_listar);
        ClienteLV=(ListView)findViewById(R.id.LV_Cliente);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"db_cliente",null,1);
        consultarListaClientes();

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaClienteInformacion);
        ClienteLV.setAdapter(adaptador);
    }

    private void consultarListaClientes() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Cliente cliente=null;
        listaCliente=new ArrayList<Cliente>();
        Cursor cursor=db.rawQuery("SELECT * FROM "+UtilidadesCliente.TABLA_CLIENTE,null);

        while (cursor.moveToNext()){
            cliente=new Cliente();
            cliente.setCodigo(cursor.getInt(0));
            cliente.setNombre(cursor.getString(1));
            cliente.setEstadoRegistro(cursor.getString(2));

            listaCliente.add(cliente);
        }

        obtenerLista();
    }

    private void obtenerLista() {
        listaClienteInformacion=new ArrayList<String>();

        for(int i=0;i<listaCliente.size();i++){
            listaClienteInformacion.add(listaCliente.get(i).getCodigo()+" - - "+listaCliente.get(i).getNombre()+" - - "+listaCliente.get(i).getEstadoRegistro());

        }
}
}

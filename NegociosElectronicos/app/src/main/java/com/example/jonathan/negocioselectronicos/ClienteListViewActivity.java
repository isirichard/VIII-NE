package com.example.jonathan.negocioselectronicos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jonathan.negocioselectronicos.entidades.Cliente;
import com.example.jonathan.negocioselectronicos.utilidades.UtilidadesCliente;

import java.util.ArrayList;

public class ClienteListViewActivity extends AppCompatActivity {
    ListView listViewCliente;
    Button Cliente_LV_Adicionar,Cliente_LV_Eliminar,Cliente_LV_Buscar,Cliente_LV_Modificar,Cliente_LV_Atras,Cliente_LV_Activar,Cliente_LV_Inactivar;
    ArrayList<String> listaClienteInformacion;
    ArrayList<Cliente> listaCliente;
    ConexionSQLiteHelper conn;

    Cliente cliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_list_view);

        listViewCliente=(ListView)findViewById(R.id.LV_Mostrar_Cliente);

        Cliente_LV_Atras=(Button)findViewById(R.id.B_LV_CLIENTE_ATRAS);
        Cliente_LV_Modificar=(Button)findViewById(R.id.B_LV_CLIENTE_MODIFICAR);
        Cliente_LV_Adicionar=(Button)findViewById(R.id.B_LV_CLIENTE_ADICIONAR);
        Cliente_LV_Eliminar=(Button)findViewById(R.id.B_LV_CLIENTE_ELIMINAR);
        Cliente_LV_Activar=(Button)findViewById(R.id.B_LV_CLIENTE_ACTIVAR);
        Cliente_LV_Inactivar=(Button)findViewById(R.id.B_LV_CLIENTE_INACTIVAR);
        Cliente_LV_Buscar=(Button)findViewById(R.id.B_LV_CLIENTE_BUSCAR);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"db_cliente",null,1);
        consultarListaClientes();
        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaClienteInformacion);

        listViewCliente.setAdapter(adaptador);

        listViewCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                cliente=listaCliente.get(pos);

                Cliente_LV_Modificar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(ClienteListViewActivity.this,ClienteModificar2.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("cliente2",cliente);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"enviaste",Toast.LENGTH_LONG).show();

                    }
                });

                Cliente_LV_Eliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(ClienteListViewActivity.this,ClienteEliminar2.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("cliente2",cliente);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"enviaste",Toast.LENGTH_LONG).show();
                    }
                });
                Cliente_LV_Activar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(ClienteListViewActivity.this,ClienteActivar.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("cliente2",cliente);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"enviaste",Toast.LENGTH_LONG).show();
                    }
                });
                Cliente_LV_Inactivar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(ClienteListViewActivity.this,ClienteInactivar.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("cliente2",cliente);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"enviaste",Toast.LENGTH_LONG).show();
                    }
                });


            }
        });

        Cliente_LV_Adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ClienteListViewActivity.this,ClienteAdicionarActivity.class);
                startActivity(intent);

            }
        });
        Cliente_LV_Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ClienteListViewActivity.this,ClienteBuscarActivity.class);
                startActivity(intent);
            }
        });

        Cliente_LV_Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ClienteListViewActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });






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

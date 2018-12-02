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

import com.example.jonathan.negocioselectronicos.entidades.Cajero;
import com.example.jonathan.negocioselectronicos.entidades.Cliente;
import com.example.jonathan.negocioselectronicos.utilidades.Utilidades;

import java.util.ArrayList;

public class CajeroListViewActivity extends AppCompatActivity {
    ListView listViewCajero;
    Button Cajero_LV_Adicionar,Cajero_LV_Eliminar,Cajero_LV_Buscar,Cajero_LV_Modificar,Cajero_LV_Atras,Cajero_LV_Activar,Cajero_LV_Inactivar;

    ArrayList<String> listaCajerosInformacion;
    ArrayList<Cajero> listaCajeros;
    ConexionSQLiteHelper conn;

    Cajero cajero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cajero_list_view);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"db_cajero",null,1);

        listViewCajero=(ListView)findViewById(R.id.LV_Mostrar_Cajero);

        Cajero_LV_Atras=(Button)findViewById(R.id.B_LV_CAJERO_ATRAS);
        Cajero_LV_Modificar=(Button)findViewById(R.id.B_LV_CAJERO_MODIFICAR);
        Cajero_LV_Adicionar=(Button)findViewById(R.id.B_LV_CAJERO_ADICIONAR);
        Cajero_LV_Eliminar=(Button)findViewById(R.id.B_LV_CAJERO_ELIMINAR);
        Cajero_LV_Activar=(Button)findViewById(R.id.B_LV_CAJERO_ACTIVAR);
        Cajero_LV_Inactivar=(Button)findViewById(R.id.B_LV_CAJERO_INACTIVAR);
        Cajero_LV_Buscar=(Button)findViewById(R.id.B_LV_CAJERO_BUSCAR);

        consultarListaCajeros();
        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaCajerosInformacion);
        listViewCajero.setAdapter(adaptador);

        listViewCajero.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                cajero=listaCajeros.get(pos);

                Cajero_LV_Modificar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(CajeroListViewActivity.this,CajeroModificar2.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("cajero2",cajero);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"enviaste",Toast.LENGTH_LONG).show();
                    }
                });

                Cajero_LV_Eliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(CajeroListViewActivity.this,CajeroEliminar2.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("cajero2",cajero);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"enviaste",Toast.LENGTH_LONG).show();
                    }
                });
                Cajero_LV_Activar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(CajeroListViewActivity.this,CajeroActivar.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("cajero2",cajero);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"enviaste",Toast.LENGTH_LONG).show();
                    }
                });
                Cajero_LV_Inactivar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(CajeroListViewActivity.this,CajeroInactivar.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("cajero2",cajero);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"enviaste",Toast.LENGTH_LONG).show();
                    }
                });


            }
        });
        Cajero_LV_Adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CajeroListViewActivity.this,CajeroAdicionarActivity.class);
                startActivity(intent);

            }
        });
        Cajero_LV_Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CajeroListViewActivity.this,CajeroBuscarActivity.class);
                startActivity(intent);
            }
        });

        Cajero_LV_Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CajeroListViewActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void consultarListaCajeros() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Cajero cajero=null;
        listaCajeros=new ArrayList<Cajero>();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_CAJERO,null);

        while (cursor.moveToNext()){
            cajero=new Cajero();
            cajero.setCodigo(cursor.getInt(0));
            cajero.setNombre(cursor.getString(1));
            cajero.setEstadoRegistro(cursor.getString(2));
            listaCajeros.add(cajero);

        }
        obtenerLista();
    }
    private void obtenerLista() {
        listaCajerosInformacion=new ArrayList<String>();

        for(int i=0;i<listaCajeros.size();i++){
            listaCajerosInformacion.add(listaCajeros.get(i).getCodigo()+" - - "+listaCajeros.get(i).getNombre()+" - - "+listaCajeros.get(i).getEstadoRegistro());

        }
    }
}

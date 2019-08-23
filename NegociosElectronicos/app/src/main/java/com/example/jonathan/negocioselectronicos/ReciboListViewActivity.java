package com.example.jonathan.negocioselectronicos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jonathan.negocioselectronicos.entidades.Recibo;
import com.example.jonathan.negocioselectronicos.utilidades.Utilidades;
import com.example.jonathan.negocioselectronicos.utilidades.UtilidadesCliente;
import com.example.jonathan.negocioselectronicos.utilidades.UtilidadesRecibo;

import java.util.ArrayList;

public class ReciboListViewActivity extends AppCompatActivity {
    ListView listViewRecibo;
    Button Recibo_LV_Adicionar,Recibo_LV_Eliminar,Recibo_LV_Buscar,Recibo_LV_Modificar,Recibo_LV_Atras,Recibo_LV_Activar,Recibo_LV_Inactivar;

    ArrayList<String> listaInformacion1;
    ArrayList<Recibo> listaRecibo;
    ConexionSQLiteHelper conn,conn1,conn2;

    Recibo recibo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo_list_view);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"db_recibo",null,1);
        conn1=new ConexionSQLiteHelper(getApplicationContext(),"db_cajero",null,1);
        conn2=new ConexionSQLiteHelper(getApplicationContext(),"db_cliente",null,1);


        listViewRecibo=(ListView)findViewById(R.id.LV_Mostrar_Recibo);
        Recibo_LV_Modificar=(Button)findViewById(R.id.B_LV_RECIBO_MODIFICAR);

        Recibo_LV_Adicionar=(Button)findViewById(R.id.B_LV_RECIBO_ADICIONAR2);
        Recibo_LV_Eliminar=(Button)findViewById(R.id.B_LV_RECIBO_ELIMINAR);
        Recibo_LV_Buscar=(Button)findViewById(R.id.B_LV_RECIBO_BUSCAR);
        Recibo_LV_Atras=(Button)findViewById(R.id.B_LV_RECIBO_ATRAS2);

        Recibo_LV_Activar=(Button)findViewById(R.id.B_LV_RECIBO_ACTIVAR);
        Recibo_LV_Inactivar=(Button)findViewById(R.id.B_LV_RECIBO_INACTIVAR);

        consultarListaRecibo();

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion1);
        listViewRecibo.setAdapter(adaptador);



        listViewRecibo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                recibo=listaRecibo.get(pos);

                Recibo_LV_Modificar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(ReciboListViewActivity.this,ReciboModificar2.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("recibo2",recibo);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"enviaste",Toast.LENGTH_LONG).show();
                    }
                });
                Recibo_LV_Eliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(ReciboListViewActivity.this,ReciboEliminar2.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("recibo2",recibo);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"enviaste",Toast.LENGTH_LONG).show();
                    }
                });
                Recibo_LV_Activar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(ReciboListViewActivity.this,ReciboActivar.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("recibo2",recibo);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"enviaste",Toast.LENGTH_LONG).show();
                    }
                });
                Recibo_LV_Inactivar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(ReciboListViewActivity.this,ReciboInactivar.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("recibo2",recibo);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"enviaste",Toast.LENGTH_LONG).show();

                    }
                });

            }
        });

        Recibo_LV_Adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ReciboListViewActivity.this,ReciboAdicionarActivity.class);
                startActivity(intent);
            }
        });
        Recibo_LV_Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ReciboListViewActivity.this,ReciboBuscarActivity.class);
                startActivity(intent);
            }
        });
        Recibo_LV_Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ReciboListViewActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void consultarListaRecibo() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Recibo recibo=null;
        listaRecibo=new ArrayList<Recibo>();
        Cursor cursor=db.rawQuery("SELECT * FROM "+UtilidadesRecibo.TABLA_RECIBO,null);

        while (cursor.moveToNext()){
            recibo=new Recibo();
            recibo.setCodigoRecibo(cursor.getInt(0));
            recibo.setCodigoCajero(cursor.getInt(1));
            recibo.setCodigoCliente(cursor.getInt(2));
            recibo.setMonto(cursor.getString(3));
            recibo.setEstadoRegistro(cursor.getString(4));
            listaRecibo.add(recibo);
        }
        obtenerLista();
    }
    private void obtenerLista() {
        listaInformacion1=new ArrayList<String>();

        for (int i=0; i<listaRecibo.size();i++){
            listaInformacion1.add(listaRecibo.get(i).getCodigoRecibo()+"  -  "+obtenerCajero(listaRecibo.get(i).getCodigoCajero())
                    +"  -  "+obtenerCliente(listaRecibo.get(i).getCodigoCliente())
                    +"  -  "+listaRecibo.get(i).getMonto()
                    +"  -  "+listaRecibo.get(i).getEstadoRegistro());
        }
    }
    private String obtenerCajero(Integer idCajero){


        SQLiteDatabase db=conn1.getReadableDatabase();
        String[] parametros_1={idCajero.toString()};
        String[] campoNombre={Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_ESTADO_REGISTRO};
        Cursor cursor =db.query(Utilidades.TABLA_CAJERO,campoNombre,Utilidades.CAMPO_CODIGO+"=?",parametros_1,null,null,null);
        cursor.moveToFirst();
        String tempo=cursor.getString(0);
        cursor.close();
        return tempo;
    }
    private String obtenerCliente(Integer idCliente){

        SQLiteDatabase db=conn2.getReadableDatabase();
        String[] parametros_2={idCliente.toString()};
        String[] campoNombre_2={UtilidadesCliente.CAMPO_NOMBRE2,UtilidadesCliente.CAMPO_ESTADO_REGISTRO2};
        Cursor cursor =db.query(UtilidadesCliente.TABLA_CLIENTE,campoNombre_2,UtilidadesCliente.CAMPO_CODIGO2+"=?",parametros_2,null,null,null);
        cursor.moveToFirst();
        String tempo=cursor.getString(0);
        cursor.close();
        return tempo;
    }
}

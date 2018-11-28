package com.example.jonathan.negocioselectronicos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jonathan.negocioselectronicos.entidades.Cliente;
import com.example.jonathan.negocioselectronicos.entidades.Recibo;
import com.example.jonathan.negocioselectronicos.utilidades.UtilidadesCliente;
import com.example.jonathan.negocioselectronicos.utilidades.UtilidadesRecibo;

import java.util.ArrayList;

public class ReciboListarActivity extends AppCompatActivity {
    ListView LV_Recibo;
    ArrayList<String> listaInformacion;
    ArrayList<Recibo> listaRecibo;
    ConexionSQLiteHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo_listar);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"db_recibo",null,1);
        LV_Recibo=(ListView)findViewById(R.id.LV_Recibo);
        consultarListaRecibo();

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        LV_Recibo.setAdapter(adaptador);

        LV_Recibo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Recibo recibo=listaRecibo.get(pos);

                Intent intent=new Intent(ReciboListarActivity.this,ReciboDetalleActivity.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("recibo",recibo);

                intent.putExtras(bundle);
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
        listaInformacion=new ArrayList<String>();

        for (int i=0; i<listaRecibo.size();i++){
            listaInformacion.add(listaRecibo.get(i).getCodigoRecibo()+" - "
                    +listaRecibo.get(i).getEstadoRegistro());
        }
    }
}

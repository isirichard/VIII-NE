package com.example.jonathan.negocioselectronicos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonathan.negocioselectronicos.entidades.Recibo;
import com.example.jonathan.negocioselectronicos.utilidades.Utilidades;
import com.example.jonathan.negocioselectronicos.utilidades.UtilidadesCliente;

public class ReciboDetalleActivity extends AppCompatActivity {
    ConexionSQLiteHelper conn,conn1,conn2;
    TextView reciboCodigo, reciboMonto, reciboER;
    TextView cajeroCodigo, cajeroNombre, cajeroER;
    TextView clienteCodigo, clienteNombre, clienteER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo_detalle);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"db_recibo",null,1);
        conn1=new ConexionSQLiteHelper(getApplicationContext(),"db_cajero",null,1);
        conn2=new ConexionSQLiteHelper(getApplicationContext(),"db_cliente",null,1);

        reciboCodigo=(TextView)findViewById(R.id.Detalle_Recibo_Codigo);
        reciboMonto=(TextView)findViewById(R.id.Detalle_Recibo_Monto);
        reciboER=(TextView)findViewById(R.id.Detalle_Recibo_ER);

        cajeroCodigo=(TextView)findViewById(R.id.Detalle_ReciboCajero_Codigo);
        cajeroNombre=(TextView)findViewById(R.id.Detalle_ReciboCajero_Nombre);
        cajeroER=(TextView)findViewById(R.id.Detalle_ReciboCajero_ER);

        clienteCodigo=(TextView)findViewById(R.id.Detalle_ReciboCliente_Codigo);
        clienteNombre=(TextView)findViewById(R.id.Detalle_ReciboCliente_Nombre);
        clienteER=(TextView)findViewById(R.id.Detalle_ReciboCliente_ER);

        Bundle objetoEnviado=getIntent().getExtras();
        Recibo recibo=null;

        if(objetoEnviado!=null){
            recibo= (Recibo) objetoEnviado.getSerializable("recibo");
            reciboCodigo.setText(recibo.getCodigoRecibo().toString());
            reciboMonto.setText(recibo.getMonto().toString());
            reciboER.setText(recibo.getEstadoRegistro().toString());
            consultarCajero(recibo.getCodigoCajero());
            consultarCliente(recibo.getCodigoCliente());
        }
    }



    private void consultarCajero(Integer idCajero) {

        SQLiteDatabase db=conn1.getReadableDatabase();
        String[] parametros4={idCajero.toString()};
        String[] campos={Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_ESTADO_REGISTRO};
        Toast.makeText(getApplicationContext(),"El documento "+idCajero,Toast.LENGTH_LONG).show();
        try {
            Cursor cursor =db.query(Utilidades.TABLA_CAJERO,campos,Utilidades.CAMPO_CODIGO+"=?",parametros4,null,null,null);
            cursor.moveToFirst();
            cajeroCodigo.setText(idCajero.toString());
            cajeroNombre.setText(cursor.getString(0));
            cajeroER.setText(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
            cajeroNombre.setText("");
            cajeroER.setText("");
        }



    }
    private void consultarCliente(Integer idCliente) {
        SQLiteDatabase db=conn2.getReadableDatabase();
        String[] parametros5={idCliente.toString()};
        String[] campos2={UtilidadesCliente.CAMPO_NOMBRE2,UtilidadesCliente.CAMPO_ESTADO_REGISTRO2};
        Toast.makeText(getApplicationContext(),"El documento "+idCliente,Toast.LENGTH_LONG).show();
        try {
            Cursor cursor =db.query(UtilidadesCliente.TABLA_CLIENTE,campos2,UtilidadesCliente.CAMPO_CODIGO2+"=?",parametros5,null,null,null);
            cursor.moveToFirst();
            clienteCodigo.setText(idCliente.toString());
            clienteNombre.setText(cursor.getString(0));
            clienteER.setText(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
            clienteNombre.setText("");
            clienteER.setText("");
        }
    }
}

package com.example.disfruta;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Date;
import java.util.Locale;


public class TiendaActivity extends AppCompatActivity {

    private EditText caquiNum_et;
    private EditText chirimoyaNum_et;
    private EditText granadaNum_et;
    private EditText aguacateNum_et;
    private EditText pitahayaNum_et;

    public final int ID_CAQUI = 1;
    public final int ID_CHIRIMOYA = 2;
    public final int ID_AGUACATE = 3;
    public final int ID_GRANADA = 4;
    public final int ID_PITAHAYA = 5;

    int cantidadCaqui;
    int cantidadChirimoya;
    int cantidadAguacate;
    int cantidadGranada;
    int cantidadPitahaya;

    public final static String ID = "id_user";
    public final static String NOMBRE = "name";
    public final static String PEDIDO = "id_pedido";
    public static int userID = 0;
    public static String userNombre = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tienda_layout);

        userID = getIntent().getExtras().getInt(this.ID);
        userNombre = getIntent().getExtras().getString(this.NOMBRE);

        caquiNum_et = (EditText)findViewById(R.id.caqui_cantidad_Edit);
        chirimoyaNum_et = (EditText)findViewById(R.id.chirimoya_cantidad_Edit);
        granadaNum_et = (EditText)findViewById(R.id.granada_cantidad_Edit);
        aguacateNum_et = (EditText)findViewById(R.id.aguacate_cantidad_Edit);
        pitahayaNum_et = (EditText)findViewById(R.id.pitahaya_cantidad_Edit);
    }

    public void siguienteOnClick(View v){

        boolean datosOK = ComprobarDatos();

        if(datosOK){

            int rowPediodID = GuardarDatosPedido();

            GuardarDatosLineaDetalle(rowPediodID);

            //Toast y Cambio de actividad --> Factura
            Toast.makeText(getApplicationContext(), "GENERANDO FACTURA", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, FacturaActivity.class);
            intent.putExtra(this.NOMBRE, this.userNombre);
            intent.putExtra(this.ID, this.userID);
            intent.putExtra(this.PEDIDO, rowPediodID);
            startActivity(intent);
            finish();

        }else{
            Toast.makeText(getApplicationContext(), "No ha seleccionado ninguna fruta, porfavor seleccione alguna y continue la compra", Toast.LENGTH_LONG).show();
        }
    }

    public void salirOnClick(View v) throws InterruptedException {

        Toast.makeText(getApplicationContext(), "Saliendo de la aplicación", Toast.LENGTH_LONG).show();
        Thread.sleep(3000);
        finish();
    }

    public void GuardarDatosLineaDetalle(int rowPedidoID){

        UsuarioDISQLite consulta = new UsuarioDISQLite(this);
        int numlineas;

        if(cantidadCaqui > 0 ){
            numlineas = consulta.consultarCantidadLineas();
            consulta.SetLineaDetalle(numlineas + 1, rowPedidoID, cantidadCaqui, ID_CAQUI);
        }
        if(cantidadChirimoya > 0 ){
            numlineas = consulta.consultarCantidadLineas();
            consulta.SetLineaDetalle(numlineas + 1, rowPedidoID, cantidadChirimoya, ID_CHIRIMOYA);

        }
        if(cantidadAguacate > 0 ){
            numlineas = consulta.consultarCantidadLineas();
            consulta.SetLineaDetalle(numlineas + 1, rowPedidoID, cantidadAguacate, ID_AGUACATE);

        }
        if(cantidadGranada > 0 ){
            numlineas = consulta.consultarCantidadLineas();
            consulta.SetLineaDetalle(numlineas + 1, rowPedidoID, cantidadGranada, ID_GRANADA);

        }
        if(cantidadPitahaya > 0){
            numlineas = consulta.consultarCantidadLineas();
            consulta.SetLineaDetalle(numlineas + 1, rowPedidoID, cantidadPitahaya, ID_PITAHAYA);
        }
    }

    private int GuardarDatosPedido(){

        UsuarioDISQLite consulta = new UsuarioDISQLite(this);
        int numPedidos = consulta.consultarCantidadPedidos();
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String fecha = df.format(c);
        int id_user = userID;
        long rowPedidoID = consulta.SetPedido(numPedidos + 1,fecha, id_user );

        return (int)rowPedidoID;
    }

    public Boolean ComprobarDatos(){

        cantidadCaqui = Integer.parseInt(caquiNum_et.getText().toString());
        cantidadChirimoya = Integer.parseInt(chirimoyaNum_et.getText().toString());
        cantidadAguacate = Integer.parseInt(aguacateNum_et.getText().toString());
        cantidadGranada = Integer.parseInt(granadaNum_et.getText().toString());
        cantidadPitahaya = Integer.parseInt(pitahayaNum_et.getText().toString());

        return cantidadCaqui > 0 || cantidadChirimoya > 0 || cantidadAguacate > 0 || cantidadGranada > 0 || cantidadPitahaya > 0;
    }

    /* caqui */

    public void caquiOnPlusClick(View v){
        String temp = caquiNum_et.getText().toString();
        if(!"".equals(temp)){
            int n = Integer.parseInt(temp);
            if(n >=19){
                Toast.makeText(getApplicationContext(), "No se puede seleccionar mas de 19", Toast.LENGTH_LONG).show();
                n = 19;
            }
            else{
                n++;
                temp = String.valueOf(n);
                caquiNum_et.setText(temp);
            }
        }
    }

    public void caquiOnMinusClick(View v){
        String temp = caquiNum_et.getText().toString();
        if(!"".equals(temp)){
            int n = Integer.parseInt(temp);
            if(n <=0){
                Toast.makeText(getApplicationContext(), "No se puede seleccionar menos de 0", Toast.LENGTH_LONG).show();
                n = 0;
            }
            else{
                n--;
                temp = String.valueOf(n);
                caquiNum_et.setText(temp);
            }
        }
    }

    /* chirimoya */

    public void chirimoyaOnPlusClick(View v){
        String temp = chirimoyaNum_et.getText().toString();
        if(!"".equals(temp)){
            int n = Integer.parseInt(temp);
            if(n >=19){
                Toast.makeText(getApplicationContext(), "No se puede seleccionar mas de 19", Toast.LENGTH_LONG).show();
                n = 19;
            }
            else{
                n++;
                temp = String.valueOf(n);
                chirimoyaNum_et.setText(temp);
            }
        }
    }

    public void chirimoyaOnMinusClick(View v){
        String temp = chirimoyaNum_et.getText().toString();
        if(!"".equals(temp)){
            int n = Integer.parseInt(temp);
            if(n <=0){
                Toast.makeText(getApplicationContext(), "No se puede seleccionar menos de 0", Toast.LENGTH_LONG).show();
                n = 0;
            }
            else{
                n--;
                temp = String.valueOf(n);
                chirimoyaNum_et.setText(temp);
            }
        }
    }

    /* granada */

    public void granadaOnPlusClick(View v){
        String temp = granadaNum_et.getText().toString();
        if(!"".equals(temp)){
            int n = Integer.parseInt(temp);
            if(n >=19){
                Toast.makeText(getApplicationContext(), "No se puede seleccionar mas de 19", Toast.LENGTH_LONG).show();
                n = 19;
            }
            else{
                n++;
                temp = String.valueOf(n);
                granadaNum_et.setText(temp);
            }
        }
    }

    public void granadaOnMinusClick(View v){
        String temp = granadaNum_et.getText().toString();
        if(!"".equals(temp)){
            int n = Integer.parseInt(temp);
            if(n <=0){
                Toast.makeText(getApplicationContext(), "No se puede seleccionar menos de 0", Toast.LENGTH_LONG).show();
                n = 0;
            }
            else{
                n--;
                temp = String.valueOf(n);
                granadaNum_et.setText(temp);
            }
        }
    }

    /* aguacate */

    public void aguacateOnPlusClick(View v){
        String temp = aguacateNum_et.getText().toString();
        if(!"".equals(temp)){
            int n = Integer.parseInt(temp);
            if(n >=19){
                Toast.makeText(getApplicationContext(), "No se puede seleccionar mas de 19", Toast.LENGTH_LONG).show();
                n = 19;
            }
            else{
                n++;
                temp = String.valueOf(n);
                aguacateNum_et.setText(temp);
            }
        }
    }

    public void aguacateOnMinusClick(View v){
        String temp = aguacateNum_et.getText().toString();
        if(!"".equals(temp)){
            int n = Integer.parseInt(temp);
            if(n <=0){
                Toast.makeText(getApplicationContext(), "No se puede seleccionar menos de 0", Toast.LENGTH_LONG).show();
                n = 0;
            }
            else{
                n--;
                temp = String.valueOf(n);
                aguacateNum_et.setText(temp);
            }
        }
    }

    /* pitahaya */

    public void pitahayaOnPlusClick(View v){
        String temp = pitahayaNum_et.getText().toString();
        if(!"".equals(temp)){
            int n = Integer.parseInt(temp);
            if(n >=19){
                Toast.makeText(getApplicationContext(), "No se puede seleccionar mas de 19", Toast.LENGTH_LONG).show();
                n = 19;
            }
            else{
                n++;
                temp = String.valueOf(n);
                pitahayaNum_et.setText(temp);
            }
        }
    }

    public void pitahayaOnMinusClick(View v){
        String temp = pitahayaNum_et.getText().toString();
        if(!"".equals(temp)){
            int n = Integer.parseInt(temp);
            if(n <=0){
                Toast.makeText(getApplicationContext(), "No se puede seleccionar menos de 0", Toast.LENGTH_LONG).show();
                n = 0;
            }
            else{
                n--;
                temp = String.valueOf(n);
                pitahayaNum_et.setText(temp);
            }
        }
    }
}
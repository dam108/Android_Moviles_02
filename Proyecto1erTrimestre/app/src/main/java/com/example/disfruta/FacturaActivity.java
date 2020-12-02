package com.example.disfruta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;
import android.widget.Toast;

import com.example.disfruta.databinding.FacturaLayoutBinding;

import java.util.ArrayList;

public class FacturaActivity extends AppCompatActivity {

    public final static String ID = "id_user";
    public final static String NOMBRE = "name";
    public final static String PEDIDO = "id_pedido";

    public static int userID = 0;
    public static String userNombre = "";
    public static int userPedido = 0;

    UsuarioDISQLite usrDIS;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         FacturaLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.factura_layout);

        userID = getIntent().getExtras().getInt(this.ID);
        userNombre = getIntent().getExtras().getString(this.NOMBRE);
        userPedido = getIntent().getExtras().getInt(this.PEDIDO);

        this.usrDIS = new UsuarioDISQLite(this);

        MessageDialog loginPopUp = new MessageDialog("COMPRA REALIZADA",
                "Se ha realizado la compra de Frutas con exito, a continuación se le muestra su factura.");
        loginPopUp.setCancelable(false);
        FragmentManager fm = this.getSupportFragmentManager();
        loginPopUp.show(fm, "Compra realizada");

       Factura factura = usrDIS.getFactura(this.userID, this.userPedido);

       binding.setFactura(factura);

        LinearLayout contenedor = (LinearLayout) findViewById(R.id.ContendedorPadre);

        TableLayout tl = new TableLayout(this);
        TableLayout.LayoutParams tablelayoutParams = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        tablelayoutParams.setMargins(10,0,10,0);
        tl.setLayoutParams(tablelayoutParams);

        for (LineaDetalle item : factura.getLineas()) {

            TableRow tr = new TableRow(this);
            tr.setBackgroundResource(R.color.colorBlanco);

            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10,10, 10);

            TextView tv2 = new TextView(this);
            tv2.setBackgroundResource(R.color.colorBlanco);
            tv2.setTextColor(R.color.colorNegro);
            tv2.setPadding(10, 5,5,5);
            tv2.setText("Fruta: "+ item.nombre + "\nCantidad: "+ item.cantidad +", Precio unidad: "+
                    Math.round( item.precioUnidad * 100.0) / 100.0 + "€, Total: "+  Math.round( item.precioTotal * 100.0) / 100.0 + "€");
            tr.addView(tv2, params);

            tl.addView(tr);
        }
        contenedor.addView(tl);
    }

    public void SalirOnClick(View v) throws InterruptedException {
        Toast.makeText(getApplicationContext(), "Saliendo de la aplicación", Toast.LENGTH_LONG).show();
        Thread.sleep(3000);
        finish();
    }

    public void VolverComprarOnClick(View v) {


        Toast.makeText(getApplicationContext(), "VOLVIENDO A LA TIENDA", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, TiendaActivity.class);
        intent.putExtra(this.NOMBRE, this.userNombre);
        intent.putExtra(this.ID, this.userID);
        startActivity(intent);

        finish();
    }
}
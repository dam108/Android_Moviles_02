package com.example.actividad3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActividadSecundaria extends AppCompatActivity {
    private TextView et_resultado;

/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_secundaria);

        et_resultado = findViewById(R.id.textViewResultado);

        Intent intent = getIntent();
        et_resultado.setText("Hola "+intent.getExtras().getString(ActividadPrincipal.NOMBRE));


    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_secundaria);

        Intent intent = getIntent();
        String nuevoTitulo="Book flight: " + intent.getExtras().getString(ActividadPrincipal.NOMBRE);
        setTitle(nuevoTitulo);

        et_resultado = findViewById(R.id.textViewResultado);
        et_resultado.setText("ID: "+intent.getExtras().getInt(ActividadPrincipal.ID)+"\n"
                +"Nombre:  "+intent.getExtras().getString(ActividadPrincipal.NOMBRE)+"\n"
                +"Login:  "+intent.getExtras().getString(ActividadPrincipal.LOGIN)+" JADF08 \n"
                +"Password:  "+intent.getExtras().getString(ActividadPrincipal.PASSWORD)+"\n");
    }

    public void OnClickVolver(View view) {
        finish();

    }
}
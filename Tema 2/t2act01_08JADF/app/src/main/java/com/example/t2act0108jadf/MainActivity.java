package com.example.t2act0108jadf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario01); // esta linea es para usar el formulario01.xml

 /*       ESTE CODIGO ES PARA EL FORMULARIO HECHO SOLO EN JAVA

        LinearLayout formulario02 = new LinearLayout(this);
        formulario02.setOrientation(LinearLayout.VERTICAL);

        TextView tv = new TextView(this);
        tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tv.setText(R.string.registro);
        formulario02.addView(tv);

        EditText ed01 = new EditText(this);
        ed01.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ed01.setEms(10);
        ed01.setText(R.string.nombre);
        formulario02.addView(ed01);

        TextView tv02 = new TextView(this);
        tv02.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tv02.setText(R.string.texto01);
        formulario02.addView(tv02);

        TextView tv03 = new TextView(this);
        tv03.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tv03.setText(R.string.nombreCompleto);
        formulario02.addView(tv03);

        Button env = new Button(this);
        env.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        env.setText(R.string.envBot);
        formulario02.addView(env);

       // setContentView(formulario02);
*/
    }
}
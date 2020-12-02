package com.example.t2act05_1_08jadf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText fromText;
    private EditText toText;
    private EditText departText;
    private EditText returnText;
    private EditText pasagerosText;
    private EditText personNameText;
    private EditText specialRequetsText;
    private EditText emailText;
    private TextView resultados;
    private TextView resultadosHTML;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromText = findViewById(R.id.editTextFrom);
        toText = findViewById(R.id.editTextTo);
        departText = findViewById(R.id.editTextTextDepart);
        returnText = findViewById(R.id.editTextTextRet);
        pasagerosText = findViewById(R.id.editTextTextPass);
        personNameText = findViewById(R.id.editTextTextPersonName);
        specialRequetsText = findViewById(R.id.editTextTextSpecialRequests);
        emailText = findViewById(R.id.editTextTextEmail);
        resultados = findViewById(R.id.resultados_TextView);
        resultadosHTML = findViewById(R.id.resultados_Html_TextView);

    }

    public void GenerarResultados(View v){
        String quien = v.getClass().toString();
        Log.d("creation",quien);
        String from = fromText.getText().toString();
        String to = toText.getText().toString();
        String depart = departText.getText().toString();
        String ret = returnText.getText().toString();
        String pasageros = pasagerosText.getText().toString();
        String nombreCompleto = personNameText.getText().toString();
        String specialReq = specialRequetsText.getText().toString();
        String email = emailText.getText().toString();
        String htmlMontado = "<h3>JADF_08</h3><h4>From : <span style=\"color:red;\"><b>"+from+
                "</b></span></h4><h4>To : <span style=\"color:red;\"><b>"+to+"</b></span></h4>" +
                "<h4>Depart : <span style=\"color:red;\"><b>"+depart+
                "</b></span></h4><h4>Return : <span style=\"color:red;\"><b>"+ret+"</b></span></h4>"+
                "<h4>Passengers : <span style=\"color:red;\"><b>"+pasageros+"</b></span></h4><h4>" +
                "Last Name, First Name : <span style=\"color:red;\"><b>"+nombreCompleto+"</b></span></h4>" +
                "<h4>Special Request : <span style=\"color:red;\"><b>"+specialReq+"</b></span></h4><h4>Email" +
                " : <span style=\"color:red;\"><b>"+email+"</b></span></h4>";

        if(quien.equals("class androidx.appcompat.widget.AppCompatButton")){
            htmlMontado = "<h4>Enviado desde Search Flights</h4>"+htmlMontado ;
        }
        else{
            htmlMontado = "<h4>Enviado desde Campo Email</h4>" + htmlMontado;
        }
        Spanned html = Html.fromHtml( htmlMontado, HtmlCompat.FROM_HTML_MODE_LEGACY );

        String viewToHtml = Html.toHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY);

        resultados.setVisibility(View.VISIBLE);
        resultados.setText(html);
        resultadosHTML.setVisibility(View.VISIBLE);
        resultadosHTML.setText(viewToHtml);

    }

    public void OnPlusClick(View v){
        String temp = pasagerosText.getText().toString();
        if(!"".equals(temp)){
            int n = Integer.parseInt(temp);
            if(n >=19) n = 19;
            else{
                n++;
                temp = String.valueOf(n);
                pasagerosText.setText(temp);
            }
        }
    }

    public void OnMinusClick(View v){
        String temp = pasagerosText.getText().toString();
        if(!"".equals(temp)){
            int n = Integer.parseInt(temp);
            if(n <=0) n = 0;
            else{
                n--;
                temp = String.valueOf(n);
                pasagerosText.setText(temp);
            }
        }
    }
}
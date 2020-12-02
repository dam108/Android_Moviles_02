package com.example.t2act05_2_08jadf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

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
    private Spinner spinerClases;
    private String StringSpiner = "Sin Clase";
    private TextView primeraClaseText;
    private ToggleButton pagarConAviosToggle;
    private CheckBox flexCheck;
    private RadioButton tipoViaje_1;
    private RadioButton tipoViaje_2;
    private RadioButton tipoViaje_3;
    private RadioButton nPAradas_1;
    private RadioButton nPAradas_2;
    private RadioButton nPAradas_3;
    private RadioGroup radio_1;
    private RadioGroup radio_2;
    private String sRadio_1 = "no se conoce";
    private String sRadio_2 = "no se conoce";
    private String pagarAvios = "NO";
    private String checkBox_flex = "NO";

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
        primeraClaseText = findViewById(R.id.firtClassTextView);
        pagarConAviosToggle= findViewById(R.id.aviosToggleButton);
        flexCheck= findViewById(R.id.flexDatesCheckBox);
        tipoViaje_1= findViewById(R.id.radioButton1);
        tipoViaje_2= findViewById(R.id.radioButton2);
        tipoViaje_3= findViewById(R.id.radioButton3);
        nPAradas_1= findViewById(R.id.nontop);
        nPAradas_2= findViewById(R.id.onestop);
        nPAradas_3= findViewById(R.id.twostopormore);
        radio_1 = findViewById(R.id.radioGroup01);
        radio_2 = findViewById(R.id.radioGroup02);
        spinerClases = findViewById(R.id.spinner);

        spinerClases.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
               if(position == 1){
                   primeraClaseText.setText("08_JADF Some flights don’t offer first class");
                   StringSpiner = "First";
               }
               else if(position == 2){
                   primeraClaseText.setText("");
                   StringSpiner = "Business";
               }
               else if(position == 3){
                   primeraClaseText.setText("");
                   StringSpiner = "Economy";
               }
               else {
                   primeraClaseText.setText("");
               }

            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                primeraClaseText.setText("");
            }
        });

        radio_1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                System.out.println(checkedId);
                if ( checkedId == 2131230917) sRadio_1 = "Roundtrip";
                else if(checkedId == 2131230918) sRadio_1 = "One way";
                else if(checkedId == 2131230919) sRadio_1 = "History";
                else sRadio_1 = "-----";
            }
        });

        radio_2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                System.out.println(checkedId);
                if(checkedId == 2131230895) sRadio_2 = "Non Stop";
                else if(checkedId == 2131230902) sRadio_2 = "One Stop";
                else if (checkedId == 2131230995) sRadio_2 = "2 or more";
                else sRadio_2 = "-----";
            }
        });

        pagarConAviosToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    pagarAvios = "YES";
                }else {
                    pagarAvios = "NO";
                }
            }
        });

        flexCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkBox_flex = "YES";
                }else {
                    checkBox_flex = "NO";
                }
            }
        });
    }

    public void GenerarResultados(View v){

        String quien = v.getClass().toString();
        Log.d("creation",quien);
        String tipovViaje =sRadio_1;
        String from = fromText.getText().toString();
        String to = toText.getText().toString();
        String depart = departText.getText().toString();
        String ret = returnText.getText().toString();
        String pasageros = pasagerosText.getText().toString();
        String nStops = sRadio_2;
        String sFlexCheck = checkBox_flex;
        String sPagarConAvios = pagarAvios;
        String sClase = StringSpiner;
        String nombreCompleto = personNameText.getText().toString();
        String specialReq = specialRequetsText.getText().toString();
        String email = emailText.getText().toString();

        if(tipoViaje_1.isChecked() == true || tipoViaje_2.isChecked() == true || tipoViaje_3.isChecked() == true ){

            if("".equals(from)){
                Toast.makeText(this, "08_JADF Se debe cubrir el campo from", Toast.LENGTH_SHORT).show();
                fromText.requestFocus();
            }
            else{
                if("".equals(to)){
                    Toast.makeText(this, "08_JADF Se debe cubrir el campo to", Toast.LENGTH_SHORT).show();
                    toText.requestFocus();
                }else {
                    if("".equals(depart)){
                        Toast.makeText(this, "08_JADF Se debe cubrir el campo fecha de ida", Toast.LENGTH_SHORT).show();
                        departText.requestFocus();
                    }
                    else{
                        if("".equals(ret)){
                            Toast.makeText(this, "08_JADF Se debe cubrir el campo fecha de vuelta", Toast.LENGTH_SHORT).show();
                            returnText.requestFocus();
                        }
                        else{
                            if("0".equals(pasageros)){
                                Toast.makeText(this, "08_JADF Se debe indicar el numero de pasajeros", Toast.LENGTH_SHORT).show();
                                pasagerosText.requestFocus();
                            }
                            else{
                                if(nPAradas_1.isChecked() == true || nPAradas_2.isChecked() == true || nPAradas_3.isChecked() == true){
                                    if(spinerClases.getSelectedItemPosition() == 0){
                                        Toast.makeText(this, "08_JADF Se debe seleccionar el tipo de clase en la que se desea viajar", Toast.LENGTH_SHORT).show();
                                    }else{
                                        if("".equals(nombreCompleto)){
                                            Toast.makeText(this, "08_JADF Se debe indicar el nombre y apellidos", Toast.LENGTH_SHORT).show();
                                            personNameText.requestFocus();
                                        }
                                        else{
                                            if("".equals(email)){
                                                Toast.makeText(this, "08_JADFSe debe indicar un email valido", Toast.LENGTH_SHORT).show();
                                                emailText.requestFocus();
                                            }
                                            else{
                                                Toast.makeText(this, "08_JADF Buscando vuelos", Toast.LENGTH_SHORT).show();

                                                String htmlMontado = "<h3>JADF_08</h3><h4>Tipo de viaje : <span style=\"color:red;\">"+ tipovViaje + "</h4>" +
                                                        "<h4>From : <span style=\"color:red;\"><b>"+from+
                                                        "</b></span></h4><h4>To : <span style=\"color:red;\"><b>"+to+"</b></span></h4>" +
                                                        "<h4>Depart : <span style=\"color:red;\"><b>"+depart+
                                                        "</b></span></h4><h4>Return : <span style=\"color:red;\"><b>"+ret+"</b></span></h4>"+
                                                        "<h4>Passengers : <span style=\"color:red;\"><b>"+pasageros+"</b></span></h4> " +
                                                        "<h4>Numero de paradas: <span style=\"color:red;\"><b>"+nStops+"</b></span></h4>"+
                                                        "<h4>Pagar con Avios: <span style=\"color:red;\"><b>"+sPagarConAvios+"</b></span></h4>" +
                                                        "<h4>Fechas Flexibles: <span style=\"color:red;\"><b>"+sFlexCheck+"</b></span></h4>" +
                                                        "<h4>Asiento: <span style=\"color:red;\"><b>"+sClase+"</b></span></h4>" +
                                                        "<h4>Last Name, First Name : <span style=\"color:red;\"><b>"+nombreCompleto+"</b></span></h4>" +
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
                                        }
                                    }
                                }else{
                                    Toast.makeText(this, "08_JADF Se debe seleccionar el numero de escalas", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
            }

        }else{
            Toast.makeText(this, "08_JADF Se debe seleccionar un tipo de viaje", Toast.LENGTH_SHORT).show();
        }

    }

    public void OnPlusClick(View v){
        String temp = pasagerosText.getText().toString();
        if(!"".equals(temp)){
            int n = Integer.parseInt(temp);
            if(n >=19){
                Toast.makeText(this, "08_JADF El número de pasajeros debe ser menor que 20", Toast.LENGTH_LONG).show();
                n = 19;

            }
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
            if(n <=0){
                Toast.makeText(this, "08_JADF El número de pasajeros debe ser mayor que 0", Toast.LENGTH_LONG).show();
                n = 0;
            }
            else{
                n--;
                temp = String.valueOf(n);
                pasagerosText.setText(temp);
            }
        }
    }


}
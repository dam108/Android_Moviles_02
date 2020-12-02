package com.example.disfruta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class registrarUsuarioActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_pass;
    private EditText et_username;
    private EditText et_address;
    private EditText et_zip;
    private Spinner sp_province;
    private CheckBox ck_country;
    private EditText et_phone;

    private String s_sp_province, name , username, pass, address, province,
            country, snumber, szip, sphone;
    private int number;
    private int zip;
    private int phone;

    private DisFrutaDB disFrutaDB;

    public final static String ID = "id_user";
    public final static String NAME = "name";

    UsuarioDISQLite usrDIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registar_layout);

        et_name = ((EditText) findViewById(R.id.nombre_editText));
        et_pass = ((EditText) findViewById(R.id.contrasena_editText));
        et_username = ((EditText) findViewById(R.id.login_editText));
        et_address = ((EditText) findViewById(R.id.direccion_editText));
        et_zip = ((EditText) findViewById(R.id.codigoPostal_editText));
        sp_province = ((Spinner) findViewById(R.id.provincias_spinner));
        ck_country = ((CheckBox) findViewById(R.id.pais_Check));
        et_phone = ((EditText) findViewById(R.id.numeroTlf_editText));

        sp_province = (Spinner) findViewById(R.id.provincias_spinner);
        sp_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 1) s_sp_province = "A Coruña";
                else if(position == 2) s_sp_province = "Lugo";
                else if(position == 3) s_sp_province = "Orense";
                else if(position == 4) s_sp_province = "Pontevedra";
                else s_sp_province = "";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                s_sp_province = "";
            }
        });

        copiarDB();
        this.usrDIS = new UsuarioDISQLite(this);

    }

    private void copiarDB(){

        String destnodb = "/data/data/" + getPackageName() + "/databases/"
                + DisFrutaDB.NOMBRE_DB;

        File archivo  = new File(destnodb);
        Log.d("DEPURACIÓN", "Ruta archivo DB: "+ destnodb);

        if(archivo.exists()){
            // Toast.makeText(getApplicationContext(), "La DB no se puede copiar. Ya existe", Toast.LENGTH_LONG).show();
            return;
        }

        String rutadb = "/data/data/" + getPackageName() + "/databases";
        File rutaArchivoDB = new File(rutadb);
        rutaArchivoDB.mkdirs();

        InputStream inputStream;
        try {
            inputStream = getAssets().open(DisFrutaDB.NOMBRE_DB);
            OutputStream outputStream = new FileOutputStream(destnodb);

            int tamread;
            byte[] buffer = new byte[2048];

            while ((tamread = inputStream.read(buffer)) > 0){
                outputStream.write(buffer,0, tamread);
            }

            inputStream.close();
            outputStream.flush();
            outputStream.close();
            Toast.makeText(getApplicationContext(), "BASE DE DATOS COPIADA", Toast.LENGTH_LONG).show();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void registrarUsuario(){

        if(snumber == null || "".equals(snumber)){
            number = 0;
        } else number = Integer.parseInt(snumber);
        zip = Integer.parseInt(szip);
        phone = Integer.parseInt(sphone);

        usrDIS.setUsuario(name, username, pass, address, number, zip, province, country, phone);

        Usuario usr = usrDIS.getUsuario(username, pass);

        if(usr != null){
            Log.d("DEPURACIÓN", "Nombre usuario: "+usr.getNombre());
            Toast.makeText(getApplicationContext(), "INICIANDO SESIÓN", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, TiendaActivity.class);
            intent.putExtra(this.NAME, usr.getNombre());
            intent.putExtra(this.ID, usr.getId());
            startActivity(intent);
            finish();
        }
        else {
            MessageDialog loginPopUp = new MessageDialog("No se pudo Registrar el usuario",
                    "Hemos tenido problemas al registrar el usuario e intentar iniciar sesion con el");
            loginPopUp.setCancelable(false);
            FragmentManager fm = this.getSupportFragmentManager();
            loginPopUp.show(fm, "Aviso Registro/Login Erróneo");
        }
    }

    public void RegistrarUsuario_Click(View view){

        name = ((EditText) findViewById(R.id.nombre_editText)).getText().toString();
        username = ((EditText) findViewById(R.id.login_editText)).getText().toString();
        pass = ((EditText) findViewById(R.id.contrasena_editText)).getText().toString();
        address = ((EditText) findViewById(R.id.direccion_editText)).getText().toString();
        snumber = ((EditText) findViewById(R.id.numeroCasa_editText)).getText().toString();
        szip = ((EditText) findViewById(R.id.codigoPostal_editText)).getText().toString();
        province = s_sp_province;
        country = "España";
        sphone = ((EditText) findViewById(R.id.numeroTlf_editText)).getText().toString();

        if("".equals(name)){
            Toast.makeText(this, "Se tiene que cubrir el campo Nombre", Toast.LENGTH_SHORT).show();
            et_name.requestFocus();
        }
        else{
            if("".equals(username)){
                Toast.makeText(this, "Se tiene que cubrir el campo Usuario", Toast.LENGTH_SHORT).show();
                et_username.requestFocus();
            }
            else{
                if("".equals(pass)){
                    Toast.makeText(this, "Se tiene que cubrir el campo Contraseña", Toast.LENGTH_SHORT).show();
                    et_pass.requestFocus();
                }
                else{
                    if("".equals(address)){
                        Toast.makeText(this, "Se tiene que cubrir el campo dirección", Toast.LENGTH_SHORT).show();
                        et_address.requestFocus();
                    }
                    else {
                        if("".equals(szip)){
                            Toast.makeText(this, "Se tiene que cubrir el campo código postal", Toast.LENGTH_SHORT).show();
                            et_zip.requestFocus();
                        }
                        else {
                            if("".equals(s_sp_province)){
                                Toast.makeText(this, "Se tiene que elegir una provincia", Toast.LENGTH_SHORT).show();
                                sp_province.requestFocus();
                            }
                            else{
                                if(!ck_country.isChecked()){
                                    Toast.makeText(this, "Se tiene que marcar el pais , en este momento solo tenemos España, marcalo", Toast.LENGTH_SHORT).show();
                                    ck_country.requestFocus();
                                }
                                else{
                                    if("".equals(sphone)){
                                        Toast.makeText(this, "Se tiene que cubrir el campo teléfono", Toast.LENGTH_SHORT).show();
                                        et_phone.requestFocus();
                                    }
                                    else{
                                        registrarUsuario();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
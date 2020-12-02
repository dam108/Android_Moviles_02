package com.example.disfruta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class loginActivity extends AppCompatActivity {

    public final static String ID = "id_user";
    public final static String NOMBRE = "name";

    UsuarioDISQLite usrDIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        MessageDialog loginPopUp = new MessageDialog("Registro", "Si no eres cliente primero debes registrarte, para ello usa el boton de registrarse.");
        loginPopUp.setCancelable(false);
        FragmentManager fm = this.getSupportFragmentManager();
        loginPopUp.show(fm, "Aviso Registro");

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
            //Toast.makeText(getApplicationContext(), "Vamos a Borrarlo", Toast.LENGTH_LONG).show();
            //archivo.delete();
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

    public void iniciarSesion(){
        String username = ((EditText) findViewById(R.id.login_editText)).getText().toString();
        String pass = ((EditText) findViewById(R.id.contrasena_editText)).getText().toString();

        Usuario usr = usrDIS.getUsuario(username, pass);

        if(usr != null){
//            Log.d("DEPURACIÓN", "Nombre usuario: "+usr.getNombre());
//            Log.d("DEPURACIÓN", "ID usuario: "+usr.getId());

            Toast.makeText(getApplicationContext(), "INICIANDO SESIÓN", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, TiendaActivity.class);
            intent.putExtra(this.NOMBRE, usr.getNombre());
            intent.putExtra(this.ID, usr.getId());

            startActivity(intent);

            finish();
        }
        else {
            MessageDialog loginPopUp = new MessageDialog("No se pudo iniciar la sesión",
                    "El login o el password que has introducido son incorrectos. Compruébalos y vuelve a intentarlo de nuevo.");
            loginPopUp.setCancelable(false);
            FragmentManager fm = this.getSupportFragmentManager();
            loginPopUp.show(fm, "Aviso Login Erróneo");
        }
    }

    public void iniciarSesion_Click(View view){
        iniciarSesion();
    };

    public void IraRegistrarUsuario_Click(View view){
        Intent intent = new Intent(this, registrarUsuarioActivity.class);
        startActivity(intent);
        finish();
    };
}
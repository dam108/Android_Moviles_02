package com.example.actividad3_1;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.fragment.app.FragmentManager;

        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.File;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.OutputStream;

public class ActividadPrincipal extends AppCompatActivity {

    private EditText et_login;
    private EditText et_password;

    private APPACT3 appbd;
    public final static String NOMBRE = "nombre";
    public final static String ID = "id";
    public final static String PASSWORD = "password";
    public final static String LOGIN = "login";

    UsuarioDAOSQLite usrDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_login = findViewById(R.id.editTextLogin);
        et_password = findViewById(R.id.editTextPassword);

        copiarBD();
        this.usrDAO = new UsuarioDAOSQLite(this);
        xestionarEventos();
    }

    private void copiarBD() {
        String bddestino = "/data/data/" + getPackageName() + "/databases/"
                + APPACT3.NOME_BD;
        File file = new File(bddestino);
        Log.d("DEPURACIÓN", "Ruta archivo BD: " + bddestino);
        if (file.exists()) {
            Toast.makeText(getApplicationContext(), "La BD no se va a copiar. Ya existe", Toast.LENGTH_LONG).show();
            return; // Ya existe la BD, salimos del método
        }


        String pathbd = "/data/data/" + getPackageName()
                + "/databases/";
        File filepathdb = new File(pathbd);
        filepathdb.mkdirs();


        InputStream inputstream;
        try {
            inputstream = getAssets().open(APPACT3.NOME_BD);
            OutputStream outputstream = new FileOutputStream(bddestino);


            int tamread;
            byte[] buffer = new byte[2048];


            while ((tamread = inputstream.read(buffer)) > 0) {
                outputstream.write(buffer, 0, tamread);
            }


            inputstream.close();
            outputstream.flush();
            outputstream.close();
            Toast.makeText(getApplicationContext(), "BASE DE DATOS COPIADA", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        }

    private void xestionarEventos() {
        Button btnLogin = (Button) findViewById(R.id.button);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                iniciarSesion();
            }
        });
    }

    void iniciarSesion() {
        String login = ((EditText) findViewById(R.id.editTextLogin)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();
        Usuario usr =usrDAO.getUsuario(login, password);

        if (usr != null) {
            Log.d("DEPURACIÓN", "Nombre usr: "+ usr.getNombre());
            Toast.makeText(getApplicationContext(), "Iniciando sesión.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ActividadSecundaria.class);
            intent.putExtra(this.NOMBRE, usr.getNombre());
            intent.putExtra(this.PASSWORD, usr.getPassword());
            intent.putExtra(this.LOGIN, usr.getLogin());
            intent.putExtra(this.ID, usr.getId());
            startActivity(intent);
           //finish();
        } else {
            //Toast.makeText(getApplicationContext(), "Error de autentificación.", Toast.LENGTH_LONG).show();
            MensajeDialogo d= new MensajeDialogo();
            d.setCancelable(false);
            FragmentManager fm= this.getSupportFragmentManager();
            d.show(fm,"errorLogin");
        }
    }

        public void OnClickBtnLogin(View view) {
        Intent intent = new Intent (this, ActividadSecundaria.class);
        intent.putExtra(NOMBRE,et_login.getText().toString());
        startActivity(intent);
    }
}
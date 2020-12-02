package com.example.actividad3_1;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UsuarioDAOSQLite implements UsuarioDAO {
    private APPACT3 appbd;
    private Context context;

    UsuarioDAOSQLite(Context context){
        this.context=context;
        this.appbd= new APPACT3(this.context);
    }

    public Usuario getUsuario(String login, String password) {
        Usuario resultado = null;
        SQLiteDatabase sqlLiteDB = appbd.getWritableDatabase();
        String[] param = {login, password};
        String consulta = "SELECT * FROM Usuario WHERE login=? AND password=?";
        Cursor cursor = sqlLiteDB.rawQuery(consulta, param);
        this.depuracion(consulta, param);
        Log.d("DEPURACIÓN", "Nº filas: " + cursor.getCount());
        if (cursor.moveToFirst()) {
            resultado = new Usuario(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }
        return resultado;
    }

    void depuracion(String consulta, String[] param) {
        String texto = "Consulta: " + consulta + " Valores: ";
        for (String p : param) {
            texto += p + " ";
        }
        Log.d("DEPURACIÓN", texto);
    }
}

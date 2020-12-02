package com.example.disfruta;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;
import java.util.ArrayList;

public class UsuarioDISQLite {

    private DisFrutaDB disfrutaDB;
    private Context context;

    public UsuarioDISQLite(Context context){

        this.context = context;
        this.disfrutaDB = new DisFrutaDB(this.context);
    }

    public long SetLineaDetalle(int id_lineadetalle, int id_pedido, int cantidad, int id_articulo){

        SQLiteDatabase sqlLiteDB = disfrutaDB.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_LINEA_ID_LINEA, id_lineadetalle);
        values.put(FeedReaderContract.FeedEntry.COLUMN_LINEA_ID_PEDIDO, id_pedido);
        values.put(FeedReaderContract.FeedEntry.COLUMN_LINEA_CANTIDAD,  cantidad);
        values.put(FeedReaderContract.FeedEntry.COLUMN_LINEA_ID_ARTICULO, id_articulo);

        long newRowID = sqlLiteDB.insert(FeedReaderContract.FeedEntry.TABLE_NAME_LINEADETALLE, null, values);

        return newRowID;
    }

    public long SetPedido(int id_pedido, String fecha, int id_user){

        SQLiteDatabase sqlLiteDB = disfrutaDB.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ID_PEDIDOS, id_pedido);
        values.put(FeedReaderContract.FeedEntry.COLUMN_DATE_PEDIDOS, fecha);
        values.put(FeedReaderContract.FeedEntry.COLUMN_ID, id_user);

        long newRowID = sqlLiteDB.insert(FeedReaderContract.FeedEntry.TABLE_NAME_PEDIDOS, null, values);

        return newRowID;
    }

    public int consultarCantidadLineas(){
        int resultado = 0;
        SQLiteDatabase sqlLiteDB = disfrutaDB.getReadableDatabase();
        resultado = (int) DatabaseUtils.queryNumEntries(sqlLiteDB,"lineadetalle");
        return resultado;
    }

    public int consultarCantidadPedidos() {
        int resultado = 0;
        SQLiteDatabase sqlLiteDB = disfrutaDB.getReadableDatabase();
        resultado = (int)DatabaseUtils.queryNumEntries(sqlLiteDB, "pedidos");
        return resultado;
    }

    public Usuario getUsuario(String login, String password) {
        Usuario resultado = null;
        SQLiteDatabase sqlLiteDB = disfrutaDB.getWritableDatabase();
        String[] param = {login, password};
        String consulta = "SELECT * FROM Usuarios WHERE username=? AND password=?";
        Cursor cursor = sqlLiteDB.rawQuery(consulta, param);
        this.depuracion(consulta, param);
        Log.d("DEPURACIÓN", "Nº filas: " + cursor.getCount());
        if (cursor.moveToFirst()) {
            resultado = new Usuario(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }
        return resultado;
    }

    public void setUsuario(String name, String username, String password, String address,
                           int number, int zip, String province, String country, int phone){

        SQLiteDatabase sqlLiteDB = disfrutaDB.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_USERNAME, username);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_NAME, name);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PASSWORD, password);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS, address);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_NUMBER, number);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ZIP, zip);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PROVINCE, province);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_COUNTRY, country);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PHONE, phone);

        long newRowID = sqlLiteDB.insert(FeedReaderContract.FeedEntry.TABLE_NAME_USUARIOS, null, values);

    }

    public final class FeedReaderContract {

        private FeedReaderContract() {}

        public class FeedEntry implements BaseColumns {
            public static final String TABLE_NAME_USUARIOS = "usuarios";
            public static final String COLUMN_NAME_NAME = "name";
            public static final String COLUMN_NAME_USERNAME = "username";
            public static final String COLUMN_NAME_PASSWORD = "password";
            public static final String COLUMN_NAME_ADDRESS = "address";
            public static final String COLUMN_NAME_NUMBER = "number";
            public static final String COLUMN_NAME_ZIP = "zip";
            public static final String COLUMN_NAME_PROVINCE = "province";
            public static final String COLUMN_NAME_COUNTRY = "country";
            public static final String COLUMN_NAME_PHONE = "phone";
            public static final String TABLE_NAME_PEDIDOS = "pedidos";
            public static final String COLUMN_NAME_ID_PEDIDOS = "id_pedido";
            public static final String COLUMN_DATE_PEDIDOS = "date";
            public static final String COLUMN_ID = "id_user";
            public static final String TABLE_NAME_LINEADETALLE = "lineadetalle";
            public static final String COLUMN_LINEA_ID_LINEA = "id_lineadetalle";
            public static final String COLUMN_LINEA_ID_PEDIDO = "id_pedido";
            public static final String COLUMN_LINEA_CANTIDAD = "cantidad";
            public static final String COLUMN_LINEA_ID_ARTICULO = "id_articulo";

        }
    }

    public Factura getFactura(int idUsuario, int idPedido) {
        String usuario = String.valueOf(idUsuario);
        String pedido = String.valueOf(idPedido);
        Factura resultado = null;
        SQLiteDatabase sqlLiteDB = disfrutaDB.getReadableDatabase();
        String[] param = {usuario};
        String consulta = "SELECT * FROM usuarios WHERE id_user LIKE ?";
        Cursor cursor = sqlLiteDB.rawQuery(consulta, param);

        this.depuracion(consulta, param);

        if (cursor.moveToFirst()) {
            resultado = new Factura(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(4), cursor.getInt(5),
                    cursor.getInt(6), cursor.getString(7),
                    cursor.getString(8), cursor.getInt(9));
        }

        resultado.setLineas(getLineaDetalle(usuario, pedido));

        return resultado;
    }

    public ArrayList<LineaDetalle> getLineaDetalle(String idUsuario, String idPedido){
        SQLiteDatabase sqlLiteDB = disfrutaDB.getReadableDatabase();

        ArrayList<LineaDetalle> resultado = new ArrayList<LineaDetalle>();

        String[] param = {idPedido};
        String consulta = "SELECT * FROM lineadetalle INNER JOIN articulos ON lineadetalle.id_articulo = articulos.id_articulo WHERE id_pedido LIKE ?";
        Cursor cursor = sqlLiteDB.rawQuery(consulta, param);

        cursor.moveToFirst();

        for(int i =  0; i< cursor.getCount(); i++){
            LineaDetalle ldt = new LineaDetalle(cursor.getInt(0), cursor.getString(5), cursor.getInt(2), cursor.getDouble(6));
            resultado.add(ldt);
            cursor.moveToNext();
        }
        cursor.close();

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

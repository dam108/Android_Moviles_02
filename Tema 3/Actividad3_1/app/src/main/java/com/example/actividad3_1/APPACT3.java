package com.example.actividad3_1;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class APPACT3 extends SQLiteOpenHelper {
    public final static String NOME_BD = "appAct3.db";
    public final static int VERSION_BD = 1;

    public APPACT3 (Context context) {
        super(context, NOME_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

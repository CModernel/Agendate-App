package com.example.agendate_app.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class _DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "com.kaizen.agendate.db";
    private static final int DATABASE_VERSION = 1;

    public _DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }

    // Tabla Variedades
    private static final String CREATE_Rubros = "CREATE TABLE Rubros (" +
            "id INTEGER NOT NULL, " +
            "rubroNom TEXT, " +
            "PRIMARY KEY (id)" +
            ");";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_Rubros);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(_DBHelper.class.getName(), "Actualizando version de la base de datos de " + oldVersion + " a " + newVersion + ", el cual va a destruir los datos anteriores.");
        db.execSQL("DROP TABLE IF EXISTS Rubros;");
        onCreate(db);
    }

    public void truncate(SQLiteDatabase db) {
        Log.w(_DBHelper.class.getName(), "Vaciando tablas...");
        db.execSQL("DELETE FROM Rubros;");

        // borro sesion
        //_Sesion.truncate();
    }
}
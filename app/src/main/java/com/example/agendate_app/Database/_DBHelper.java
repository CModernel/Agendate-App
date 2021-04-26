package com.example.agendate_app.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class _DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "com.kaizen.agendate.db";
    private static final int DATABASE_VERSION = 6;

    public _DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }

    private static final String CREATE_Rubros = "CREATE TABLE Rubros (" +
            "id INTEGER NOT NULL, " +
            "rubroNom TEXT, " +
            "PRIMARY KEY (id)" +
            ");";

    private static final String CREATE_Empresas = "CREATE TABLE Empresas (" +
            "EmpId INTEGER NOT NULL, " +
            "EmpRUT INTEGER, " +
            "EmpRazonSocial TEXT,"+
            "EmpDirCalle TEXT,"+
            "EmpDirEsquina TEXT,"+
            "EmpDirNum INTEGER,"+
            "EmpDirEmail TEXT,"+
            "EmpTelefono TEXT,"+
            "EmpDescripcion TEXT,"+
            "EmpActivo BOOLEAN,"+
            "EmpImagen TEXT,"+  // NO SE QUE TIPO DE DATO SERIA ESTA VARIABLE
            "EmpRubro1 INTEGER,"+
            "EmpRubro2 INTEGER," +
            "PRIMARY KEY (EmpId)" +
            ");";


    private static final String CREATE_SolicitudEmpresa = "CREATE TABLE SolicitudEmpresa (" +
            "EmpId INTEGER NOT NULL, " +
            "Fecha DATE, " +
            "HorarioSolicitud TIME,"+
            "PRIMARY KEY (EmpId)" +
            ");";

    private static final String CREATE_Agenda = "CREATE TABLE Agenda (" +
            "id INTEGER NOT NULL, " +
            "FechaSolicitud DATE, " +
            "HoraSolicitud TIME,"+
            "SeConcreto BOOLEAN,"+
            "ComentarioAdmin TEXT,"+
            "ComentarioUsuario TEXT,"+
            "SolicitudActivo BOOLEAN,"+
            "UsuId INTEGER,"+
            "EmpId INTEGER,"+
            "UsuAdminResponsable INTEGER,"+
            "PRIMARY KEY (id)" +
            ");";

    private static final String CREATE_Vermiperfil = "CREATE TABLE Vermiperfil (" +
            "username TEXT NOT NULL, " +
            "first_name TEXT, " +
            "last_name TEXT,"+
            "email TEXT,"+
            "PRIMARY KEY (username)" +
            ");";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_Rubros);
        db.execSQL(CREATE_Empresas);
        db.execSQL(CREATE_SolicitudEmpresa);
        db.execSQL(CREATE_Agenda);
        db.execSQL(CREATE_Vermiperfil);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS Rubros;");
                db.execSQL("DROP TABLE IF EXISTS Empresas;");
                db.execSQL("DROP TABLE IF EXISTS SolicitudEmpresa;");
                db.execSQL("DROP TABLE IF EXISTS Agenda;");
                db.execSQL("DROP TABLE IF EXISTS Vermiperfil;");
                onCreate(db);
    }

    public void truncate(SQLiteDatabase db) {
        db.execSQL("DELETE FROM Rubros;");
        db.execSQL("DELETE FROM Empresas;");
        db.execSQL("DELETE FROM SolicitudEmpresa;");
        db.execSQL("DELETE FROM Agenda;");
        db.execSQL("DELETE FROM Vermiperfil;");
    }
}
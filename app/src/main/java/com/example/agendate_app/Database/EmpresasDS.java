package com.example.agendate_app.Database;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.agendate_app.Interfaces._SyncableGet;
import com.example.agendate_app.Utils._Utils;
import com.example.agendate_app.Utils._WebServicesGet;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("DefaultLocale")
public class EmpresasDS implements _SyncableGet {

    // Database fields
    private SQLiteDatabase database;
    private _DBHelper dbHelper;

    private String[] allEmpresasColumns = {"EmpId", "EmpRUT", "EmpRazonSocial", "EmpDirCalle",
            "EmpDirEsquina", "EmpDirNum", "EmpDirEmail", "EmpTelefono", "EmpDescripcion", "EmpActivo",
            "EmpImagen", "EmpRubro1", "EmpRubro2"};

    public EmpresasDS() {
        dbHelper = new _DBHelper(_Utils.getContext());
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    private void openW() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    private void openR() throws SQLException {
        database = dbHelper.getReadableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // crear empresas
    public boolean createEmpresas(Empresas o) {
        return createEmpresas(o.getEmpId(), o.getEmpRUT(),o.getEmpRazonSocial(),o.getEmpDirCalle(),
                o.getEmpDirEsquina(),o.getEmpDirNum(),o.getEmpDirEmail(),o.getEmpTelefono(),
                o.getEmpDescripcion(), o.getEmpActivo(),o.getEmpImagen(),o.getEmpRubro1(),
                o.getEmpRubro2());
    }

    // Create Empresas
    public boolean createEmpresas(Integer EmpId, Integer EmpRUT, String EmpRazonSocial, String EmpDirCalle,
                                  String EmpDirEsquina, Integer EmpDirNum, String EmpDirEmail, String EmpTelefono,
                                  String EmpDescripcion, Boolean EmpActivo, String EmpImagen, Integer EmpRubro1,
                                  Integer EmpRubro2) {
        ContentValues values = new ContentValues();
        values.put("EmpId", EmpId);
        values.put("EmpRUT", EmpRUT);
        if(EmpRazonSocial != null) values.put("EmpRazonSocial", EmpRazonSocial.trim());
        if (EmpDirCalle != null) values.put("EmpDirCalle", EmpDirCalle.trim());
        if (EmpDirEsquina != null) values.put("EmpDirEsquina", EmpDirEsquina.trim());
        values.put("EmpDirNum", EmpDirNum);
        if (EmpDirEmail != null) values.put("EmpDirEmail", EmpDirEmail.trim());
        if (EmpTelefono != null) values.put("EmpTelefono", EmpTelefono.trim());
        if (EmpDescripcion != null) values.put("EmpDescripcion", EmpDescripcion.trim());
        values.put("EmpActivo", EmpActivo);
        if (EmpImagen != null) values.put("EmpImagen", EmpImagen);
        values.put("EmpRubro1", EmpRubro1);
        values.put("EmpRubro2", EmpRubro2);

        try {
            this.openW();
            if (database.replace("Empresas", null, values) >= 0) {
                this.close();
                return true;
            } else {
                this.close();
                Log.w(EmpresasDS.class.getName(), "Creando Empresas: ERROR 1 - insert - " +
                        "EmpId = " + EmpId + ", EmpRazonSocial = " + EmpRazonSocial.trim());
                return false;
            }
        } catch (Exception e) {
            this.close();
            Log.w(EmpresasDS.class.getName(), "Creando Empresas: ERROR 2 - insert - " + "EmpId = " +
                    EmpId + ", EmpRazonSocial = " + EmpRazonSocial.trim() + " - " + e.getMessage());
            Log.d("EmpresasDS : Lin97", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Get All Empresas
    public List<Empresas> getAllEmpresas() {
        List<Empresas> mEmpresas = new ArrayList<Empresas>();
        this.openR();

        String query = "select * from Empresas";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Empresas mEmpresa = cursorToEmpresas(cursor);
            mEmpresas.add(mEmpresa);
            cursor.moveToNext();
        }
        cursor.close();
        this.close();
        return mEmpresas;
    }

    // Get All Empresas
    public List<Empresas> getAllEmpresasPorRubro(int rubroId) {
        List<Empresas> mEmpresas = new ArrayList<Empresas>();
        this.openR();

        String query = "select * from Empresas where " +
                "EmpRubro1 = " + rubroId + " or EmpRubro2 = "+ rubroId+";";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Empresas mEmpresa = cursorToEmpresas(cursor);
            mEmpresas.add(mEmpresa);
            cursor.moveToNext();
        }
        cursor.close();
        this.close();
        return mEmpresas;
    }

    // Get Empresas por clave
    public Empresas getEmpresas(int EmpId) {
        this.openR();

        String query = "select * from Empresas" +
                " where EmpId = " + EmpId +";";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        this.close();
        return cursorToEmpresas(cursor);
    }
    // Cursor to Empresa
    private Empresas cursorToEmpresas(Cursor cursor) {
        if (cursor.getCount() == 0) {
            return null;
        }
        Empresas mEmpresas = new Empresas();

        int i = 0;
        mEmpresas.setEmpId(cursor.getInt(i++));
        mEmpresas.setEmpRUT(cursor.getInt(i++));
        mEmpresas.setEmpRazonSocial(cursor.getString(i++));
        mEmpresas.setEmpDirCalle(cursor.getString(i++));
        mEmpresas.setEmpDirEsquina(cursor.getString(i++));
        mEmpresas.setEmpDirNum(cursor.getInt(i++));
        mEmpresas.setEmpDirEmail(cursor.getString(i++));
        mEmpresas.setEmpTelefono(cursor.getString(i++));
        mEmpresas.setEmpDescripcion(cursor.getString(i++));
        mEmpresas.setEmpActivo(cursor.getString(i++));
        mEmpresas.setEmpImagen(cursor.getString(i++));
        mEmpresas.setEmpRubro1(cursor.getInt(i++));
        mEmpresas.setEmpRubro2(cursor.getInt(i++));
        return mEmpresas;
    }

    private _SyncableGet syncable;

    private _SyncableGet getSyncCallback() {
        return syncable;
    }

    private void setSyncCallback(_SyncableGet sCallback) {
        this.syncable = sCallback;
    }

    public boolean syncGet(_SyncableGet mSyncable) {
        syncable = mSyncable;
        _WebServicesGet ws = new _WebServicesGet(_WebServicesGet._elegirServicio+"/"+_Utils.getRubroSeleccionado().getId(), this, "Empresas");
        ws.execute();
        return true;
    }

    @Override
    public boolean syncGetReturn(String tag, String out, _SyncableGetResponse sgr) {

        ObjectMapper om = new ObjectMapper();
        om.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JsonNode cJson = null; // JSON de coleccion de objetos
        JsonNode oJson = null; // JSON de objeto
        try {
            cJson = om.readTree(out);
            // si la coleccion es nula o de tamaño cero me voy
            if (cJson == null || cJson.size() == 0) {
                if (getSyncCallback() != null)
                    getSyncCallback().syncGetReturn("Empresas", out, sgr);
                return false;
            }

            this.open();
            for (int i = 0; i < cJson.size(); i++) {
                oJson = cJson.get(i);
                Empresas m;
                try {
                    m = om.readValue(oJson.toString(), Empresas.class);
                    createEmpresas(m);
                } catch (IOException e) {
                    Log.d("EmpresasDS : Lin225", e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            om = null;
            cJson = null;
            oJson = null;
        }
        this.close();
        if (syncable != null)
            getSyncCallback().syncGetReturn(tag, out, sgr);
        return true;
    }

}

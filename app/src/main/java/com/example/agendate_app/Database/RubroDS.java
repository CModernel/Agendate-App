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
public class RubroDS implements _SyncableGet {
    // Database fields
    private SQLiteDatabase database;
    private _DBHelper dbHelper;

    private String[] allVariedadesColumns = {"id", "rubroNom"};

    public RubroDS() {
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



    public boolean createRubro(String json) {
        ObjectMapper om = new ObjectMapper();
        try {
            createRubro((Rubro) om.readValue(json, Rubro.class));
            return true;
        } catch (IOException e) {
            Log.d("RubroDS : Lin67", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Create Rubro
    public boolean createRubro(Rubro o) {
        return createRubro(o.getId(), o.getRubroNom());
    }

    // Create Rubro
    public boolean createRubro(int id, String rubroNom) {
        ContentValues values = new ContentValues();
        values.put("id", id);
        if (rubroNom != null) values.put("rubroNom", rubroNom.trim());
        try {
            this.openW();
            if (database.replace("rubroNom", null, values) >= 0) {
                this.close();
                return true;
            } else {
                this.close();
                Log.w(RubroDS.class.getName(), "creando rubroNom: ERROR 1 - insert - " + "id = " + id + ", rubroNom = " + rubroNom.trim());
                return false;
            }
        } catch (Exception e) {
            this.close();
            Log.w(RubroDS.class.getName(), "creando rubroNom: ERROR 2 - insert - " + "id = " + id + ", VariedadId = " + rubroNom.trim() + " - " + e.getMessage());
            Log.d("RubroDS : Lin97", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Get All Variedades
    public List<Rubro> getAllVariedades() {
        List<Rubro> mRubros = new ArrayList<Rubro>();
        this.openR();

        String query = "select * from Rubros";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Rubro mRubro = cursorToRubro(cursor);
            mRubros.add(mRubro);
            cursor.moveToNext();
        }
        cursor.close();
        this.close();
        return mRubros;
    }

    // Get Rubro por clave
    public Rubro getRubros(int id) {
        this.openR();

        String query = "select * from Rubros" +
                " where id = " + id +";";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        this.close();
        return cursorToRubro(cursor);
    }

    // Cursor to Rubros
    private Rubro cursorToRubro(Cursor cursor) {
        if (cursor.getCount() == 0) {
            return null;
        }
        Rubro mRubro = new Rubro();

        // id, rubroNom
        mRubro.setId(cursor.getInt(0));
        mRubro.setRubroNom(cursor.getString(1));
        return mRubro;
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
        _WebServicesGet ws = new _WebServicesGet(_WebServicesGet._getRubros, this, "Rubros");
        ws.execute();
        return true;
    }

    @Override
    public boolean syncGetReturn(String tag, String out, _SyncableGetResponse sgr) {
        Log.i("SYNCRETURN RUBRO", "Largo retorno: " + out.length());
        // parsear json coleccion de objetos
        ObjectMapper om = new ObjectMapper();
        om.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // JsonNode rJson = null; // JSON de respuesta
        JsonNode cJson = null; // JSON de coleccion de objetos
        JsonNode oJson = null; // JSON de objeto
        try {
            // rJson = om.readTree(out).get("sdtWSRespuesta");
            // parseo coleccion
            cJson = om.readTree(out);
            // si la coleccion es nula o de tamaño cero me voy
            if (cJson == null || cJson.size() == 0) {
                if (getSyncCallback() != null)
                    getSyncCallback().syncGetReturn("Rubro", out, sgr);
                return false;
            }
            // abro db

            this.open();
            for (int i = 0; i < cJson.size(); i++) {
                oJson = cJson.get(i);
                // convertir cada objeto - String oJson
                Rubro m;
                try {
                    m = om.readValue(oJson.toString(), Rubro.class);
                    _Utils.addRubro(m);
                    //createRubro(m);
                } catch (IOException e) {
                    Log.d("RubroDS : Lin225", e.getMessage());
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
        if (syncable != null) syncable.syncGetReturn(tag, out, sgr);
        return true;
    }

}
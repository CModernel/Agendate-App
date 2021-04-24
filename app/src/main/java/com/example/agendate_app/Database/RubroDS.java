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

    private String[] allRubrosColumns = {"id", "rubroNom"};

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
            if (database.replace("Rubros", null, values) >= 0) {
                this.close();
                return true;
            } else {
                this.close();
                Log.w(RubroDS.class.getName(), "Creando Rubros: ERROR 1 - insert - " + "id = " + id + ", rubroNom = " + rubroNom.trim());
                return false;
            }
        } catch (Exception e) {
            this.close();
            Log.w(RubroDS.class.getName(), "Creando Rubros: ERROR 2 - insert - " + "id = " + id + ", rubroNom = " + rubroNom.trim() + " - " + e.getMessage());
            Log.d("RubroDS : Lin97", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Get All Rubros
    public List<Rubro> getAllRubros() {
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

    // Cursor to Rubro
    private Rubro cursorToRubro(Cursor cursor) {
        if (cursor.getCount() == 0) {
            return null;
        }
        Rubro mRubro = new Rubro();

        // id, rubroNom
        int i = 0;
        mRubro.setId(cursor.getInt(i++));
        mRubro.setRubroNom(cursor.getString(i++));
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
                    getSyncCallback().syncGetReturn("Rubro", out, sgr);
                return false;
            }

            this.open();
            for (int i = 0; i < cJson.size(); i++) {
                oJson = cJson.get(i);
                Rubro m;
                try {
                    m = om.readValue(oJson.toString(), Rubro.class);
                    createRubro(m);
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
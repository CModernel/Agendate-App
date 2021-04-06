package com.example.agendate_app.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sd.solidatec.wms.Utils._Log;
import com.sd.solidatec.wms.Utils._LoginStatus;
import com.sd.solidatec.wms.Utils._ProgressBar;
import com.sd.solidatec.wms.Utils._Utils;
import com.sd.solidatec.wms.Utils._WebServicesGet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("DefaultLocale")
public class VariedadDS implements _SyncableGet {
    // Database fields
    private SQLiteDatabase database;
    private _DBHelper dbHelper;

    private String[] allVariedadesColumns = {"EmpId", "VariedadId", "VariedadDsc", "VariedadModDT"};

    public VariedadDS() {
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

    private _SyncableGet syncable;

    private _SyncableGet getSyncCallback() {
        return syncable;
    }

    private void setSyncCallback(_SyncableGet sCallback) {
        this.syncable = sCallback;
    }

    public boolean createVariedad(String json) {
        ObjectMapper om = new ObjectMapper();
        try {
            createVariedad((Rubro) om.readValue(json, Rubro.class));
            return true;
        } catch (IOException e) {
            _Log.d("VariedadDS : Lin67", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Create Rubro
    public boolean createVariedad(Rubro o) {
        return createVariedad(o.getEmpId(), o.getVariedadId(), o.getVariedadDsc(), o.getVariedadModDT());
    }

    // Create Rubro
    public boolean createVariedad(int EmpId, String VariedadId, String VariedadDsc, String VariedadModDT) {
        ContentValues values = new ContentValues();
        values.put("EmpId", EmpId);
        if (VariedadId != null) values.put("VariedadId", VariedadId.trim());
        if (VariedadDsc != null) values.put("VariedadDsc", VariedadDsc.trim());
        if (VariedadModDT != null) values.put("VariedadModDT", VariedadModDT.trim());
        try {
            this.openW();
            if (database.replace("Variedades", null, values) >= 0) {
                this.close();
                return true;
            } else {
                this.close();
                _Log.w(VariedadDS.class.getName(), "creando Variedades: ERROR 1 - insert - " + "EmpId = " + EmpId + ", VariedadId = " + VariedadId.trim());
                return false;
            }
        } catch (Exception e) {
            this.close();
            _Log.w(VariedadDS.class.getName(), "creando Variedades: ERROR 2 - insert - " + "EmpId = " + EmpId + ", VariedadId = " + VariedadId.trim() + " - " + e.getMessage());
            _Log.d("VariedadDS : Lin98", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Get All Variedades
    public List<Rubro> getAllVariedades() {
        List<Rubro> mVariedades = new ArrayList<Rubro>();
        this.openR();

        String query = "select * from Variedades where EmpId = " + _LoginStatus.getEmpId();

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Rubro mRubro = cursorToVariedad(cursor);
            mVariedades.add(mRubro);
            cursor.moveToNext();
        }
        cursor.close();
        this.close();
        return mVariedades;
    }

    // Get Rubro por clave
    public Rubro getVariedad(String variedadId) {
        this.openR();

        String query = "select * from Variedades" +
                " where EmpId = " + _LoginStatus.getEmpId() +
                " and VariedadId = '" + variedadId.trim() + "'";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        this.close();
        return cursorToVariedad(cursor);
    }

    // Get ultimo ModDT
    public String getUltimoModDT(boolean doModDT) {
        if (!doModDT) return "1900-01-01T00:00:00";
        try {
            String ultimoModDT = "";
            this.openR();
            Cursor cursor = database.rawQuery("select VariedadModDT from Variedades where EmpId = " + _LoginStatus.getEmpId() + " order by EmpId, VariedadModDT desc limit 1", null);
            cursor.moveToFirst();
            String moddt = cursor.getString(0);
            this.close();
            cursor.close();
            return moddt;
        } catch (Exception e) {
            return "1900-01-01T00:00:00";
        }
    }

    // Delete Rubro por clave
    public boolean deleteVariedad(int EmpId, String VariedadId) {
        this.openW();
        boolean ret = database.delete("Variedades", "EmpId = " + EmpId + " and VariedadId = '" + VariedadId.trim() + "'", null) > 0;
        this.close();
        return ret;
    }

    // Delete All Variedades
    public boolean deleteAllVariedad() {
        this.openW();
        database.execSQL("delete from Variedades");
        this.close();
        return true;
    }

    // Cursor to Variedades
    private Rubro cursorToVariedad(Cursor cursor) {
        if (cursor.getCount() == 0) {
            return null;
        }
        Rubro mRubro = new Rubro();
        // EmpId, VariedadId, VariedadDsc, VariedadModDT,
        mRubro.setEmpId(cursor.getInt(0));
        mRubro.setVariedadId(cursor.getString(1));
        mRubro.setVariedadDsc(cursor.getString(2));
        mRubro.setVariedadModDT(cursor.getString(3));
        return mRubro;
    }

    public boolean syncGet(_SyncableGet mSyncable, boolean doModDT) {
        syncable = mSyncable;
        _WebServicesGet ws = new _WebServicesGet(_WebServicesGet._getVariedades, this, "Variedades", getUltimoModDT(doModDT));
        ws.execute();
        return true;
    }

    @Override
    public boolean syncGetReturn(String tag, String out, _SyncableGetResponse sgr) {
        _Log.i("SYNCRETURN VARIEDAD", "Largo retorno: " + out.length());
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
            cJson = om.readTree(out).get("sdtColVariedades");
            // si la coleccion es nula o de tamaño cero me voy
            if (cJson == null || cJson.size() == 0) {
                if (getSyncCallback() != null)
                    getSyncCallback().syncGetReturn("Variedades", out, sgr);
                return false;
            }
            // abro db
            _ProgressBar.show2(cJson.size());
            this.open();
            for (int i = 0; i < cJson.size(); i++) {
                _ProgressBar.updateAdd2(1);
                oJson = cJson.get(i);
                // convertir cada objeto - String oJson
                Rubro m;
                try {
                    m = om.readValue(oJson.toString(), Rubro.class);
                    createVariedad(m);
                } catch (IOException e) {
                    _Log.d("VariedadDS : Lin225", e.getMessage());
                    e.printStackTrace();
                }
            }
            _ProgressBar.reset2();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            om = null;
            // rJson = null;
            cJson = null;
            oJson = null;
        }
        this.close();
        if (syncable != null) syncable.syncGetReturn(tag, out, sgr);
        return true;
    }

}
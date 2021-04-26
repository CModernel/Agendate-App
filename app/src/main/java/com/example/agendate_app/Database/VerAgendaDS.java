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
public class VerAgendaDS {

    // Database fields
    private SQLiteDatabase database;
    private _DBHelper dbHelper;

    private String[] allVerAgendaColumns = {"id", "FechaSolicitud", "HoraSolicitud", "SeConcreto",
            "ComentarioAdmin", "ComentarioUsuario", "SolicitudActivo", "UsuId", "EmpId",
            "UsuAdminResponsable"};

    public VerAgendaDS() {
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

    // crear VerAgenda
    public boolean VerAgendaDS(VerAgenda o) {
        return createVerAgenda(o.getId(), o.getFechaSolicitud(),o.getHoraSolicitud(),o.getSeConcreto(),
                o.getComentarioAdmin(),o.getComentarioUsuario(),o.getSolicitudActivo(),o.getUsuId(),
                o.getEmpId(), o.getUsuAdminResponsable());
    }

    private boolean createVerAgenda(Integer id, String fechaSolicitud, String horaSolicitud,
                                    String seConcreto, String comentarioAdmin,
                                    String comentarioUsuario, String solicitudActivo, Integer usuId,
                                    Integer empId, Integer usuAdminResponsable) {

        ContentValues values = new ContentValues();
        values.put("id", id);
        return false;
    }
    // Get All VerAgenda
    public List<VerAgenda> getAllVerAgenda(Integer id) {
        List<VerAgenda> mVerAgenda = new ArrayList<VerAgenda>();
        this.openR();

        String query = "select * from VerAgenda";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            VerAgenda VerAgendas = cursorToVerAgenda(cursor);
            mVerAgenda.add(VerAgendas);
            cursor.moveToNext();
        }
        cursor.close();
        this.close();
        return mVerAgenda;
    }

    // Get All mVerAgenda
    public List<VerAgenda> getAllmVerAgenda(int id) {
        List<VerAgenda> mVerAgenda = new ArrayList<VerAgenda>();
        this.openR();

        String query = "select * from VerAgenda where " +
                "id = " + id + " ;";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            VerAgenda mVerAgendas = cursorToVerAgenda(cursor);
            mVerAgenda.add(mVerAgendas);
            cursor.moveToNext();
        }
        cursor.close();
        this.close();
        return mVerAgenda;
    }

    // Get VerAgenda por clave
    public VerAgenda getVerAgenda(int id) {
        this.openR();

        String query = "select * from VerAgenda" +
                " where id = " + id +";";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        this.close();
        return cursorToVerAgenda(cursor);
    }


    // Cursor to VerAgenda
    private VerAgenda cursorToVerAgenda(Cursor cursor) {
        if (cursor.getCount() == 0) {
            return null;
        }
        VerAgenda mVerAgenda = new VerAgenda();

        int i = 0;
        mVerAgenda.setId(cursor.getInt(i++));
        mVerAgenda.setFechaSolicitud(cursor.getString(i++));
        mVerAgenda.setHoraSolicitud(cursor.getString(i++));
        mVerAgenda.setSeConcreto(cursor.getString(i++));
        mVerAgenda.setComentarioAdmin(cursor.getString(i++));
        mVerAgenda.setComentarioUsuario(cursor.getString(i++));
        mVerAgenda.setSolicitudActivo(cursor.getString(i++));
        mVerAgenda.setUsuId(cursor.getInt(i++));
        mVerAgenda.setEmpId(cursor.getInt(i++));
        mVerAgenda.setUsuAdminResponsable(cursor.getInt(i++));
        return mVerAgenda;
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
        String urlIn = _WebServicesGet._elegirServicio+"/"+_Utils.getRubroSeleccionado().getId();
        _WebServicesGet ws = new _WebServicesGet(urlIn, (_SyncableGet) this, "VerAgenda");
        ws.execute();
        return true;
    }


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
                    getSyncCallback().syncGetReturn("VerAgenda", out, sgr);
                return false;
            }

            this.open();
            for (int i = 0; i < cJson.size(); i++) {
                oJson = cJson.get(i);
                VerAgenda m;
                try {
                    m = om.readValue(oJson.toString(), VerAgenda.class);
                    _Utils.setVerAgenda(m);
                } catch (IOException e) {
                    Log.d("VerAgendaDS : Lin225", e.getMessage());
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

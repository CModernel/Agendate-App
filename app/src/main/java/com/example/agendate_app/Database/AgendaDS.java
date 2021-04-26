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
public class AgendaDS implements _SyncableGet {

    // Database fields
    private SQLiteDatabase database;
    private _DBHelper dbHelper;

    private String[] allAgendaColumns = {"id", "FechaSolicitud", "HoraSolicitud", "SeConcreto",
            "ComentarioAdmin", "ComentarioUsuario", "SolicitudActivo", "UsuId", "EmpId",
            "UsuAdminResponsable"};

    public AgendaDS() {
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


    // crear Agenda
    public boolean createAgenda(Agenda o) {
        return createAgenda(o.getId(), o.getFechaSolicitud(),o.getHoraSolicitud(),o.getSeConcreto(),
                o.getComentarioAdmin(),o.getComentarioUsuario(),o.getSolicitudActivo(),o.getUsuId(),
                o.getEmpId(), o.getUsuAdminResponsable());
    }

    private boolean createAgenda(Integer id, String fechaSolicitud, String horaSolicitud,
                                 String seConcreto, String comentarioAdmin,
                                 String comentarioUsuario, boolean solicitudActivo, Integer usuId,
                                 Integer empId, Integer usuAdminResponsable) {

        ContentValues values = new ContentValues();
        values.put("id", id);
        if(fechaSolicitud!=null) values.put("fechaSolicitud", fechaSolicitud);
        if(horaSolicitud!=null) values.put("horaSolicitud", horaSolicitud);
        if(seConcreto!=null) values.put("seConcreto", seConcreto);
        if(comentarioAdmin!=null) values.put("comentarioAdmin", comentarioAdmin);
        if(comentarioUsuario!=null) values.put("comentarioUsuario", comentarioUsuario);
        values.put("solicitudActivo", solicitudActivo);
        values.put("usuId", usuId);
        values.put("empId", empId);
        values.put("usuAdminResponsable", usuAdminResponsable);
        try {
            this.openW();
            if (database.replace("Agenda", null, values) >= 0) {
                this.close();
                return true;
            } else {
                this.close();
                Log.w(RubroDS.class.getName(), "Creando Agenda: ERROR 1 - insert - " + "id = " + id );
                return false;
            }
        } catch (Exception e) {
            this.close();
            Log.w(RubroDS.class.getName(), "Creando Agenda: ERROR 2 - insert - " + "id = " + id + " - " + e.getMessage());
            Log.d("Agenda : Lin90", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    // Get All Agenda
    public List<Agenda> getAllAgendas() {
        List<Agenda> mAgendas = new ArrayList<Agenda>();
        this.openR();

        String query = "select * from Agenda";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Agenda mAgenda = cursorToAgenda(cursor);
            mAgendas.add(mAgenda);
            cursor.moveToNext();
        }
        cursor.close();
        this.close();
        return mAgendas;
    }

    // Get All Agenda
    public List<Agenda> getAllAgendasActivas() {
        List<Agenda> mAgendas = new ArrayList<Agenda>();
        this.openR();

        String query = "select * from Agenda where SolicitudActivo = 1";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Agenda mAgenda = cursorToAgenda(cursor);
            mAgendas.add(mAgenda);
            cursor.moveToNext();
        }
        cursor.close();
        this.close();
        return mAgendas;
    }

    // Get All mAgenda
    public List<Agenda> getAllmAgenda(int id) {
        List<Agenda> mAgendas = new ArrayList<Agenda>();
        this.openR();

        String query = "select * from Agenda where " +
                "id = " + id + " ;";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Agenda mAgenda = cursorToAgenda(cursor);
            mAgendas.add(mAgenda);
            cursor.moveToNext();
        }
        cursor.close();
        this.close();
        return mAgendas;
    }

    // Get Agenda por clave
    public Agenda getAgenda(int id) {
        this.openR();

        String query = "select * from Agenda" +
                " where id = " + id +";";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        this.close();
        return cursorToAgenda(cursor);
    }

    public boolean bajaAgendaLocal(int id){
        this.openW();
        database.execSQL("UPDATE Agenda SET SolicitudActivo = 0 WHERE id = " + id);
        this.close();
        return true;
    }

    // Cursor to Agenda
    private Agenda cursorToAgenda(Cursor cursor) {
        if (cursor.getCount() == 0) {
            return null;
        }
        Agenda mAgenda = new Agenda();

        int i = 0;
        mAgenda.setId(cursor.getInt(i++));
        mAgenda.setFechaSolicitud(cursor.getString(i++));
        mAgenda.setHoraSolicitud(cursor.getString(i++));
        mAgenda.setSeConcreto(cursor.getString(i++));
        mAgenda.setComentarioAdmin(cursor.getString(i++));
        mAgenda.setComentarioUsuario(cursor.getString(i++));
        if(cursor.getInt(i++)==1)
            mAgenda.setSolicitudActivo(true);
        else
            mAgenda.setSolicitudActivo(false);
        mAgenda.setUsuId(cursor.getInt(i++));
        mAgenda.setEmpId(cursor.getInt(i++));
        mAgenda.setUsuAdminResponsable(cursor.getInt(i++));
        return mAgenda;
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
        String urlIn = _WebServicesGet._verAgenda+"/"+_Utils.UsuId;
        _WebServicesGet ws = new _WebServicesGet(urlIn, (_SyncableGet) this, "Agendas");
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
                    getSyncCallback().syncGetReturn("Agendas", out, sgr);
                return false;
            }

            this.open();
            for (int i = 0; i < cJson.size(); i++) {
                oJson = cJson.get(i);
                Agenda m;
                try {
                    m = om.readValue(oJson.toString(), Agenda.class);
                    createAgenda(m);
                } catch (IOException e) {
                    Log.d("AgendaDS : Lin225", e.getMessage());
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

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
import com.example.agendate_app.Database._SyncableGetResponse;
import com.example.agendate_app.Interfaces._SyncableGet;


import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.sql.Date.valueOf;
import static java.util.Date.*;

@SuppressLint("DefaultLocale")
public class SolicitudEmpresaDS implements _SyncableGet{

    private SQLiteDatabase database;
    private _DBHelper dbHelper;

    private String[] allSolicitudEmpresaColumns = {"EmpId", "Fecha", "HorarioSolicitud"};

    public SolicitudEmpresaDS() {
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


    public boolean createSolicitudEmpresa(SolicitudEmpresa o) {
        return (boolean) createSolicitudEmpresa(o.getEmpId(), o.getFecha(),o.getHorarioSolicitud());
    }
    // crear solicitud de empresas
    @SuppressLint("LongLogTag")
    public Object createSolicitudEmpresa(int EmpId, Date Fecha, Time HorarioSolicitud) {
        ContentValues values = new ContentValues();
        values.put("EmpId", EmpId);
        if (Fecha != null) values.put("Fecha", Fecha.toString());

        try {
            this.openW();
            if (database.replace("Fecha", null, values) >= 0) {
                this.close();
                return true;
            } else {
                this.close();
                Log.w(SolicitudEmpresa.class.getName(),
                        "Creando solicitud: ERROR 1 - insert - " + "EmpId = " + EmpId + ", " +
                                "Fecha = " + Fecha.toString() + ", " +
                                "HorarioSolicitud = " + HorarioSolicitud.toString() );
                return false;
            }
        } catch (Exception e) {
            this.close();
            Log.w(SolicitudEmpresaDS.class.getName(),
                    "Creando SolicitudEmpresa: ERROR 2 - insert - "
                            + "EmpId = " + EmpId + ", VariedadId = " +
                            Fecha.toString() + " - " + e.getMessage());
            //Log.d("SolicitudEmpresaDS : Lin97", e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    private SolicitudEmpresa cursorToSolicitudEmpresa(Cursor cursor) {
        if (cursor.getCount() == 0) {
            return null;
        }
        SolicitudEmpresa mSolicitudEmpresas = new SolicitudEmpresa();


        int i = 0;
        mSolicitudEmpresas.setEmpId(cursor.getInt(i++));
        mSolicitudEmpresas.setFecha(valueOf(cursor.getString(i++)));
        mSolicitudEmpresas.setHorarioSolicitud(Time.valueOf(cursor.getString(i++)));
        return mSolicitudEmpresas;
    }

    public List<SolicitudEmpresa> getHorarioPorSolicitudEmpresa(SolicitudEmpresa solicitudEmpresa){
        List<SolicitudEmpresa> retorno = new ArrayList<SolicitudEmpresa>();
        SolicitudEmpresa solEmp;
        for (String hora: solicitudEmpresa.getHorario()) {
            solEmp = new SolicitudEmpresa();
            solEmp.setHorario(new String[]{hora});
            for (String solicitud: solicitudEmpresa.getSolicitudes()) {
                if(hora.equals(solicitud))
                    solEmp.setSolicitudes(new String[]{solicitud});
            }
            for (String horarioVencido: solicitudEmpresa.getHorariosVencidos()) {
                if(hora.equals(horarioVencido))
                    solEmp.setHorariosVencidos(new String[]{horarioVencido});
            }
            retorno.add(solEmp);
        }
        return retorno;
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
        String fecha = "2021-04-26";
        String urlIn = _WebServicesGet._elegirHorario+"/"+_Utils.getEmpresaSeleccionada().getEmpId()+"/"+fecha;
        _WebServicesGet ws = new _WebServicesGet(urlIn, this, "SolicitudEmpresa");
        ws.execute();
        return true;
    }

    @Override
    public boolean syncGetReturn(String tag, String out, _SyncableGetResponse sgr) {
        ObjectMapper om = new ObjectMapper();
        om.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //String out2 = "[" + out + "]";
        JsonNode cJson = null; // JSON de coleccion de objetos
        JsonNode oJson = null; // JSON de objeto
        try {
            cJson = om.readTree(out);
            // si la coleccion es nula o de tamaño cero me voy
            if (cJson == null || cJson.size() == 0) {
                return false;
            }

            this.open();
            for (int i = 0; i < cJson.size(); i++) {
                oJson = cJson.get(i);
                SolicitudEmpresa m;
                try {
                    m = om.readValue(oJson.toString(), SolicitudEmpresa.class);
                    _Utils.setSolicitudesEmpresa(m);
                } catch (IOException e) {
                    //Log.d("SolicitudEmpresaDS : Lin225", e.getMessage());
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



    public SolicitudEmpresa getSolicitudEmpresa(Integer empId) {
        this.openR();

        String query = "select * from SolicitudEmpresa" +
                " where EmpId = " + empId +";";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        this.close();
        return cursorToSolicitudEmpresa(cursor);
    }

    // Get All Solicitudes
    public List<SolicitudEmpresa> getAllSolicitudEmpresa() {
        List<SolicitudEmpresa> mSolicitudEmpresas = new ArrayList<SolicitudEmpresa>();
        this.openR();

        String query = "select * from SolicitudEmpresa";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SolicitudEmpresa mSolicitudEmpresa = cursorToSolicitudEmpresa(cursor);
            mSolicitudEmpresas.add(mSolicitudEmpresa);
            cursor.moveToNext();
        }
        cursor.close();
        this.close();
        return mSolicitudEmpresas;
    }
}

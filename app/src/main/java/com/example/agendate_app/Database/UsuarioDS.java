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
public class UsuarioDS implements _SyncableGet {
    // Database fields
    private SQLiteDatabase database;
    private _DBHelper dbHelper;

    private String[] allUsuarioColumns = {"username", "first_name", "last_name", "email",};

    public UsuarioDS() {
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

    // Create Usuario
    public boolean CreateUsuario(Usuario o) {
        return CreateUsuario(o.getUsername(), o.getFirst_name(), o.getLast_name(), o.getEmail());
    }

    // Create Usuario
    public boolean CreateUsuario(String username, String firstName, String lastName, String email) {
        ContentValues values = new ContentValues();
        if (username != null) values.put("username", username.trim());
        if (firstName != null) values.put("first_name", firstName.trim());
        if (lastName != null) values.put("last_name", lastName.trim());
        if (email != null) values.put("email", email.trim());

        try {
            this.openW();
            if (database.replace("Usuarios", null, values) >= 0) {
                this.close();
                return true;
            } else {
                this.close();
                Log.w(UsuarioDS.class.getName(), "Creando Usuario: ERROR 1 - insert - " + "username = " + username);
                return false;
            }
        } catch (Exception e) {
            this.close();
            Log.w(UsuarioDS.class.getName(), "Creando Usuario: ERROR 2 -  insert - " + "username = " + username + e.getMessage());
            Log.d("UsuarioDS : Lin76", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Get All Usuarios
    public List<Usuario> getAllUsuarios() {
        List<Usuario> mUsuarios = new ArrayList<Usuario>();
        this.openR();

        String query = "select * from Usuarios";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Usuario mUsuario = cursorToUsuario(cursor);
            mUsuarios.add(mUsuario);
            cursor.moveToNext();
        }
        cursor.close();
        this.close();
        return mUsuarios;
    }

    // Get Usuario por clave
    public Usuario getUsuario(String username) {
        this.openR();

        String query = "select * from Usuario" +
                " where username = " + username +";";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        this.close();
        return cursorToUsuario(cursor);
    }

    // Cursor to Usuario
    private Usuario cursorToUsuario(Cursor cursor) {
        if (cursor.getCount() == 0) {
            return null;
        }
        Usuario mUsuario = new Usuario();

        int i = 0;
        mUsuario.setUsername(cursor.getString(i++));
        mUsuario.setFirst_name(cursor.getString(i++));
        mUsuario.setLast_name(cursor.getString(i++));
        mUsuario.setEmail(cursor.getString(i++));
        return mUsuario;
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
        String urlIn = _WebServicesGet._verMiPerfil+"/"+_Utils.UsuId;
        _WebServicesGet ws = new _WebServicesGet(urlIn, this, "Usuario");
        ws.execute();
        return true;
    }

    @Override
    public boolean syncGetReturn(String tag, String out, _SyncableGetResponse sgr) {

        ObjectMapper om = new ObjectMapper();
        om.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String out2 = "[" + out + "]";
        JsonNode cJson = null; // JSON de coleccion de objetos
        JsonNode oJson = null; // JSON de objeto
        try {
            cJson = om.readTree(out2);
            // si la coleccion es nula o de tamaño cero me voy
            if (cJson == null || cJson.size() == 0) {
                if (getSyncCallback() != null)
                    getSyncCallback().syncGetReturn("Usuario", out, sgr);
                return false;
            }

            this.open();
            for (int i = 0; i < cJson.size(); i++) {
                oJson = cJson.get(i);
                Usuario m;
                try {
                    m = om.readValue(oJson.toString(), Usuario.class);
                    CreateUsuario(m);
                } catch (IOException e) {
                    Log.d("UsuarioDS : Lin225", e.getMessage());
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
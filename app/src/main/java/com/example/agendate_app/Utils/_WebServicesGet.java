package com.example.agendate_app.Utils;

import android.os.AsyncTask;
import android.util.Log;

import com.example.agendate_app.Database.RubroDS;
import com.example.agendate_app.Database._SyncableGetResponse;
import com.example.agendate_app.Interfaces._SyncableGet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

import static java.lang.Thread.sleep;


public class _WebServicesGet extends AsyncTask<String, Void, String> {

    //v1
    public static String _getRubros = "getAllRubrosV1";

    private String urlIn = "http://agendate.pythonanywhere.com/api/";
    // url de donde voy a sincronizar
    //private _SyncableGet syncableAll;   // si estoy sincronizando todos los tipos, a quien le doy el retorno
    private _SyncableGet syncableUnico; // si estoy sincronizando un solo tipo, a quien le doy el retorno
    private String tag;                 // tag identificando el tipo de objeto que sincronizo
    //private String dtOper;              // fecha desde la ultima sincronizacion
    //private String dtUsuario;           // fecha desde del usuario

    //private String jsonBodyIn;          // opcional, body del request, en JSON, opcional

    // auxiliares
    //private Object objeto1;

    public _WebServicesGet(String urlIn, _SyncableGet syncable, String tag) {
        // constructor para objetos que no tienen/requieren moddt
        super();
        this.urlIn += urlIn;
        this.syncableUnico = syncable;
        this.tag = tag;
        //this.dtOper = "";
        //this.jsonBodyIn = "";
    }

    public _WebServicesGet(String urlIn, _SyncableGet syncable, String tag, String dtOper, String identificador1, String identificador2, String identificador3, String identificador4, String identificador5) {
        // constructor para objetos que tienen/requieren moddt o identificadores adicionales
        super();
        this.urlIn += urlIn;
        this.syncableUnico = syncable;
        this.tag = tag;
        //this.dtOper = dtOper;
        //this.jsonBodyIn = "";
        //this.objeto1 = null;
        //this.dtUsuario = _Utils.ahora();
    }

    public _WebServicesGet(String urlIn, _SyncableGet syncable, String tag, String dtOper, String jsonBodyIn, Object objeto1) {
        // constructor para objetos que tienen/requieren moddt o identificadores adicionales
        super();
        this.urlIn += urlIn;
        this.syncableUnico = syncable;
        this.tag = tag;
        //this.dtOper = dtOper;
        //this.jsonBodyIn = jsonBodyIn;
        //this.objeto1 = objeto1;
        //this.dtUsuario = _Utils.ahora();
    }

    @Override
    protected String doInBackground(String... args) {

        String strOut = "";
        strOut = httpPost().trim();

        int intentos = 0;
        while(intentos < 10 && (strOut == null || strOut.length() == 0 || strOut.isEmpty())){
            if (strOut.equalsIgnoreCase("SocketTimeoutException")) {
                try {
                    sleep(1000);
                    Log.i("SYNCRETURN Intento 2", tag);
                    //_ProgressBar.update(tag + " - Error\nIntento 2 de 10...");
                    strOut = httpPost().trim();
                } catch (InterruptedException e) {
                    Log.d("WebServicesGet : Lin156", e.getMessage());
                    e.printStackTrace();
                }
            }
            intentos++;
        }

        _SyncableGetResponse sgr = new _SyncableGetResponse();
        sgr.tag = tag;
        sgr.out = strOut;
        //sgr.objeto1 = objeto1;

        //_ProgressBar.update("Actualizando " + tag + "...");

        syncableUnico.syncGetReturn(tag, strOut, sgr);
        return strOut;
    }

    public String httpPost() {
        BufferedReader in = null;
        HttpURLConnection huc = null;
        String inputLine;
        String strOut;

        // TODO: ir a buscar a donde corresponda: TOKEN
        //String UsuId = _LoginStatus.getUsuId();
        //String EmpId = Integer.toString(_LoginStatus.getEmpId());
        //String Pass = _LoginStatus.getUsuPass();
        //String DeviceId = "1";
        //String Imei = _Utils.getImei();
        //String Token = "lite.1234";
        //String Plataforma = "M";
        //String ModuloMobile = _Utils.moduloMobile;

        //_ProgressBar.update("Descargando " + tag + "...");

        try {
            URL url = new URL(urlIn);
            strOut = "";
            huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("GET");
            huc.setRequestProperty("Connection", "Close");
            //huc.setRequestProperty("UsuId", UsuId);
            //huc.setRequestProperty("EmpId", EmpId);
            //huc.setRequestProperty("Pass", Pass);
            //huc.setRequestProperty("DeviceId", DeviceId);
            //huc.setRequestProperty("Imei", Imei);
            //huc.setRequestProperty("Token", Token);
            //huc.setRequestProperty("Plataforma", Plataforma);
            //huc.setRequestProperty("ModuloMobile", ModuloMobile);
            //if (dtOper != null && !dtOper.equals(""))
            //    huc.setRequestProperty("DtOper", dtOper);
            //if (dtUsuario != null && !dtUsuario.equals(""))
            //    huc.setRequestProperty("dtUsuario", dtUsuario);

            /*
            if (jsonBodyIn != null && jsonBodyIn.length() > 0) {

                // send data
                huc.setRequestProperty("Content-Type", "application/json");
                huc.setRequestProperty("Content-Length", Integer.toString(jsonBodyIn.getBytes().length));
                huc.setDoInput(true);
                huc.setDoOutput(true);
                DataOutputStream dos = new DataOutputStream(huc.getOutputStream());
                dos.write(jsonBodyIn.getBytes("UTF-8"));
                dos.flush();
                dos.close();
            }
            */
            huc.setConnectTimeout(10 * 1000);

            in = new BufferedReader(new InputStreamReader(huc.getInputStream()));

            while ((inputLine = in.readLine()) != null) {
                if (strOut.equalsIgnoreCase("")) {
                    strOut = inputLine;
                } else {
                    // strOut = strOut + "\n" + inputLine;
                    strOut = strOut + inputLine;
                }
            }
            //_LoginStatus.setServidorEnLinea(true);
        } catch (ConnectException ce) {
            //_LoginStatus.setServidorEnLinea(false);
            strOut = "ConnectException";
            Log.d("WebServicesGet : Lin413", ce.getMessage());
            ce.printStackTrace();
        } catch (SocketTimeoutException ste) {
            //_LoginStatus.setServidorEnLinea(false);
            strOut = "SocketTimeoutException";
            Log.d("WebServicesGet : Lin413", ste.getMessage());
            ste.printStackTrace();
        } catch (Exception e) {
            //_LoginStatus.setServidorEnLinea(false);
            strOut = "";
            Log.d("WebServicesGet : Lin423", e.getMessage());
            e.printStackTrace();
        }
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            Log.d("WebServicesGet : Lin431", e.getMessage());
            e.printStackTrace();
        }
        if (huc != null) huc.disconnect();
        in = null;
        huc = null;

        return strOut;
    }
/*
    public int syncAll(_SyncableGet syncable, boolean doModDT) {
        syncableAll = syncable;
        int iRet = 0;

        // Rubros
        Log.d("_WebServiceGet:syncAll", " disparo Rubros");
        new RubroDS().syncGet(this, doModDT);
        iRet++;

        return iRet;
    }

    @Override
    public boolean syncGetReturn(String tag, String out, _SyncableGetResponse sgr) {
        syncableAll.syncGetReturn(tag, out, sgr);
        return true;
    }

 */
}
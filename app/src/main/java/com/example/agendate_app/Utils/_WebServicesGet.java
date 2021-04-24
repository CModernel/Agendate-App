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

    public static String _getRubros = "getAllRubrosV1";
    public static String _getEmpresas = "getAllEmpresasV1";
    public static String _elegirServicio = "elegirServicioV1";
    public static String _elegirHorario = "elegirHorarioV1";

    private String urlIn = "http://agendate.pythonanywhere.com/api/";

    private _SyncableGet syncableUnico;
    private String tag;  // tag identificando el tipo de objeto que sincronizo


    public _WebServicesGet(String urlIn, _SyncableGet syncable, String tag) {
        super();
        this.urlIn += urlIn;
        this.syncableUnico = syncable;
        this.tag = tag;
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

        syncableUnico.syncGetReturn(tag, strOut, sgr);
        return strOut;
    }

    public String httpPost() {
        BufferedReader in = null;
        HttpURLConnection huc = null;
        String inputLine;
        String strOut;

        try {
            URL url = new URL(urlIn);
            strOut = "";
            huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("GET");
            huc.setRequestProperty("Connection", "Close");
            huc.setConnectTimeout(10 * 1000);

            in = new BufferedReader(new InputStreamReader(huc.getInputStream()));

            while ((inputLine = in.readLine()) != null) {
                if (strOut.equalsIgnoreCase("")) {
                    strOut = inputLine;
                } else {
                    strOut = strOut + inputLine;
                }
            }
        } catch (ConnectException ce) {
            strOut = "ConnectException";
            Log.d("WebServicesGet : Lin413", ce.getMessage());
            ce.printStackTrace();
        } catch (SocketTimeoutException ste) {
            strOut = "SocketTimeoutException";
            Log.d("WebServicesGet : Lin413", ste.getMessage());
            ste.printStackTrace();
        } catch (Exception e) {
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
}
package com.example.agendate_app.Utils;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncLogin extends AsyncTask<String, Void, String> {

    private Exception exception;

    protected String doInBackground(String... urls) {
        try {
            HttpConecction httpConecct = new HttpConecction();
            httpConecct.executeConecction(urls[0]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "OK";
    }

    private void doConnect2(String urlCompleto) throws IOException {
        URL url = new URL(urlCompleto);
        //URL url = new URL(myurl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Content-Type","application/json");
        //getFullCSRFcookie() returns the full cookie string with time stamps and everything
        urlConnection.setRequestProperty("Cookie", getFullCSRFcookie());
        urlConnection.connect();
    }

    private String getFullCSRFcookie(){
        return "csrftoken=WEglCG22Adp47j2dvbPXbBdp0aiQuOfP6wL1XFbvy8lLa0r1uabcXkRTGIhCF0VO; expires=Fri, 01 Apr 2022 01:03:56 GMT; Max-Age=31449600; Path=/; SameSite=Lax";
    }

    protected void onPostExecute(String feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }
}

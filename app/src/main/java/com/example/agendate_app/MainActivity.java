package com.example.agendate_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.agendate_app.Utils._Utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btn_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        _Utils.setActivity(this);
        _Utils.setContext(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_Login = (Button) findViewById(R.id.btn_login);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _Utils.fragment(new MainFragment());
            }
        });
    }
}
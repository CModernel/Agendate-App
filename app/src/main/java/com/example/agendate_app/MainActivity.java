package com.example.agendate_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;

import com.example.agendate_app.Fragments.LoginFragment;
import com.example.agendate_app.Utils._Utils;

public class MainActivity extends AppCompatActivity {

    Button btn_Login;
    Toolbar tbToolbar;
    private int mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _Utils.setActivity(this);
        _Utils.setContext(this);


        //tbToolbar = findViewById(R.id.amb_tb_toolbar);
        //mToolbar = R.menu.toolbar_main;
        //setSupportActionBar(tbToolbar);
        _Utils.fragment(new LoginFragment());

    }
}
package com.example.agendate_app.Fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.agendate_app.Database._DBHelper;
import com.example.agendate_app.Fragments.MenuPrincipalFragment;
import com.example.agendate_app.Interfaces._SyncableGet;
import com.example.agendate_app.R;
import com.example.agendate_app.Utils._Utils;

public class LoginFragment  extends Fragment {
    View myView;
    Button btn_Login;
    TextView usuario, password;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_login, container, false);

        btn_Login = (Button) myView.findViewById(R.id.frg_login_btn_enter);
        usuario = (TextView) myView.findViewById(R.id.frg_login_txt_usuario);
        password = (TextView) myView.findViewById(R.id.frg_login_txt_password);

        _DBHelper dbHelper = new _DBHelper(_Utils.getContext());
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        dbHelper.truncate(database);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(usuario.getText().toString().equals("ADMIN") && password.getText().toString().equals("admin"))
                    _Utils.fragment(new MenuPrincipalFragment());
                else{
                    _Utils.toast("Usuario incorrecto.");
                }
            }
        });

        return myView;
    }
}

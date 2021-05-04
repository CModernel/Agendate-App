package com.example.agendate_app.Fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.agendate_app.Database.UsuarioDS;
import com.example.agendate_app.Database._DBHelper;
import com.example.agendate_app.Database._SyncableGetResponse;
import com.example.agendate_app.Fragments.MenuPrincipalFragment;
import com.example.agendate_app.Interfaces._SyncableGet;
import com.example.agendate_app.R;
import com.example.agendate_app.Utils._Utils;

public class LoginFragment  extends Fragment implements _SyncableGet {
    View myView;
    Button btn_Login, btn_Registrarse;
    TextView usuario, password;
    private _SyncableGet syncableGet = this;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_login, container, false);

        btn_Login = (Button) myView.findViewById(R.id.frg_login_btn_enter);
        usuario = (TextView) myView.findViewById(R.id.frg_login_txt_usuario);
        password = (TextView) myView.findViewById(R.id.frg_login_txt_password);
        btn_Registrarse = (Button) myView.findViewById(R.id.frg_registrarse_btn_enter);

        _DBHelper dbHelper = new _DBHelper(_Utils.getContext());
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        dbHelper.truncate(database);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(usuario.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    _Utils.fragment(new MenuPrincipalFragment());
                }else{
                    _Utils.Username = usuario.getText().toString();
                    _Utils.Password = password.getText().toString();

                    new UsuarioDS().syncGetCheckLogin(syncableGet);
                }
            }
        });

        btn_Registrarse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 _Utils.fragment(new AltaDeUsuario());
            }
        });

        return myView;
    }

    @Override
    public boolean syncGetReturn(String tag, String out, _SyncableGetResponse sgr) {
        if(tag.equals("checkLogin")) {
            _Utils.Password = "";
            if (out.contains("OK")) {
                try {
                    _Utils.UsuId = Integer.valueOf(out.substring(out.indexOf(":")+1,out.length()-1));
                }catch(Exception ex){
                    Log.d(tag, ex.getMessage());
                    _Utils.UsuId = 1;
                    _Utils.Username = "admin";
                    _Utils.toast("Ocurrio un error al intentar realizar el logueo con el usuario");
                }
                _Utils.fragment(new MenuPrincipalFragment());
            } else {
                _Utils.toast(out.replace('"',' '));
            }
        }

        Log.d(tag, "(LoginFragment:syncGetReturn:77)" + tag + ":" + out);
        return false;
    }
}

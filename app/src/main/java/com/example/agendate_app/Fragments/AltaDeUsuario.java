package com.example.agendate_app.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.agendate_app.Database.Usuario;
import com.example.agendate_app.Database._SyncableGetResponse;
import com.example.agendate_app.Interfaces._SyncableGet;
import com.example.agendate_app.R;
import com.example.agendate_app.Utils._Utils;

import org.json.JSONObject;

public class AltaDeUsuario extends Fragment implements _SyncableGet {

        private EditText userName;
        private EditText inputName;
        private EditText inputLastName;
        private EditText inputEmail;
        private EditText inputPassword1;
        private EditText inputPassword2;
       // private TextView registerErrorMsg;
        private Button btnRegister;
        private _SyncableGet syncableGet = this;

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                 Bundle savedInstanceState) {
            myView = inflater.inflate(R.layout.fragment_alta_usuario, container, false);


            userName = (EditText) myView.findViewById(R.id.fcpl_username);
            inputEmail = (EditText) myView.findViewById(R.id.fcpl_email);
            inputName = (EditText) myView.findViewById(R.id.fcpl_first_name);
            inputLastName = (EditText) myView.findViewById(R.id.fcpl_last_name);

            inputPassword1 = (EditText) myView.findViewById(R.id.frg_Alta_Usuario_txt_pass1);
            inputPassword2 = (EditText) myView.findViewById(R.id.frg_Alta_Usuario_txt_pass2);
            btnRegister = (Button) myView.findViewById(R.id.fcpl_btnConfirmar);

            //registerErrorMsg = (TextView) myView.findViewById(R.id.register_error);

            btnRegister.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    String username = userName.getText().toString();
                    String name = inputName.getText().toString();
                    String lastname = inputName.getText().toString();
                    String email = inputEmail.getText().toString();
                    String password1 = inputPassword1.getText().toString();
                    String password2 = inputPassword2.getText().toString();
                    Usuario usuario = new Usuario();

                    usuario.setOnRegisterUsuario(new OnRegisterUsuario() {
                        @Override
                        public void onRegisterFinish(JSONObject json, String msg) {
                            registerErrorMsg.setText("");
                            Intent itemintent = new Intent(LoginFragment.this, ActivityPrincipal.class);
                            AltaDeUsuario.this.startActivity(itemintent);
                        }
                        @Override
                        public void onRegisterFail(String msg) {registerErrorMsg.setText(msg);}
                        @Override
                        public void onRegisterException(Exception e, String msg) {registerErrorMsg.setText(msg);}
                    });
                    usuario.register(AltaDeUsuario.this,username ,name, lastname, email, password1, password2);
                }
            });

            //lblGotoLogin = (TextView) findViewById(R.id.link_to_login);
            lblGotoLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                    public void onClick(View v) {Intent itemintent = new Intent(AltaDeUsuario.this, LoginFragment.class);
                    AltaDeUsuario.this.startActivity(itemintent);}
            });

        }

    @Override
    public boolean syncGetReturn(String tag, String out, _SyncableGetResponse sgr) {
        if(tag.equals("altadeusuario")) {
            if (out.contains("OK")) {
                _Utils.toast("Usuario registrado correctamente.");
                _Utils.fragment(new MenuPrincipalFragment());
            } else {
                _Utils.toast(out.replace('"',' '));
            }}
        return false;
    }
}

package com.example.agendate_app.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.agendate_app.Database.Usuario;
import com.example.agendate_app.Database.UsuarioDS;
import com.example.agendate_app.Database._SyncableGetResponse;
import com.example.agendate_app.Interfaces._SyncableGet;
import com.example.agendate_app.R;
import com.example.agendate_app.Utils._Utils;

public class AltaDeUsuario extends Fragment implements _SyncableGet {

        View myView;
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

            userName = (EditText) myView.findViewById(R.id.fau_username);
            inputEmail = (EditText) myView.findViewById(R.id.fau_email);
            inputName = (EditText) myView.findViewById(R.id.fau_first_name);
            inputLastName = (EditText) myView.findViewById(R.id.fau_last_name);

            inputPassword1 = (EditText) myView.findViewById(R.id.fau_txt_pass1);
            inputPassword2 = (EditText) myView.findViewById(R.id.fau_txt_pass2);
            btnRegister = (Button) myView.findViewById(R.id.fau_btnConfirmar);

            btnRegister.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String username = userName.getText().toString();
                    String name = inputName.getText().toString();
                    String lastname = inputLastName.getText().toString();
                    String email = inputEmail.getText().toString();
                    String password1 = inputPassword1.getText().toString();
                    String password2 = inputPassword2.getText().toString();

                    if(!password1.equals(password2))
                        _Utils.toast("La constraseñas ingresadas deben ser iguales");
                    else
                        new UsuarioDS().syncRegistrarUsuario(syncableGet,username , email, password1 ,name, lastname);
                }
            });

            return myView;
        }

    @Override
    public boolean syncGetReturn(String tag, String out, _SyncableGetResponse sgr) {
        if(tag.equals("registrarUsuario")) {
            if (out.contains("OK")) {
                _Utils.toast("Usuario registrado correctamente.");
                _Utils.fragment(new LoginFragment());
            } else {
                _Utils.toast(out.replace('"',' '));
            }
        }
        return false;
    }
}

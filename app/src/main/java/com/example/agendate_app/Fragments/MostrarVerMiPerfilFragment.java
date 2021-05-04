package com.example.agendate_app.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.agendate_app.Database.Usuario;
import com.example.agendate_app.Database.UsuarioDS;
import com.example.agendate_app.Database._SyncableGetResponse;
import com.example.agendate_app.Interfaces._SyncableGet;
import com.example.agendate_app.R;
import com.example.agendate_app.Utils._Utils;


public class MostrarVerMiPerfilFragment extends Fragment implements _SyncableGet {

    private _SyncableGet syncableGet = this;
    View myView;
    TextView fcpl_username, fcpl_first_name, fcpl_last_name, fcpl_email;
    Bundle bundle;
    Usuario mUsuario;

    Button btnConfirmar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_ver_mi_perfil_layout, container, false);

        btnConfirmar = myView.findViewById(R.id.fau_btnConfirmar);
        fcpl_username = (TextView) myView.findViewById(R.id.fau_username);
        fcpl_first_name= (TextView) myView.findViewById(R.id.fau_first_name);
        fcpl_last_name=(TextView)  myView.findViewById(R.id.fau_last_name);
        fcpl_email= (TextView) myView.findViewById(R.id.fau_email);


        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                guardarPerfil();
            }
        });

        cargarPerfil();
        _Utils.setAccionAtras(myView, new MenuPrincipalFragment(), null);
        return myView;
    }

    public void cargarPerfil(){
        mUsuario = new UsuarioDS().getAllUsuarios().get(0);
        fcpl_username.setText(mUsuario.getUsername());
        fcpl_first_name.setText(mUsuario.getFirst_name());
        fcpl_last_name.setText(mUsuario.getLast_name());
        fcpl_email.setText(mUsuario.getEmail());
    }

    public void guardarPerfil(){
        mUsuario.setFirst_name(fcpl_first_name.getText().toString());
        mUsuario.setLast_name(fcpl_last_name.getText().toString());
        mUsuario.setEmail(fcpl_email.getText().toString());
        new UsuarioDS().CreateUsuario(mUsuario);
        // Sync de usuario
        new UsuarioDS().syncGetModificarDatos(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Mi Perfil");
    }

    @Override
    public boolean syncGetReturn(String tag, String out, _SyncableGetResponse sgr) {
        if(tag.equals("modificarPerfil")) {
            if (out.contains("OK")) {
                _Utils.toast("Usuario actualizado correctamente.");
                _Utils.fragment(new MenuPrincipalFragment());
            } else {
                _Utils.toast(out.replace('"',' '));
            }
        }

        /*if(tag.equals("Rubros")) {
            if (new RubroDS().getAllRubros().size() > 0) {
                _Utils.fragment(new MostrarRubrosFragment());
            }
        }else if(tag.equals("Agendas")) {
            _Utils.fragment(new MostrarVerAgendaFragment());
        }
        Log.d(tag, "(MainFragment:syncGetReturn:55)" + tag + ":" + out);
         */
        return false;
    }
}

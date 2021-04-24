package com.example.agendate_app.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.agendate_app.Database.Rubro;
import com.example.agendate_app.Database.RubroDS;
import com.example.agendate_app.Database._SyncableGetResponse;
import com.example.agendate_app.Interfaces._SyncableGet;
import com.example.agendate_app.R;
import com.example.agendate_app.Utils._Utils;

import static java.lang.Thread.sleep;


public class MenuPrincipalFragment extends Fragment implements _SyncableGet {

    private _SyncableGet syncableGet = this;
    View myView;

    LinearLayout btnAgendar, btnVerAgenda;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_main, container, false);

        btnAgendar = myView.findViewById(R.id.msf_ver);

        btnAgendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new RubroDS().syncGet(syncableGet);
            }
        });

        btnVerAgenda = myView.findViewById(R.id.msf_editar);

        btnVerAgenda.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        return myView;
    }

    @Override
    public boolean syncGetReturn(String tag, String out, _SyncableGetResponse sgr) {
        if(new RubroDS().getAllRubros().size()>0){
            _Utils.fragment(new MostrarRubrosFragment());
        }

        Log.d(tag, "(MainFragment:syncGetReturn:55)" + tag + ":" + out);
        return false;
    }
}

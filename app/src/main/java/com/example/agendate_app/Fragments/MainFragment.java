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

public class MainFragment extends Fragment implements _SyncableGet {

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
                if(_Utils.getmRubros().size()==0) {
                    _Utils.toast("No hay rubros descargados");
                } else {
                    for (Rubro r: _Utils.getmRubros()) {
                        _Utils.toast(r.toString(),Toast.LENGTH_LONG);
                    }
                }
            }
        });

        return myView;
    }

    @Override
    public boolean syncGetReturn(String tag, String out, _SyncableGetResponse sgr) {

        if (tag.equalsIgnoreCase("Rubros")) {
                //int cantRegistros = Integer.valueOf(out);
                _Utils.toast(" Rubros descargados", Toast.LENGTH_LONG);

        }

        Log.d(tag, "(MainFragment:syncGetReturn:55)" + tag + ":" + out);
        return false;
    }
}

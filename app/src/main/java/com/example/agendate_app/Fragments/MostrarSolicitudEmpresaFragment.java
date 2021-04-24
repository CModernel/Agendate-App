package com.example.agendate_app.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendate_app.Adaptador.AdaptadorRubro;
import com.example.agendate_app.Adaptador.AdaptadorSolicitudEmpresa;
import com.example.agendate_app.Database.Rubro;
import com.example.agendate_app.Database.RubroDS;
import com.example.agendate_app.Database.SolicitudEmpresa;
import com.example.agendate_app.Database.SolicitudEmpresaDS;
import com.example.agendate_app.Interfaces._RVListener;
import com.example.agendate_app.MainActivity;
import com.example.agendate_app.R;
import com.example.agendate_app.Utils._Utils;

import java.util.List;

public class MostrarSolicitudEmpresaFragment extends Fragment implements _RVListener {
    View mView, mEmptyView;
    RecyclerView mLista;
    RecyclerView.Adapter<?> mAdapter;
    LinearLayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_linea, container, false);

        mEmptyView = mView.findViewById(R.id.fc_emptyview);
        mLista = mView.findViewById(R.id.fc_rv);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLista.setLayoutManager(mLayoutManager);

        setAdapterSolicitudEmpresa();
        _Utils.setBackAction(mView, new MainActivity.MenuPrincipalFragment());
        return mView;
    }

    private void setAdapterSolicitudEmpresa() {

        try {
            List<SolicitudEmpresa> SolicitudEmpresa = new SolicitudEmpresaDS().getAllSolicitudEmpresa();

            if (SolicitudEmpresa.size() <= 0) {
                mLista.setVisibility(View.GONE);
                mEmptyView.setVisibility(View.VISIBLE);

            } else {
                mLista.setVisibility(View.VISIBLE);
                mEmptyView.setVisibility(View.GONE);

                mAdapter = new AdaptadorSolicitudEmpresa(_Utils.getContext(), SolicitudEmpresa,
                        this, this);
                mLista.setHasFixedSize(true);

                mLista.setAdapter(mAdapter);
            }
        } catch (Exception e){
            _Utils.toast("Hubo un error", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("SolicitudEmpresa");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

    }

    @Override
    public void onRVItemClick(Fragment fragment, View view, Object object, int position) {
        try{
            if(object instanceof List<?>) {
                List<SolicitudEmpresa> mSolicitudEmpresas = (List<SolicitudEmpresa>) object;
                SolicitudEmpresa r = mSolicitudEmpresas.get(position);
                _Utils.toast("Se seleccionó SolicitudEmpresa: "+r.getFecha());

                //_Utils.fragment(, bundle);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onRVItemLongClick(Fragment fragment, View view, Object object, int position) {

    }

}
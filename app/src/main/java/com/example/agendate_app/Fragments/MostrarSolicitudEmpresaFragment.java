package com.example.agendate_app.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendate_app.Adaptador.AdaptadorSolicitudEmpresa;
import com.example.agendate_app.Database.SolicitudEmpresa;
import com.example.agendate_app.Database.SolicitudEmpresaDS;
import com.example.agendate_app.Interfaces._RVListener;
import com.example.agendate_app.MainActivity;
import com.example.agendate_app.R;
import com.example.agendate_app.Utils._Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MostrarSolicitudEmpresaFragment extends Fragment implements _RVListener {
    View mView, mEmptyView;
    RecyclerView mLista;
    RecyclerView.Adapter<?> mAdapter;
    LinearLayoutManager mLayoutManager;
    Bundle bundle;
    SolicitudEmpresa solicitudEmpresa;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_pedido_layout, container, false);

        mEmptyView = mView.findViewById(R.id.fpl_llEmpty);
        mLista = mView.findViewById(R.id.listapedidos);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLista.setLayoutManager(mLayoutManager);

        getBundle();
        build();
        setAdapterSolicitudEmpresa();
        _Utils.setBackAction(mView, new MainActivity.MenuPrincipalFragment());
        return mView;
    }

    private void build(){
        if(_Utils.getEmpresaSeleccionada()!=null) {
            // Razon Social Empresa
            TextView tvnombre = mView.findViewById(R.id.icc_txt_tvnombre);
            tvnombre.setText(_Utils.getEmpresaSeleccionada().getEmpRazonSocial());

            // Telefono
            TextView tvtel = mView.findViewById(R.id.icc_txt_tvtelefono);
            tvtel.setText("Telefono: " + _Utils.getEmpresaSeleccionada().getEmpTelefono());

            // Direccion
            TextView tvdir = mView.findViewById(R.id.icc_txt_tvdir);
            String direccion = _Utils.getEmpresaSeleccionada().getEmpDirCalle() + _Utils.getEmpresaSeleccionada().getEmpDirNum();
            if(!_Utils.getEmpresaSeleccionada().getEmpDirEsquina().isEmpty())
                direccion += " Esq. " + _Utils.getEmpresaSeleccionada().getEmpDirEsquina();

            tvdir.setText(direccion);
            tvdir.setSelected(true);

            ImageView ivfoto = mView.findViewById(R.id.icc_txt_letranom);
            String urlImage = _Utils._URL_AGENDATE+_Utils._PATH_STATIC + _Utils.getEmpresaSeleccionada().getEmpImagen();
            Picasso.get().load(urlImage).into(ivfoto);

            Button btnfecha = mView.findViewById(R.id.fpl_btnFecha);
            btnfecha.setText("Fecha: " + "2021-04-26");
        }
    }

    private void setAdapterSolicitudEmpresa() {

        try {

            List<SolicitudEmpresa> SolicitudEmpresa = new SolicitudEmpresaDS().getHorarioPorSolicitudEmpresa(solicitudEmpresa);

            if (SolicitudEmpresa.size() <= 0) {
                mLista.setVisibility(View.GONE);
                mEmptyView.setVisibility(View.VISIBLE);

            } else {
                mLista.setVisibility(View.VISIBLE);
                mEmptyView.setVisibility(View.GONE);

                mAdapter = new AdaptadorSolicitudEmpresa(_Utils.getContext(), SolicitudEmpresa,this, this);
                mLista.setHasFixedSize(true);

                mLista.setAdapter(mAdapter);
            }
        } catch (Exception e){
            _Utils.toast("Hubo un error", Toast.LENGTH_SHORT);
        }
    }


    public void getBundle(){
        bundle = getArguments();
        try {
            if (bundle != null) {
                // Obtenemos int de Rubro seleccionado
                if (bundle.getSerializable("SolicitudesEmpresa")!= null) {
                    solicitudEmpresa = (SolicitudEmpresa) bundle.getSerializable("SolicitudesEmpresa");
                }
            } else {
                solicitudEmpresa = _Utils.getSolicitudesEmpresa();
            }
        }catch(Exception ex){
            _Utils.toast("Ocurrio un error.");
            ex.printStackTrace();
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

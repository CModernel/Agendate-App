package com.example.agendate_app.Fragments;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendate_app.Adaptador.AdaptadorSolicitudEmpresa;
import com.example.agendate_app.Database.SolicitudEmpresa;
import com.example.agendate_app.Database.SolicitudEmpresaDS;
import com.example.agendate_app.Database._SyncableGetResponse;
import com.example.agendate_app.Interfaces._RVListener;
import com.example.agendate_app.Interfaces._SyncableGet;
import com.example.agendate_app.MainActivity;
import com.example.agendate_app.R;
import com.example.agendate_app.Utils._Utils;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

public class MostrarSolicitudEmpresaFragment extends Fragment implements _RVListener, _SyncableGet {
    View mView, mEmptyView;
    Button btnFecha;
    DatePickerDialog datePickerDialog;
    RecyclerView mLista;
    RecyclerView.Adapter<?> mAdapter;
    LinearLayoutManager mLayoutManager;
    Bundle bundle;
    SolicitudEmpresa solicitudEmpresa;
    _SyncableGet syncableGet = this;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_pedido_layout, container, false);

        mEmptyView = mView.findViewById(R.id.fpl_llEmpty);
        mLista = mView.findViewById(R.id.listapedidos);
        btnFecha = mView.findViewById(R.id.fpl_btnFecha);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLista.setLayoutManager(mLayoutManager);

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirDatePicker();
            }
        });


        getBundle();
        initDatePicker();
        build();
        setAdapterSolicitudEmpresa();
        _Utils.setAccionAtras(mView, new MostrarEmpresasFragment(), bundle);
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

            btnFecha.setText("Fecha: " + _Utils.getFechaSeleccionada());

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

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int año, int mes, int dia) {
                mes = mes +1;
                String fecha = _Utils.crearFechaString(dia, mes, año);
                _Utils.setFechaSeleccionada(fecha);
                btnFecha.setText("Fecha: " + fecha);
                new SolicitudEmpresaDS().syncGet(syncableGet);
            }
        };

        Calendar cal = Calendar.getInstance();
        int año = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(_Utils.getContext(), 0, dateSetListener, año, mes ,dia);
    }


    private void abrirDatePicker(){
        datePickerDialog.show();

    }

    public void getBundle(){
        bundle = getArguments();
        try {
            if (bundle != null) {
                // Obtenemos int de Rubro seleccionado
                if (bundle.getSerializable("SolicitudesEmpresa")!= null) {
                    solicitudEmpresa = (SolicitudEmpresa) bundle.getSerializable("SolicitudesEmpresa");
                } else {
                    solicitudEmpresa = _Utils.getSolicitudesEmpresa();
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
        actionBar.setTitle("Seleccione horario de Agenda");
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
                if(r.getSolicitudes() != null || r.getHorariosVencidos() != null){
                    _Utils.toast("Ya existe solicitud para la hora seleccionada.");
                }else
                {
                    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(_Utils.getActivity());
                    dialogo1.setTitle("Alta Solicitud");
                    dialogo1.setMessage("Desea dar de alta la solicitud seleccionada?");
                    dialogo1.setCancelable(true);
                    dialogo1.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            // TODO
                            // Ejecutar WS para dar de baja la solicitud
                            // "/crearSolicitudV1/<str:empresaSel>/<str:fechaSel>/<str:horaSel>/<str:usuId>"
                            new SolicitudEmpresaDS().altaSolicitud(MostrarSolicitudEmpresaFragment.this::syncGetReturn, _Utils.getEmpresaSeleccionada().getEmpId(), _Utils.FechaSeleccionada, r.getHorario()[0], _Utils.UsuId);

                        }
                    });
                    dialogo1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {

                        }
                    });
                    dialogo1.show();
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onRVItemLongClick(Fragment fragment, View view, Object object, int position) {

    }

    @Override
    public boolean syncGetReturn(String tag, String out, _SyncableGetResponse sgr) {
        // Refrescar Fragment
        if(tag.equals("crearSolicitud"))
            new SolicitudEmpresaDS().syncGet(this);
        else if(tag.equals("SolicitudEmpresa"))
            _Utils.fragment(new MostrarSolicitudEmpresaFragment());

        return false;
    }
}

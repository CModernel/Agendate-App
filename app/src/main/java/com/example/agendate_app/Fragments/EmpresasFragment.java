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

import com.example.agendate_app.Adaptador.AdaptadorEmpresasLineas;
import com.example.agendate_app.Database.Empresas;
import com.example.agendate_app.Database.EmpresasDS;
import com.example.agendate_app.Interfaces._RVListener;
import com.example.agendate_app.R;
import com.example.agendate_app.Utils._Utils;

import java.util.List;

public class EmpresasFragment extends Fragment implements _RVListener {
    View mView, mEmptyView;
    RecyclerView mLista;
    RecyclerView.Adapter<?> mAdapter;
    LinearLayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_empresas, container, false);

        mEmptyView = mView.findViewById(R.id.fc_emptyview);
        mLista = mView.findViewById(R.id.fc_rv);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLista.setLayoutManager(mLayoutManager);

        setAdapterRubros();
        _Utils.setBackAction(mView, new MainFragment());
        return mView;
    }

    private void setAdapterRubros() {

        try {
            List<Empresas> Empresa = new EmpresasDS().getAllEmpresas();

            if (Empresa.size() <= 0) {
                mLista.setVisibility(View.GONE);
                mEmptyView.setVisibility(View.VISIBLE);

            } else {
                mLista.setVisibility(View.VISIBLE);
                mEmptyView.setVisibility(View.GONE);

                mAdapter = new AdaptadorEmpresasLineas(_Utils.getContext(), Empresa, this, this);
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
        actionBar.setTitle("Empresas");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

    }

    @Override
    public void onRVItemClick(Fragment fragment, View view, Object object, int position) {
        try{
            //_Utils.fragment(, bundle);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onRVItemLongClick(Fragment fragment, View view, Object object, int position) {

    }
}

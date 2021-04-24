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
    View mViewE, mEmptyViewE;
    RecyclerView mListaE;
    RecyclerView.Adapter<?> mAdapter;
    LinearLayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewE = inflater.inflate(R.layout.fragment_empresas, container, false);

        mEmptyViewE = mViewE.findViewById(R.id.fc_emptyview1);
        mListaE = mViewE.findViewById(R.id.fc_rv1);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mListaE.setLayoutManager(mLayoutManager);

        setAdapterEmpresas();
        _Utils.setBackAction(mViewE, new MenuPrincipalFragment());
        return mViewE;
    }

    private void setAdapterEmpresas() {

        try {
            List<Empresas> Empresa = new EmpresasDS().getAllEmpresas();

            if (Empresa.size() <= 0) {
                mListaE.setVisibility(View.GONE);
                mEmptyViewE.setVisibility(View.VISIBLE);

            } else {
                mListaE.setVisibility(View.VISIBLE);
                mEmptyViewE.setVisibility(View.GONE);

                mAdapter = new AdaptadorEmpresasLineas(_Utils.getContext(), Empresa, this, this);
                mListaE.setHasFixedSize(true);

                mListaE.setAdapter(mAdapter);
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

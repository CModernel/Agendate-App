package com.example.agendate_app.Fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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
import com.example.agendate_app.Database.EmpresasDS;
import com.example.agendate_app.Database.Rubro;
import com.example.agendate_app.Database.RubroDS;
import com.example.agendate_app.Database._SyncableGetResponse;
import com.example.agendate_app.Interfaces._RVListener;
import com.example.agendate_app.Interfaces._SyncableGet;
import com.example.agendate_app.MainActivity;
import com.example.agendate_app.R;
import com.example.agendate_app.Utils._Utils;

import java.util.List;

public class MostrarRubrosFragment extends Fragment implements _RVListener, _SyncableGet {
    View mView, mEmptyView;
    RecyclerView mLista;
    RecyclerView.Adapter<?> mAdapter;
    LinearLayoutManager mLayoutManager;
    Rubro rubroSeleccionado;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_linea, container, false);

        mEmptyView = mView.findViewById(R.id.fc_emptyview);
        mLista = mView.findViewById(R.id.fc_rv);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLista.setLayoutManager(mLayoutManager);

        _Utils.setAccionAtras(mView, new MenuPrincipalFragment(), null);
        setAdapterRubros();

        return mView;
    }

    private void setAdapterRubros() {

        try {
            List<Rubro> rubros = new RubroDS().getAllRubros();

            if (rubros.size() <= 0) {
                mLista.setVisibility(View.GONE);
                mEmptyView.setVisibility(View.VISIBLE);

            } else {
                mLista.setVisibility(View.VISIBLE);
                mEmptyView.setVisibility(View.GONE);

                mAdapter = new AdaptadorRubro(_Utils.getContext(), rubros, this, this);
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
        actionBar.setTitle("Rubros");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

    }

    @Override
    public void onRVItemClick(Fragment fragment, View view, Object object, int position) {
        try{
            if(object instanceof List<?>) {
                List<Rubro> mRubros = (List<Rubro>) object;
                rubroSeleccionado = mRubros.get(position);
                _Utils.setRubroSeleccionado(rubroSeleccionado);
                new EmpresasDS().syncGet(this);
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

        if(rubroSeleccionado!=null) {
            if(new EmpresasDS().getAllEmpresasPorRubro(rubroSeleccionado.getId()).size()>0) {
                Bundle bundle = new Bundle();
                bundle.putInt("RubroId", rubroSeleccionado.getId());

                _Utils.fragment(new MostrarEmpresasFragment(), bundle);
            } else {
                _Utils.toast("No existen empresas para ese rubro.");
            }
        }else {
            _Utils.toast("Ocurrió un error.");
        }

        Log.d(tag, "(MostrarRubrosFragment:syncGetReturn:127)" + tag + ":" + out);
        return false;
    }
}
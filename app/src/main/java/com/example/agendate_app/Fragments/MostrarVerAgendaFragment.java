package com.example.agendate_app.Fragments;

import android.os.Bundle;
import android.util.Log;
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

import com.example.agendate_app.Adaptador.AdaptadorVerAgenda;
import com.example.agendate_app.Database.EmpresasDS;
import com.example.agendate_app.Database.VerAgenda;
import com.example.agendate_app.Database.VerAgendaDS;
import com.example.agendate_app.Database._SyncableGetResponse;
import com.example.agendate_app.Interfaces._RVListener;
import com.example.agendate_app.Interfaces._SyncableGet;
import com.example.agendate_app.MainActivity;
import com.example.agendate_app.R;
import com.example.agendate_app.Utils._Utils;

import java.util.List;

public class MostrarVerAgendaFragment extends Fragment implements _RVListener, _SyncableGet {

    View mView, mEmptyView;
    RecyclerView mLista;
    RecyclerView.Adapter<?> mAdapter;
    LinearLayoutManager mLayoutManager;
    VerAgenda solicitudVerAgenda;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_ver_agenda, container, false);

        mEmptyView = mView.findViewById(R.id.fc_emptyview);
        mLista = mView.findViewById(R.id.fc_rv);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLista.setLayoutManager(mLayoutManager);

        setAdapterVerAgenda();
        _Utils.setBackAction(mView, new MainActivity.MenuPrincipalFragment());
        return mView;
    }

    private void setAdapterVerAgenda() {

        try {
            List<VerAgenda> VerAgenda = new VerAgendaDS().getAllVerAgenda(solicitudVerAgenda.getId());

            if (VerAgenda.size() <= 0) {
                mLista.setVisibility(View.GONE);
                mEmptyView.setVisibility(View.VISIBLE);

            } else {
                mLista.setVisibility(View.VISIBLE);
                mEmptyView.setVisibility(View.GONE);

                mAdapter = new AdaptadorVerAgenda(_Utils.getContext(), VerAgenda, this, this);
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
        actionBar.setTitle("VerAgenda");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

    }

    @Override
    public void onRVItemClick(Fragment fragment, View view, Object object, int position) {
        try{
            if(object instanceof List<?>) {
                List<VerAgenda> mVerAgenda = (List<VerAgenda>) object;
                solicitudVerAgenda = mVerAgenda.get(position);
                _Utils.setSolicitudVerAgenda(solicitudVerAgenda);
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

        if(solicitudVerAgenda!=null) {
            if(new VerAgendaDS().getAllVerAgenda(solicitudVerAgenda.getId()).size()>0) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", solicitudVerAgenda.getId());

                _Utils.fragment(new MostrarVerAgendaFragment(), bundle);
            } else {
                _Utils.toast("No agenda disponible.");
            }
        }else {
            _Utils.toast("Ocurrió un error.");
        }

        Log.d(tag, "(MostrarVerAgendaFragment:syncGetReturn:127)" + tag + ":" + out);
        return false;
    }


}

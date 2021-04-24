package com.example.agendate_app.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.agendate_app.Database.SolicitudEmpresa;
import com.example.agendate_app.Database.SolicitudEmpresaDS;

import com.example.agendate_app.Fragments.MostrarSolicitudEmpresaFragment;
import com.example.agendate_app.Interfaces._RVListener;
import com.example.agendate_app.R;
import com.example.agendate_app.Utils._Utils;

import java.util.List;

public class AdaptadorSolicitudEmpresa extends RecyclerView.Adapter<AdaptadorSolicitudEmpresa.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<SolicitudEmpresa> SolicitudEmpresas;
    private _RVListener clickListener;
    MostrarSolicitudEmpresaFragment callback;

    public AdaptadorSolicitudEmpresa(Context context, List<SolicitudEmpresa> SolicitudEmpresas,
                                     _RVListener clickListener, MostrarSolicitudEmpresaFragment callback) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.SolicitudEmpresas = SolicitudEmpresas;
        this.clickListener = clickListener;
        this.callback = callback;
    }

    @NonNull
    @Override
    public AdaptadorSolicitudEmpresa.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_linea, parent, false);
        return new AdaptadorSolicitudEmpresa.ViewHolder(view);
    }
    

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            SolicitudEmpresa linea = SolicitudEmpresas.get(position);

            holder.SolicitudEmpresa = new SolicitudEmpresaDS().getSolicitudEmpresa(linea.getEmpId());

            holder.dsc.setText(holder.SolicitudEmpresa.getEmpId().toString() +
                    " - "+ holder.SolicitudEmpresa.getFecha().toString() +
                    " - "+ holder.SolicitudEmpresa.getHorarioSolicitud().toString());

        }catch (Exception ex){
            _Utils.toast(ex.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return SolicitudEmpresa.size();
    }

    public SolicitudEmpresa getItem(int position) {
        return SolicitudEmpresa.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        LinearLayout container;
        TextView dsc;

        SolicitudEmpresa SolicitudEmpresa;

        public ViewHolder(View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.ic_container);
            container.setOnClickListener(this);
            container.setOnLongClickListener(this);

            dsc = itemView.findViewById(R.id.ic_dsc);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null)
                clickListener.onRVItemClick(null, v,  SolicitudEmpresa, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {

            return true;
        }
    }

}
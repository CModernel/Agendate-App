package com.example.agendate_app.Adaptador;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.example.agendate_app.Database.Agenda;
import com.example.agendate_app.Database.AgendaDS;
import com.example.agendate_app.Fragments.MostrarVerAgendaFragment;
import com.example.agendate_app.Interfaces._RVListener;
import com.example.agendate_app.R;
import com.example.agendate_app.Utils._Utils;

import java.util.List;

public class AdaptadorVerAgenda extends RecyclerView.Adapter<AdaptadorVerAgenda.ViewHolder>{

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Agenda> mAgendas;
    private _RVListener clickListener;
    MostrarVerAgendaFragment callback;

    public AdaptadorVerAgenda(Context context, List<Agenda> listaAgendas, _RVListener clickListener,
                              MostrarVerAgendaFragment callback) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.mAgendas = listaAgendas;
        this.clickListener = clickListener;
        this.callback = callback;
    }


    @NonNull
    @Override
    public AdaptadorVerAgenda.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_planilla, parent, false);
        return new AdaptadorVerAgenda.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AdaptadorVerAgenda.ViewHolder holder, int position) {
        try {
            Agenda linea = mAgendas.get(position);

            holder.mAgenda = linea;

            // TODO
            // Ir a la tabla empresa y traer el nombre
            holder.fechaSolicitud.setText(linea.getFechaSolicitud());
            holder.hora.setText(linea.getHoraSolicitud());

            if(linea.getEmpRazonSocial()!=null)
                holder.empId.setText(linea.getEmpRazonSocial());

            if(linea.getEmpRubro1()!=null)
                holder.rubro.setText(linea.getEmpRubro1());

            if(linea.getEmpTelefono()!=null)
                holder.telefono.setText(linea.getEmpTelefono());

        }catch (Exception ex){
            _Utils.toast(ex.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return mAgendas.size();
    }

    public Agenda getItem(int position) {
        return mAgendas.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener {

        LinearLayout container;

        Agenda mAgenda;
        TextView empId, fechaSolicitud, hora, rubro, telefono;
        ImageButton remove;

        public ViewHolder(View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.ip_container);
            //container.setOnClickListener(this);
            //container.setOnLongClickListener(this);

            empId = (TextView) itemView.findViewById(R.id.pla_nro_tview);
            fechaSolicitud = (TextView) itemView.findViewById(R.id.pla_fecha_tview);
            hora = (TextView) itemView.findViewById(R.id.estado_tview);
            rubro = (TextView) itemView.findViewById(R.id.nro_viaje_tview);
            telefono = (TextView) itemView.findViewById(R.id.pla_nro_tel);
            remove = (ImageButton) itemView.findViewById(R.id.imageView3);
            remove.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null)
                clickListener.onRVItemClick(null, v, mAgendas, getAdapterPosition());

        }

        @Override
        public boolean onLongClick(View view) {

            return true;
        }
    }

}

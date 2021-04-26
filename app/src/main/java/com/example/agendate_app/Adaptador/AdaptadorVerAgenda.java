package com.example.agendate_app.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.agendate_app.Database.VerAgenda;
import com.example.agendate_app.Database.VerAgendaDS;
import com.example.agendate_app.Fragments.MostrarVerAgendaFragment;
import com.example.agendate_app.Interfaces._RVListener;
import com.example.agendate_app.R;
import com.example.agendate_app.Utils._Utils;

import java.util.List;

public class AdaptadorVerAgenda extends RecyclerView.Adapter<AdaptadorVerAgenda.ViewHolder {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<VerAgenda> VerAgenda;
    private _RVListener clickListener;
    MostrarVerAgendaFragment callback;

    public AdaptadorVerAgenda(Context context, List<VerAgenda> VerAgenda, _RVListener clickListener,
                              MostrarVerAgendaFragment callback) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.VerAgenda = VerAgenda;
        this.clickListener = clickListener;
        this.callback = callback;
    }


    @NonNull
    @Override
    public AdaptadorVerAgenda.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.fragment_ver_agenda, parent, false);
        return new AdaptadorVerAgenda.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AdaptadorVerAgenda.ViewHolder holder, int position) {
        try {
            VerAgenda linea = VerAgenda.get(position);

            holder.VerAgenda = new VerAgendaDS().getVerAgenda(linea.getEmpId());


            holder.id.setText(linea.getId().toString());


        }catch (Exception ex){
            _Utils.toast(ex.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return VerAgenda.size();
    }

    public VerAgenda getItem(int position) {
        return VerAgenda.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener {

        LinearLayout container;

        VerAgenda VerAgenda;
        TextView id, FechaSolicitud, HoraSolicitud, SeConcreto, ComentarioAdmin, ComentarioUsuario,
                SolicitudActivo, UsuId,EmpId, UsuAdminResponsable;


        public ViewHolder(View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.ic_empcontainer);
            container.setOnClickListener(this);
            container.setOnLongClickListener(this);



            id = (TextView) itemView.findViewById(R.id.ic_txt_empid);
            FechaSolicitud = (TextView) itemView.findViewById(R.id.ic_txt_empnombre);
            ComentarioUsuario = (TextView) itemView.findViewById(R.id.ic_txt_empdireccion);

            ComentarioUsuario.setSelected(true);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null)
                clickListener.onRVItemClick(null, v,  VerAgenda, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {

            return true;
        }
    }

}

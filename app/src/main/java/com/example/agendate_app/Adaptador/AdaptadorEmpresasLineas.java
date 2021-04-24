package com.example.agendate_app.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendate_app.Database.Empresas;
import com.example.agendate_app.Database.EmpresasDS;
import com.example.agendate_app.Fragments.EmpresasFragment;
import com.example.agendate_app.Interfaces._RVListener;
import com.example.agendate_app.R;
import com.example.agendate_app.Utils._Utils;

import java.util.List;

public class AdaptadorEmpresasLineas extends RecyclerView.Adapter<AdaptadorEmpresasLineas.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Empresas> Empresa;
    private _RVListener clickListener;
    EmpresasFragment callback;

    public AdaptadorEmpresasLineas(Context context, List<Empresas> Empresa, _RVListener clickListener, EmpresasFragment callback) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.Empresa = Empresa;
        this.clickListener = clickListener;
        this.callback = callback;
    }


    @NonNull
    @Override
    public AdaptadorEmpresasLineas.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_linea, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorEmpresasLineas.ViewHolder holder, int position) {
        try {
            Empresas linea = Empresa.get(position);

            holder.Empresas = new EmpresasDS().getEmpresas(linea.getEmpId());

            holder.dsc.setText(holder.Empresas.getEmpId().toString() + " - "+ holder.Empresas.getEmpRazonSocial());

        }catch (Exception ex){
            _Utils.toast(ex.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return Empresa.size();
    }

    public Empresas getItem(int position) {
        return Empresa.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        LinearLayout container;
        TextView dsc;

        Empresas Empresas;

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
                clickListener.onRVItemClick(null, v,  Empresa, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            /*
            if (clickListener != null) {


                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int op) {

                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(_Utils.getContext());
                builder.setMessage(R.string.clf_txt_eliminar_linea).setPositiveButton("Si", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }

*/
            return true;
        }
    }
}

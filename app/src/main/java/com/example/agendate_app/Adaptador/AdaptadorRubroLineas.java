package com.example.agendate_app.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendate_app.Database.Rubro;
import com.example.agendate_app.Database.RubroDS;
import com.example.agendate_app.Fragments.LineasFragment;
import com.example.agendate_app.Interfaces._RVListener;
import com.example.agendate_app.R;
import com.example.agendate_app.Utils._Utils;

import java.util.List;

public class AdaptadorRubroLineas extends RecyclerView.Adapter<AdaptadorRubroLineas.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Rubro> rubros;
    private _RVListener clickListener;
    LineasFragment callback;

    public AdaptadorRubroLineas(Context context, List<Rubro> rubros, _RVListener clickListener, LineasFragment callback) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.rubros = rubros;
        this.clickListener = clickListener;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.item_linea, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            Rubro linea = rubros.get(position);

            holder.rubro = new RubroDS().getRubros(linea.getId());

            holder.dsc.setText(holder.rubro.getId().toString() + " - "+ holder.rubro.getRubroNom());

        }catch (Exception ex){
            _Utils.toast(ex.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return rubros.size();
    }

    public Rubro getItem(int position) {
        return rubros.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        LinearLayout container;
        TextView dsc;

        Rubro rubro;

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
                clickListener.onRVItemClick(null, v,  rubros, getAdapterPosition());
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

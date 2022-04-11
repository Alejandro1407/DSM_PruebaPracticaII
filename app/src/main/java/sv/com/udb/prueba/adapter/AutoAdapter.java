package sv.com.udb.prueba.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import sv.com.udb.prueba.R;
import sv.com.udb.prueba.interfaces.OnItemClickListener;
import sv.com.udb.prueba.model.Automovil;
import sv.com.udb.prueba.model.Marca;

public class AutoAdapter extends AbstractAdapter<Automovil, AutoAdapter.AutoViewHolder> {

    public AutoAdapter(List<Automovil> payload, OnItemClickListener<Automovil> onItemClickListener) {
        super(payload, onItemClickListener);
    }

    public AutoAdapter(List<Automovil> payload) {
        super(payload);
    }

    @NonNull
    @Override
    public AutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_auto,parent,false);
        return new AutoViewHolder(view);
    }


    public class AutoViewHolder extends AbstractViewHolder<Automovil> {

        private TextView txtModelo;
        private TextView txtAnio;
        private TextView txtAsientos;
        private TextView txtPrecio;

        public AutoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtModelo = itemView.findViewById(R.id.txtModelo);
            txtAnio = itemView.findViewById(R.id.txtAnio);
            txtAsientos = itemView.findViewById(R.id.txtAsientos);
            txtPrecio = itemView.findViewById(R.id.txtPrecio);
        }

        @Override
        public void bind(Automovil payload, int postion, OnItemClickListener<Automovil> onItemClickListener) {
            txtModelo.setText(payload.getModelo());
            txtAnio.setText("Año: " + payload.getAnio());
            txtAsientos.setText("# Asientos: " + payload.getNumeroAsientos());
            txtPrecio.setText("$ " + payload.getPrecio());
            if(null != onItemClickListener){
                itemView.setOnClickListener((View v) -> onItemClickListener.onClick(payload));
            }

        }
    }

}

package com.example.soa_r.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soa_r.R;
import com.example.soa_r.model.ropas;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ropaAdapterUser  extends FirestoreRecyclerAdapter<ropas, ropaAdapterUser.ViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ropaAdapterUser(@NonNull FirestoreRecyclerOptions<ropas> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull ropas ropas) {
        viewHolder.type.setText(ropas.getTipo());
        viewHolder.gener.setText(ropas.getGenero());
        viewHolder.waist.setText(ropas.getTalla());
        viewHolder.condition.setText(ropas.getEstado());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_usuario_single, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView type, gener, waist, condition;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            type = itemView.findViewById(R.id.Tipo);
            gener = itemView.findViewById(R.id.Genero);
            waist = itemView.findViewById(R.id.Talla);
            condition = itemView.findViewById(R.id.Estado);
        }
    }
}

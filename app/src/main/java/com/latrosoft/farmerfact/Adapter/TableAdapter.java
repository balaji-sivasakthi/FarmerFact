package com.latrosoft.farmerfact.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.latrosoft.farmerfact.R;
import com.latrosoft.farmerfact.model.TableModel;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {
    List<TableModel> models ;
    Context mContext;
    public TableAdapter(List<TableModel> models,Context mContext) {
        this.models = models;
        this.mContext =mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TableModel model = models.get(position);
        holder.ferName.setText(model.getFertilizer());
        holder.ferQty.setText(model.getQty());
        holder.ferTime.setText(model.getTime());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ferName,ferQty,ferTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ferName =itemView.findViewById(R.id.fertName);
            ferQty =itemView.findViewById(R.id.fertQty);
            ferTime=itemView.findViewById(R.id.fertTime);

        }
    }
}

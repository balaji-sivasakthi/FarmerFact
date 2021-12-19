package com.latrosoft.farmerfact.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.latrosoft.farmerfact.Detail;
import com.latrosoft.farmerfact.R;
import com.latrosoft.farmerfact.model.CropListModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CropAdapter extends RecyclerView.Adapter<CropAdapter.ViewHolder> {
    private ArrayList<CropListModel> mCropList;
    private Context mContext;

    public CropAdapter(ArrayList<CropListModel> mCropList,Context mContext) {
        this.mCropList = mCropList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            CropListModel cropListModel = mCropList.get(position);
            holder.name.setText(cropListModel.getName());
            holder.acre.setText(cropListModel.getAcre());
    }

    @Override
    public int getItemCount() {
        return mCropList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView acre;
        private ImageView imageView;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            acre=itemView.findViewById(R.id.acre);
            cardView = itemView.findViewById(R.id.crop_item_card);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CropListModel m = mCropList.get(getAdapterPosition());
                    Intent i = new Intent(mContext.getApplicationContext(), Detail.class);
                    i.putExtra("cropId",m.getId());
                    i.putExtra("cropAcre",m.getAcre());
                    i.putExtra("cropLink",m.getLink());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(i);
                }
            });
        }
    }
}

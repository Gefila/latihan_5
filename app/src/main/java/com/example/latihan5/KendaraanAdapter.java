package com.example.latihan5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KendaraanAdapter extends RecyclerView.Adapter<KendaraanAdapter.CustomViewHolder> {
    private List<KendaraanPost> dataList;
    private Context context;

    public KendaraanAdapter(Context context, List<KendaraanPost> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        TextView platNomor, namaKendaraan, jenisKendaraaan, hargaKendaraan, deskripsiKendaraan;
        private ImageView coveringKendaraan;

        CustomViewHolder(View itemView){
            super(itemView);
            mView = itemView;

//            namaKendaraan = mView.findViewById(R.id.id_head);
//            platNomor = mView.findViewById(R.id.food_location);
//            deskripsiKendaraan = mView.findViewById(R.id.id_body);
//            hargaKendaraan = mView.findViewById(R.id.id_footer);

            namaKendaraan = mView.findViewById(R.id.kendaraanName);
            platNomor = mView.findViewById(R.id.platKendaraan);
            deskripsiKendaraan = mView.findViewById(R.id.deskripsiKendaraan);
            hargaKendaraan = mView.findViewById(R.id.hargaKendaraan);
            jenisKendaraaan = mView.findViewById(R.id.jenisKendaraan);
        }
    }

    @Override
    public KendaraanAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_output_kendaraan, parent, false);
        return new KendaraanAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KendaraanAdapter.CustomViewHolder holder, int position) {
        holder.platNomor.setText(dataList.get(position).getPlatNomor());
        holder.namaKendaraan.setText(dataList.get(position).getNamaKendaraan());
        holder.deskripsiKendaraan.setText(dataList.get(position).getDeskripsiKendaraan());
        holder.hargaKendaraan.setText(dataList.get(position).getHargaKendaraan());
        holder.jenisKendaraaan.setText(dataList.get(position).getJenisKendaraan());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

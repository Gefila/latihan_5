package com.example.latihan5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.CustomViewHolder> {
    private List<HotelPost> dataList;
    private Context context;

    public HotelAdapter(Context context, List<HotelPost> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        TextView namaHotel, rangeHarga, lokasiHotel, deskripsi;
        //private ImageView coveringHotel;

        CustomViewHolder(View itemView){
            super(itemView);
            mView = itemView;

            namaHotel = mView.findViewById(R.id.hotelName);
            lokasiHotel = mView.findViewById(R.id.alamatHotel);
            deskripsi = mView.findViewById(R.id.deskripsiHotel);
            rangeHarga = mView.findViewById(R.id.hargaHotel);
            //coveringHotel = mView.findViewById(R.id.hotelFoto);
        }
    }

    @Override
    public HotelAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_output_hotel, parent, false);
        return new HotelAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotelAdapter.CustomViewHolder holder, int position) {
        holder.namaHotel.setText(dataList.get(position).getNamaHotel());
        holder.lokasiHotel.setText(dataList.get(position).getLokasi());
        holder.deskripsi.setText(dataList.get(position).getDeskripsi());
        holder.rangeHarga.setText(dataList.get(position).getRangeHarga());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
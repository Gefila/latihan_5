package com.example.latihan5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.CustomViewHolder> {
    private List<FoodPost> dataList;
    private Context context;

    public FoodAdapter(Context context, List<FoodPost> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        TextView namaMakanan, lokasiToko, hargaMakanan, deskripsiMakanan;
        private ImageView coveringFood;

        CustomViewHolder(View itemView){
            super(itemView);
            mView = itemView;

            deskripsiMakanan = mView.findViewById(R.id.id_body);
            lokasiToko = mView.findViewById(R.id.food_location);
            namaMakanan = mView.findViewById(R.id.id_head);
            hargaMakanan = mView.findViewById(R.id.id_footer);
            coveringFood = mView.findViewById(R.id.foodFoto);
        }
    }

    @Override
    public FoodAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_output, parent, false);
        return new FoodAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodAdapter.CustomViewHolder holder, int position) {
        holder.lokasiToko.setText(dataList.get(position).getLokasiToko());
        holder.namaMakanan.setText(dataList.get(position).getNamaMakanan());
        holder.hargaMakanan.setText(dataList.get(position).getHargaMakanan());
        holder.deskripsiMakanan.setText(dataList.get(position).getDeskripsiMakanan());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

package com.example.latihan5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InformationAdapter  extends RecyclerView.Adapter<InformationAdapter.CustomViewHolder> {
    private List<InformationPost> dataList;
    private Context context;

    public InformationAdapter(Context context, List<InformationPost> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        TextView tanggalInformation, email, komentar, tanggapan;
        RatingBar rating;
        //private ImageView coveringFood;

        CustomViewHolder(View itemView){
            super(itemView);
            mView = itemView;

            tanggalInformation = mView.findViewById(R.id.id_body);
            email = mView.findViewById(R.id.food_location);
            komentar = mView.findViewById(R.id.id_head);
            tanggapan = mView.findViewById(R.id.id_footer);
            rating = mView.findViewById(R.id.rb_information_rating);
        }

    }
    @Override
    public InformationAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_output_information, parent, false);
        return new InformationAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InformationAdapter.CustomViewHolder holder, int position) {
        holder.tanggalInformation.setText(dataList.get(position).getTglInformation());
        holder.email.setText(dataList.get(position).getEmail());
        holder.komentar.setText(dataList.get(position).getKomentar());
        holder.tanggapan.setText(dataList.get(position).getTanggapan());
        holder.rating.setRating(dataList.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

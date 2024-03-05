package com.example.englishapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.englishapp.R;
import com.example.englishapp.domain.MostViewedDomain;

import java.util.ArrayList;


public class MostViewAdapter extends RecyclerView.Adapter<MostViewAdapter.ViewHolder> {

    ArrayList<MostViewedDomain> mostViewDomains;

    public MostViewAdapter(ArrayList<MostViewedDomain> mostViewDomains) {
        this.mostViewDomains = mostViewDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTxt.setText(String.valueOf(mostViewDomains.get(position).getTitle()));
        holder.subTitleTxt.setText(String.valueOf(mostViewDomains.get(position).getSubtitle()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(mostViewDomains.get(position).getUrl(), "drawable", holder.itemView.getContext().getPackageName());


        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.removeItem);
    }


    @Override
    public int getItemCount() {
        return mostViewDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt,subTitleTxt;
        ImageView removeItem;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            subTitleTxt = itemView.findViewById(R.id.subTitleTxt);
            removeItem = itemView.findViewById(R.id.img_view);
        }
    }
}

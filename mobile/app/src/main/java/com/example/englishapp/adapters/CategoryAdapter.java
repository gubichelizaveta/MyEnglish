package com.example.englishapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.englishapp.R;
import com.example.englishapp.domain.CategoryDomain;

import java.util.ArrayList;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    public interface OnStateClickListener{
        void onStateClick(CategoryDomain domain, int position);
    }

    private final OnStateClickListener onClickListener;
    ArrayList<CategoryDomain> categoryDomain;

    public CategoryAdapter(ArrayList<CategoryDomain> categoryDomain, Context context, OnStateClickListener onClickListener) {
        this.categoryDomain = categoryDomain;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder2, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTxt.setText(String.valueOf(categoryDomain.get(position).getTitle()));
        CategoryDomain domains = categoryDomain.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onStateClick(domains,holder.getAdapterPosition());
            }
        });

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(categoryDomain.get(position).getUrl(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.removeItem);
    }


    @Override
    public int getItemCount() {
        return categoryDomain.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        ImageView removeItem;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            removeItem = itemView.findViewById(R.id.img_view);
        }
    }
}

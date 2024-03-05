package com.example.englishapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.R;
import com.example.englishapp.domain.ProgressDomain;

import java.util.ArrayList;

public class ProgressAdapter extends RecyclerView.Adapter<ProgressAdapter.ViewHolder> {
//    interface OnStateClickListener{
//        void onStateClick(ProgressDomain domain, int position);
//    }

    //private final ProgressAdapter.OnStateClickListener onClickListener;
    ArrayList<ProgressDomain> progressDomain;

    public ProgressAdapter(ArrayList<ProgressDomain> progressDomain, Context context) {
        this.progressDomain = progressDomain;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_element, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.resultTxt.setText("Ваш результат: " + progressDomain.get(position).getMark());
        holder.Count.setText("№ попытки: "+progressDomain.get(position).getCount());
        ProgressDomain domains = progressDomain.get(position);
    }

    @Override
    public int getItemCount() {
        return progressDomain.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView resultTxt;
        TextView Count;

        public ViewHolder(View itemView) {
            super(itemView);
            resultTxt = itemView.findViewById(R.id.resultTxt);
            Count = itemView.findViewById(R.id.countTxt);
        }
    }
}

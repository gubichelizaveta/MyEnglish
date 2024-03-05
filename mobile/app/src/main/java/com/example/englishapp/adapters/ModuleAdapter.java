package com.example.englishapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.R;
import com.example.englishapp.domain.ModuleDomain;
import com.example.englishapp.models.Module;

import java.util.ArrayList;


public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder>{
    public interface OnStateClickListener{
        void onStateClick(Module domain, int position);
    }

    private final OnStateClickListener onClickListener;
    ArrayList<Module> moduleDomain;

    public ModuleAdapter(ArrayList<Module> moduleDomain, Context context, OnStateClickListener onClickListener) {
        this.moduleDomain = moduleDomain;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_module_view, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTxt.setText(String.valueOf(moduleDomain.get(position).getModuleTitle()));
        //holder.TerminCount.setText(String.valueOf(moduleDomain.get(position).getCount()));
        Module domains = moduleDomain.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onStateClick(domains,holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return moduleDomain.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        //TextView TerminCount;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            //TerminCount = itemView.findViewById(R.id.subTitleTxt);
        }
    }
}

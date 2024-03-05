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
import com.example.englishapp.domain.CardDomain;
import com.example.englishapp.models.Card;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{
    public interface OnStateClickListener{
        void onStateClick(Card domain, int position);
    }

    private final OnStateClickListener onClickListener;
    ArrayList<Card> cardDomain;

    public CardAdapter(ArrayList<Card> cardDomain, Context context, OnStateClickListener onClickListener) {
        this.cardDomain = cardDomain;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_card, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Word.setText(String.valueOf(cardDomain.get(position).getWord()));
        holder.Translate.setText(String.valueOf(cardDomain.get(position).getTranslation()));
        holder.Transcription.setText(String.valueOf(cardDomain.get(position).getTranscription()));
        Card domains = cardDomain.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onStateClick(domains,holder.getAdapterPosition());
            }
        });

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(cardDomain.get(position).getPicture(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.Picture);
    }


    @Override
    public int getItemCount() {
        return cardDomain.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Word;
        ImageView Picture;
        TextView Transcription;
        TextView Translate;

        public ViewHolder(View itemView) {
            super(itemView);
            Word = itemView.findViewById(R.id.titleTxtWord);
            Picture = itemView.findViewById(R.id.imageQuestion);
            Translate = itemView.findViewById(R.id.textTranslate);
            Transcription = itemView.findViewById(R.id.titleTranscription);
        }
    }
}

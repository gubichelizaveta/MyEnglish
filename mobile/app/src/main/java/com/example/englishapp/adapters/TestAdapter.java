package com.example.englishapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.englishapp.R;
import com.example.englishapp.domain.TestDomain;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder>{
    public interface OnStateClickListener{
        void onStateClick(TestDomain domain, int position);
    }
    public interface  OnCheckedChangeListener{
        void onCheckedChange(TestDomain domain,int position,String Answer);
    }
    private final OnStateClickListener onClickListener;
    private final OnCheckedChangeListener onCheckedChangeListener;
    ArrayList<TestDomain> testDomain;

    public TestAdapter(ArrayList<TestDomain> testDomain, Context context, OnStateClickListener onClickListener,
                       OnCheckedChangeListener onCheckedChangeListener) {
        this.testDomain = testDomain;
        this.onClickListener = onClickListener;
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_in_test, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Answer1.setText(String.valueOf(testDomain.get(position).getAnswerVariant1()));
        holder.Answer2.setText(String.valueOf(testDomain.get(position).getAnswerVariant2()));
        holder.Answer3.setText(String.valueOf(testDomain.get(position).getAnswerVariant3()));
        TestDomain domains = testDomain.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onStateClick(domains,holder.getAdapterPosition());
            }
        });
        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                String Answer = "";
                switch (i) {
                    case R.id.answerOne:
                        Answer = domains.getAnswerVariant1();
                        break;
                    case R.id.answerTwo:
                        Answer = domains.getAnswerVariant2();
                        break;
                    case R.id.answerThree:
                        Answer = domains.getAnswerVariant3();
                        break;
                    default:
                        break;
                }
                onCheckedChangeListener.onCheckedChange(domains,holder.getAdapterPosition(),Answer);
            }
        });
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(testDomain.get(position).getQuestionText(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.QuestionText);
    }


    @Override
    public int getItemCount() {
        return testDomain.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView QuestionText;
        TextView Answer1;
        TextView Answer2;
        TextView Answer3;
        RadioGroup radioGroup;
        public ViewHolder(View itemView) {
            super(itemView);
            QuestionText = itemView.findViewById(R.id.imageQuestion);
            Answer1 = itemView.findViewById(R.id.answerOne);
            Answer2 = itemView.findViewById(R.id.answerTwo);
            Answer3 = itemView.findViewById(R.id.answerThree);
            radioGroup = itemView.findViewById(R.id.radioGroup);
        }
    }
}

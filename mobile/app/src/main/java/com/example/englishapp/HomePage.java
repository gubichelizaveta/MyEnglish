package com.example.englishapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.activity.FavoritesModulesActivity;
import com.example.englishapp.activity.LogInActivity;
import com.example.englishapp.activity.ModulesActivity;
import com.example.englishapp.activity.ProgressActivity;
import com.example.englishapp.activity.TestCategoryActivity;
import com.example.englishapp.adapters.CategoryAdapter;
import com.example.englishapp.adapters.MostViewAdapter;
import com.example.englishapp.domain.CategoryDomain;
import com.example.englishapp.domain.MostViewedDomain;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView.Adapter adapter2;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewCat;
    private Button btnLogin;
    private ImageView logOUT;
    String UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        logOUT = findViewById(R.id.imageView9);

        Bundle arguments = getIntent().getExtras();
        UserName = arguments.get("UserName").toString();

        recyclerView = findViewById(R.id.viewMostView);
        recyclerViewCat = findViewById(R.id.viewCategory);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewCat.setLayoutManager(linearLayoutManager2);

        ArrayList<CategoryDomain> cat = new ArrayList<>();
        cat.add(new CategoryDomain("Modules", "cube"));
        cat.add(new CategoryDomain("Favorites", "favorite"));
        cat.add(new CategoryDomain("Test", "test"));
        cat.add(new CategoryDomain("Progress", "progress"));
        CategoryAdapter.OnStateClickListener stateClickListener = new CategoryAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(CategoryDomain domain, int position) {
                if(domain.getTitle() == "Modules")
                {
                    Intent intent = new Intent(getApplicationContext(), ModulesActivity.class);
                    intent.putExtra("UserName",UserName);
                    startActivity(intent);
                }
                else if(domain.getTitle() == "Favorites")
                {
                    Intent intent = new Intent(getApplicationContext(), FavoritesModulesActivity.class);
                    intent.putExtra("UserName",UserName);
                    startActivity(intent);
                }
                else if(domain.getTitle() == "Test")
                {
                    Intent intent = new Intent(getApplicationContext(), TestCategoryActivity.class);
                    intent.putExtra("UserName",UserName);
                    startActivity(intent);
                }
                else if(domain.getTitle() == "Progress")
                {
                    Intent intent = new Intent(getApplicationContext(), ProgressActivity.class);
                    intent.putExtra("UserName",UserName);
                    startActivity(intent);
                }
            }
        };
        adapter2 = new CategoryAdapter(cat,this,stateClickListener);
        recyclerViewCat.setAdapter(adapter2);



        ArrayList<MostViewedDomain> mostviewed =new ArrayList<>();
        mostviewed.add(new MostViewedDomain("Уильям Шекспир придумал более 1000 английских слов", "Он настолько сильно изменил английский язык, что его можно поделить на «до» и «после» Шекспира!", "en3"));
        mostviewed.add(new MostViewedDomain("В США нет официального государственного языка", "Несмотря на то что английский язык в США самый распространенный, он далеко не единственный, на котором говорят американцы.", "en"));
        mostviewed.add(new MostViewedDomain("В английском языке когда-то было на 3 буквы больше, чем сейчас", "За долгие годы английский алфавит сократился! Сейчас в нем 26 букв, а когда-то было 29.", "en2"));
        mostviewed.add(new MostViewedDomain("Cute Aggression", "Выражение “cute aggression” переводится как «агрессивное умиление» или «неистовое умиление»", "cute"));

        adapter=new MostViewAdapter(mostviewed);
        recyclerView.setAdapter(adapter);

        logOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(intent);
            }
        });
    }
}

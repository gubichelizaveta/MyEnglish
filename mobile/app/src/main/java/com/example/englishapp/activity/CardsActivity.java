package com.example.englishapp.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.R;
import com.example.englishapp.adapters.CardAdapter;
import com.example.englishapp.api.OnFetchDataListener;
import com.example.englishapp.api.RequestManager;
import com.example.englishapp.localDB.DBHelper;
import com.example.englishapp.models.Card;
import com.example.englishapp.models.FavoriteModule;
import com.example.englishapp.models.Module;
import com.google.android.material.button.MaterialButton;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class CardsActivity extends AppCompatActivity {
    MaterialButton button;
    String ModuleName;
    private RecyclerView.Adapter adapter2;
    private RecyclerView recyclerViewModules;
    String mn;
    String UserName;

    CardAdapter.OnStateClickListener stateClickListener;
    ArrayList<Card> cards = new ArrayList<>();
    Bundle arguments2;
    ArrayList<Module> favoritesModules = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cards);
        Bundle arguments = getIntent().getExtras();
        ModuleName = arguments.get("ModuleName").toString();

        arguments2 = getIntent().getExtras();
        UserName = arguments.get("UserName").toString();
        mn = ModuleName;
        recyclerViewModules = findViewById(R.id.viewCards);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewModules.setLayoutManager(linearLayoutManager2);
        RequestManager manager = new RequestManager();
        manager.getCards(getCardsListener,ModuleName);
        button = findViewById(R.id.buttonToggle);
        //button.setChecked(false);
        manager.getFavorites(getFavoritesListener,UserName);

        button.addOnCheckedChangeListener(new MaterialButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MaterialButton button, boolean isChecked) {
                if (isChecked) {
                    RequestManager manager = new RequestManager();
                    FavoriteModule fv = new FavoriteModule(mn,arguments2.get("UserName").toString());
                    manager.AddUsersModule(addFavoritesListener,fv);
                } else{
                    RequestManager manager = new RequestManager();
                    manager.DeleteUsersModule(deleteFavoritesListener,mn,arguments2.get("UserName").toString());
                }
            }
            });

        Button button = findViewById(R.id.goToTest);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TestActivity.class);
                intent.putExtra("ModuleNameC",arguments.get("ModuleName").toString());
                intent.putExtra("UserName",arguments2.get("UserName").toString());
                startActivity(intent);
            }
        });

        stateClickListener = new CardAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(Card domain, int position) {

                Toast.makeText(getApplicationContext(), "Был выбран пункт " + domain.getWord(),
                        Toast.LENGTH_SHORT).show();
            }
        };
    }
    private final OnFetchDataListener<List<Card>> getCardsListener = new OnFetchDataListener<List<Card>>() {
        @Override
        public void onFetchData(Response<List<Card>> response) {
            if(response.isSuccessful()) {
                List<Card> mod = response.body();
                for (Card i:
                        mod) {
                    cards.add(i);
                }
                adapter2 = new CardAdapter(cards,getApplicationContext(),stateClickListener);
                recyclerViewModules.setAdapter(adapter2);
            }
        }
        @Override
        public void onFetchError(Throwable error) {
            Log.d("Error",error.getMessage());
        }
    };
    private final OnFetchDataListener<List<FavoriteModule>> getFavoritesListener = new OnFetchDataListener<List<FavoriteModule>>() {
        @Override
        public void onFetchData(Response<List<FavoriteModule>> response) {
            if(response.isSuccessful()) {
                List<FavoriteModule> mod = response.body();
                for (FavoriteModule i:
                        mod) {
                        button.setChecked(false);
                        if(i.getModuleTitle().equals(mn))
                        {

                        Resources res = getResources();
                        Drawable drawable = res.getDrawable(R.drawable.fav32, getTheme());
                        button.setIcon(drawable);
                        button.setChecked(true);
                        }
                    }
                }
        }
        @Override
        public void onFetchError(Throwable error) {
            Log.d("Error",error.getMessage());
        }
    };
    private final OnFetchDataListener<String> addFavoritesListener = new OnFetchDataListener<String>() {
        @Override
        public void onFetchData(Response<String> response) {
            if(response.isSuccessful()) {
                Resources res = getResources();
                Drawable drawable = res.getDrawable(R.drawable.fav32, getTheme());
                button.setIcon(drawable);
                //////
                SQLiteDatabase db = new DBHelper(getApplicationContext()).getWritableDatabase();
                String insertQuery = "INSERT INTO ModuleUser (FavoriteModulesTitle, UsersName) VALUES (?, ?)";
                db.execSQL(insertQuery, new Object[]{mn,arguments2.get("UserName").toString()});
                db.close();
            }
        }
        @Override
        public void onFetchError(Throwable error) {
            Log.d("Error",error.getMessage());
        }
    };
    private final OnFetchDataListener<String> deleteFavoritesListener = new OnFetchDataListener<String>() {
        @Override
        public void onFetchData(Response<String> response) {
            if(response.isSuccessful()) {
                favoritesModules.remove(mn);
                Resources res = getResources();
                Drawable drawable = res.getDrawable(R.drawable.notfav, getTheme());
                button.setIcon(drawable);
                ///
                SQLiteDatabase db = new DBHelper(getApplicationContext()).getWritableDatabase();
                String condition = "UsersName = ? AND FavoriteModulesTitle = ?";
                String[] args = {UserName, mn};

                String query = "DELETE FROM " + "ModuleUser" + " WHERE " + condition;
                db.execSQL(query, args);
                db.close();
            }
        }
        @Override
        public void onFetchError(Throwable error) {
            Log.d("Error",error.getMessage());
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return super.onOptionsItemSelected(item);
    }
}

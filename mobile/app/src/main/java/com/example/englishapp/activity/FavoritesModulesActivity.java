package com.example.englishapp.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.R;
import com.example.englishapp.adapters.ModuleAdapter;
import com.example.englishapp.api.OnFetchDataListener;
import com.example.englishapp.api.RequestManager;
import com.example.englishapp.models.FavoriteModule;
import com.example.englishapp.models.Module;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class FavoritesModulesActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter2;
    private RecyclerView recyclerViewModules;
    String UserName;

    ModuleAdapter.OnStateClickListener stateClickListener;
    ArrayList<Module> modules = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modules_activity);
        recyclerViewModules = findViewById(R.id.viewModule);

        Bundle arguments = getIntent().getExtras();
        UserName = arguments.get("UserName").toString();

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewModules.setLayoutManager(linearLayoutManager2);
//
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot data: snapshot.getChildren())
//                {
//                    String str = data.getValue().toString();
//                    if(str!= null) {
//                        ModuleDomain module = new ModuleDomain(str);
//                        modules.add(module);
//                    }
//                }
//                adapter2 = new ModuleAdapter(modules,getApplicationContext(),stateClickListener);
//                recyclerViewModules.setAdapter(adapter2);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//
//        });
        RequestManager manager = new RequestManager();
        manager.getFavorites(getFavoritesListener,UserName);
        stateClickListener = new ModuleAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(Module domain, int position) {

                Intent intent = new Intent(getApplicationContext(),CardsActivity.class);
                intent.putExtra("ModuleName",domain.getModuleTitle());
                intent.putExtra("UserName", UserName);
                startActivity(intent);
            }
        };
    }

    private final OnFetchDataListener<List<FavoriteModule>> getFavoritesListener = new OnFetchDataListener<List<FavoriteModule>>() {
        @Override
        public void onFetchData(Response<List<FavoriteModule>> response) {
            if(response.isSuccessful()) {
                List<FavoriteModule> mod = response.body();
                for (FavoriteModule i:
                        mod) {
                    Module md = new Module();
                    md.setModuleTitle(i.getModuleTitle());
                    modules.add(md);
                }
                adapter2 = new ModuleAdapter(modules,getApplicationContext(),stateClickListener);
                recyclerViewModules.setAdapter(adapter2);
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

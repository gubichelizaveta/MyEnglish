package com.example.englishapp.activity;

import android.content.Intent;
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
import com.example.englishapp.models.Module;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class TestCategoryActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter2;
    private RecyclerView recyclerViewModules;
    ModuleAdapter.OnStateClickListener stateClickListener;
    String UserName;
    ArrayList<Module> modules = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modules_activity);
        recyclerViewModules = findViewById(R.id.viewModule);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewModules.setLayoutManager(linearLayoutManager2);

        Bundle arguments = getIntent().getExtras();
        UserName = arguments.get("UserName").toString();

        RequestManager manager = new RequestManager();
        manager.getModules(getModulesListener);


        stateClickListener = new ModuleAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(Module domain, int position) {
                Intent intent = new Intent(getApplicationContext(),TestActivity.class);
                intent.putExtra("UserName",UserName);
                intent.putExtra("ModuleNameC",domain.getModuleTitle());
                startActivity(intent);
            }
        };
    }
    private final OnFetchDataListener<List<Module>> getModulesListener = new OnFetchDataListener<List<Module>>() {
        @Override
        public void onFetchData(Response<List<Module>> response) {
            if(response.isSuccessful()) {
                List<Module> mod = response.body();
                for (Module i:
                        mod) {
                    modules.add(i);
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

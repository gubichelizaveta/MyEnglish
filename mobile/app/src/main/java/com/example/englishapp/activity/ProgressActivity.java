package com.example.englishapp.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.R;
import com.example.englishapp.adapters.ModuleAdapter;
import com.example.englishapp.adapters.ProgressAdapter;
import com.example.englishapp.api.OnFetchDataListener;
import com.example.englishapp.api.RequestManager;
import com.example.englishapp.domain.ProgressDomain;
import com.example.englishapp.domain.TestResultDomain;
import com.example.englishapp.models.Module;
import com.example.englishapp.models.TestResults;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class ProgressActivity extends AppCompatActivity {

    ArrayList<String> modules = new ArrayList<>();
    AutoCompleteTextView autoCompleteTextView;
    private RecyclerView.Adapter adapter2;
    private RecyclerView recyclerViewModules;
    ArrayList<ProgressDomain> progresses = new ArrayList<>();
    String UserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_activity);
        recyclerViewModules = findViewById(R.id.viewProgress);

        Bundle arguments = getIntent().getExtras();
        UserName = arguments.get("UserName").toString();
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewModules.setLayoutManager(linearLayoutManager2);

        autoCompleteTextView = findViewById(R.id.filled_exposed);
        RequestManager manager = new RequestManager();
        manager.getModules(getModulesListener);


        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = autoCompleteTextView.getText().toString();
                progresses.clear();
                RequestManager mn = new RequestManager();
                mn.getUsersResult(getUsersResultListener,name,UserName);
            }
        });
    }
    private final OnFetchDataListener<List<Module>> getModulesListener = new OnFetchDataListener<List<Module>>() {
        @Override
        public void onFetchData(Response<List<Module>> response) {
            if(response.isSuccessful()) {
                List<Module> mod = response.body();
                for (Module i:
                        mod) {
                    modules.add(i.getModuleTitle());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.drop_down_item,modules);
                autoCompleteTextView.setAdapter(adapter);
            }
        }
        @Override
        public void onFetchError(Throwable error) {
            Log.d("Error",error.getMessage());
        }
    };
    private final OnFetchDataListener<List<TestResultDomain>> getUsersResultListener = new OnFetchDataListener<List<TestResultDomain>>() {
        @Override
        public void onFetchData(Response<List<TestResultDomain>> response) {
            if(response.isSuccessful()) {
                Integer count = 1;
                List<TestResultDomain> mod = response.body();
                for (TestResultDomain i:
                        mod) {
                    ProgressDomain progress = new ProgressDomain(String.valueOf(i.getMark()),String.valueOf(count));
                    progresses.add(progress);
                    count++;
                }
                adapter2 = new ProgressAdapter(progresses,getApplicationContext());
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

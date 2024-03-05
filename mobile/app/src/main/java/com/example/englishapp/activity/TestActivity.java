package com.example.englishapp.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.R;
import com.example.englishapp.adapters.ModuleAdapter;
import com.example.englishapp.adapters.TestAdapter;
import com.example.englishapp.api.OnFetchDataListener;
import com.example.englishapp.api.RequestManager;
import com.example.englishapp.domain.TestDomain;
import com.example.englishapp.models.Module;
import com.example.englishapp.models.TestResults;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class TestActivity extends AppCompatActivity {
    String ModuleName;
    private RecyclerView.Adapter adapter2;
    private RecyclerView recyclerViewModules;
    String TestId;

    TestAdapter.OnStateClickListener stateClickListener;
    TestAdapter.OnCheckedChangeListener checkedChangeListener;
    ArrayList<TestDomain> tests = new ArrayList<>();
    private int CountCorrectAnswer = 0;
    String UserName;
    private int NumberOfAttempts = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        Bundle arguments = getIntent().getExtras();
        ModuleName = arguments.get("ModuleNameC").toString();
        UserName = arguments.get("UserName").toString();

        recyclerViewModules = findViewById(R.id.viewTests);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewModules.setLayoutManager(linearLayoutManager2);

        RequestManager manager = new RequestManager();
        manager.getTests(getTestsListener,ModuleName);

        checkedChangeListener = new TestAdapter.OnCheckedChangeListener() {
            @Override
            public void onCheckedChange(TestDomain domain, int position,String Answer) {

                if((domain.getCorrectAnswer()).equals(Answer))
                {
                    CountCorrectAnswer++;
                }
            }
        };


        Button btn = findViewById(R.id.resultBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Ваш результат:" + CountCorrectAnswer*100/3+ "%",Toast.LENGTH_SHORT).show();
                //ref2.child(String.valueOf(NumberOfAttempts+1)).setValue(CountCorrectAnswer*100/4);
                TestResults tr = new TestResults(Integer.parseInt(TestId),UserName,CountCorrectAnswer);
                RequestManager mn = new RequestManager();
                mn.TestResultAdd(addTestsResultListener,tr);
                NumberOfAttempts = 0;
            }

        });
    }
    private final OnFetchDataListener<List<TestDomain>> getTestsListener = new OnFetchDataListener<List<TestDomain>>() {
        @Override
        public void onFetchData(Response<List<TestDomain>> response) {
            if(response.isSuccessful()) {
                List<TestDomain> mod = response.body();
                for (TestDomain i:
                        mod) {
                    tests.add(i);
                    TestId = i.getTestId();
                }
                adapter2 = new TestAdapter(tests,getApplicationContext(),stateClickListener,checkedChangeListener);
                recyclerViewModules.setAdapter(adapter2);
                Toast.makeText(getApplicationContext(),"TEstID:" + TestId,Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onFetchError(Throwable error) {
            Log.d("Error",error.getMessage());
        }
    };
    private final OnFetchDataListener<String> addTestsResultListener = new OnFetchDataListener<String>() {
        @Override
        public void onFetchData(Response<String> response) {
            if(response.isSuccessful()) {
                Log.d("TR", "Результат теста добавлен в бд");
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

package com.example.englishapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.englishapp.HomePage;
import com.example.englishapp.R;
import com.example.englishapp.api.OnFetchDataListener;
import com.example.englishapp.api.RequestUserManager;
import com.example.englishapp.models.User;

import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;

import retrofit2.Response;

public class LogInActivity extends AppCompatActivity {
    private EditText UserNameLogin;
    private EditText UserPasswordLogin;
    private Button btnLogin;
    private String UserNameForActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        UserNameLogin = findViewById(R.id.UserNameLogin);
        UserPasswordLogin = findViewById(R.id.UserPasswordLogin);
        btnLogin = findViewById(R.id.buttonLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserNameLogin.getText().toString().isEmpty() || UserPasswordLogin.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Введите данные",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    RequestUserManager userManager = new RequestUserManager();
                    userManager.getUser(getUserListener, UserNameLogin.getText().toString(),UserPasswordLogin.getText().toString());
                }
            }
        });
    }

    private final OnFetchDataListener<Boolean> getUserListener = new OnFetchDataListener<Boolean>() {
        @Override
        public void onFetchData(Response<Boolean> response) {
            if(response.isSuccessful()) {
                if(response.body().equals(true)) {
                  UserNameForActivity = UserNameLogin.getText().toString();
                  Intent intent = new Intent(getApplicationContext(), HomePage.class);
                  intent.putExtra("UserName", UserNameForActivity);
                  startActivity(intent);
              }
              else {
                  Toast.makeText(getApplicationContext(),"Неправильный пароль", Toast.LENGTH_LONG).show();
              }
            }
            else {
                Toast.makeText(getApplicationContext(),"Error by getting ", Toast.LENGTH_LONG).show();
            }
        }
        @Override
        public void onFetchError(Throwable error) {
            Toast.makeText(getApplicationContext(), "Пользователь не существует", Toast.LENGTH_LONG).show();
        }
    };
}

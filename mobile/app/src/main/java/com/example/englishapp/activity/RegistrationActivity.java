package com.example.englishapp.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import com.example.englishapp.localDB.DBHelper;
import com.example.englishapp.models.User;
import com.example.englishapp.utils.Validation;

import java.util.ArrayList;

import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    private EditText UserNameSignUp;
    private EditText UserPasswordSignUp;
    private EditText PasswordRepeat;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        UserNameSignUp = findViewById(R.id.UserNameSignUp);
        UserPasswordSignUp = findViewById(R.id.UserPasswordSignUp);
        PasswordRepeat = findViewById(R.id.RepeatPassword);

        btnSignUp = findViewById(R.id.buttonsignin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserNameSignUp.getText().toString().isEmpty() || UserPasswordSignUp.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Введите данные",Toast.LENGTH_SHORT).show();
                }
                else if(!Validation.isLoginValid(UserNameSignUp.getText().toString()) && !Validation.isPasswordValid(UserPasswordSignUp.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Логин и пароль введены некорректно",Toast.LENGTH_SHORT).show();
                }
                else if(!Validation.isLoginValid(UserNameSignUp.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Логин введен некорректно",Toast.LENGTH_SHORT).show();
                }
                else if(!Validation.isPasswordValid(UserPasswordSignUp.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Пароль введен некорректно",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    RequestUserManager userManager = new RequestUserManager();
                    userManager.getAllUsers(getAllUsersListener);
                }
            }
        });
    }

    private final OnFetchDataListener<ArrayList<User>> getAllUsersListener = new OnFetchDataListener<ArrayList<User>>() {
        @Override
        public void onFetchData(Response<ArrayList<User>> response) {
            if(response.isSuccessful()) {
                int k = 0;
                ArrayList<User> list = response.body();
                for (User i:
                     list) {
                    if(i.getUserName().equals(UserNameSignUp.getText().toString()))
                    {k++;}
                }
                if(k!=0)
                {Log.d("USERCHECK","Пользователь с таким именем уже существует");}
                else
                {
                    Log.d("USERCHECK","Пользователь может быть зарегистрирован");
                    if(UserPasswordSignUp.getText().toString().equals(PasswordRepeat.getText().toString()))
                    {
                        RequestUserManager userManager = new RequestUserManager();
                        User user = new User();
                        user.setUserName(UserNameSignUp.getText().toString());
                        user.setPassword(UserPasswordSignUp.getText().toString());
                        userManager.register(AddUserListener,user);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Пароли не совпадают",Toast.LENGTH_SHORT).show();
                    }

                }
            }
            else {
                Toast.makeText(getApplicationContext(),"Error by getting", Toast.LENGTH_LONG).show();
            }
        }
        @Override
        public void onFetchError(Throwable error) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }
    };
    private final OnFetchDataListener<String> AddUserListener = new OnFetchDataListener<String>() {
        @Override
        public void onFetchData(Response<String> response) {
            if(response.isSuccessful()) {

                SQLiteDatabase db = new DBHelper(getApplicationContext()).getWritableDatabase();
                String insertQuery = "INSERT INTO Users (UserName, Password) VALUES (?, ?)";
                db.execSQL(insertQuery, new Object[]{UserNameSignUp.getText().toString(), UserPasswordSignUp.getText().toString()});
                db.close();

                Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Error by adding", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onFetchError(Throwable error) {
            Toast.makeText(getApplicationContext(), "Ошибка запроса: " + error.getMessage(), Toast.LENGTH_LONG).show();
        }
    };
}

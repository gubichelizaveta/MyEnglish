package com.example.englishapp.api;

import com.example.englishapp.config.ApiConfig;
import com.example.englishapp.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RequestUserManager {
    private final CollectionApi collectionAPI = ApiConfig.create();

    public void getAllUsers(OnFetchDataListener<ArrayList<User>> listener) {
        Call<ArrayList<User>> call = collectionAPI.getAllUsers();

        call.enqueue(new Callback<ArrayList<User>>() {
            @Override public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                listener.onFetchData(response);
            }
            @Override public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                listener.onFetchError(t);
            }
        });
    }

    public void getUser(OnFetchDataListener<Boolean> listener, String UserName,String Password) {
        Call<Boolean> call = collectionAPI.getUser(UserName,Password);
        call.enqueue(new Callback<Boolean>() {
            @Override public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                listener.onFetchData(response);
            }
            @Override public void onFailure(Call<Boolean> call, Throwable t) {
                listener.onFetchError(t);
            }
        });
    }

    public void register(OnFetchDataListener<String> listener, User user) {
        Call<String> call = collectionAPI.register(user);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                listener.onFetchData(response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onFetchError(t);
            }
        });
    }
}

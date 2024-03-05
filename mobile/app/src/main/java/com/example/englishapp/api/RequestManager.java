package com.example.englishapp.api;

import com.example.englishapp.config.ApiConfig;
import com.example.englishapp.domain.CardDomain;
import com.example.englishapp.domain.ModuleDomain;
import com.example.englishapp.domain.TestDomain;
import com.example.englishapp.domain.TestResultDomain;
import com.example.englishapp.models.Card;
import com.example.englishapp.models.FavoriteModule;
import com.example.englishapp.models.Module;
import com.example.englishapp.models.TestResults;
import com.example.englishapp.models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RequestManager {
    private final CollectionApi collectionAPI = ApiConfig.create();
    public void getModules(OnFetchDataListener<List<Module>> listener) {
        Call<List<Module>> call = collectionAPI.getModules();

        call.enqueue(new Callback<List<Module>>() {
            @Override public void onResponse(Call<List<Module>> call, Response<List<Module>> response) {
                listener.onFetchData(response);
            }
            @Override public void onFailure(Call<List<Module>> call, Throwable t) {
                listener.onFetchError(t);
            }
        });
    }

    public void getCards(OnFetchDataListener<List<Card>> listener, String ModuleTitle) {
        Call<List<Card>> call = collectionAPI.getCards(ModuleTitle);

        call.enqueue(new Callback<List<Card>>() {
            @Override public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                listener.onFetchData(response);
            }
            @Override public void onFailure(Call<List<Card>> call, Throwable t) {
                listener.onFetchError(t);
            }
        });
    }

    public void getFavorites(OnFetchDataListener<List<FavoriteModule>> listener,String UserName) {
        Call<List<FavoriteModule>> call = collectionAPI.getFavorites(UserName);

        call.enqueue(new Callback<List<FavoriteModule>>() {
            @Override public void onResponse(Call<List<FavoriteModule>> call, Response<List<FavoriteModule>> response) {
                listener.onFetchData(response);
            }
            @Override public void onFailure(Call<List<FavoriteModule>> call, Throwable t) {
                listener.onFetchError(t);
            }
        });
    }
    public void AddUsersModule(OnFetchDataListener<String> listener, FavoriteModule module) {
        Call<String> call = collectionAPI.AddUsersModule(module);

        call.enqueue(new Callback<String>() {
            @Override public void onResponse(Call<String> call, Response<String> response) {
                listener.onFetchData(response);
            }
            @Override public void onFailure(Call<String> call, Throwable t) {
                listener.onFetchError(t);
            }
        });
    }
    public void DeleteUsersModule(OnFetchDataListener<String> listener,String ModuleTitle, String UserName) {
        Call<String> call = collectionAPI.DeleteUsersModule(ModuleTitle,UserName);

        call.enqueue(new Callback<String>() {
            @Override public void onResponse(Call<String> call, Response<String> response) {
                listener.onFetchData(response);
            }
            @Override public void onFailure(Call<String> call, Throwable t) {
                listener.onFetchError(t);
            }
        });
    }

    public void getTests(OnFetchDataListener<List<TestDomain>> listener, String ModuleTitle) {
        Call<List<TestDomain>> call = collectionAPI.getTests(ModuleTitle);

        call.enqueue(new Callback<List<TestDomain>>() {
            @Override public void onResponse(Call<List<TestDomain>> call, Response<List<TestDomain>> response) {
                listener.onFetchData(response);
            }
            @Override public void onFailure(Call<List<TestDomain>> call, Throwable t) {
                listener.onFetchError(t);
            }
        });
    }

    public void TestResultAdd(OnFetchDataListener<String> listener, TestResults tr) {
        Call<String> call = collectionAPI.TestResultAdd(tr);

        call.enqueue(new Callback<String>() {
            @Override public void onResponse(Call<String> call, Response<String> response) {
                listener.onFetchData(response);
            }
            @Override public void onFailure(Call<String> call, Throwable t) {
                listener.onFetchError(t);
            }
        });
    }

    public void getUsersResult(OnFetchDataListener<List<TestResultDomain>> listener, String ModuleTitle, String UserName) {
        Call<List<TestResultDomain>> call = collectionAPI.getUsersResult(UserName,ModuleTitle);

        call.enqueue(new Callback<List<TestResultDomain>>() {
            @Override public void onResponse(Call<List<TestResultDomain>> call, Response<List<TestResultDomain>> response) {
                listener.onFetchData(response);
            }
            @Override public void onFailure(Call<List<TestResultDomain>> call, Throwable t) {
                listener.onFetchError(t);
            }
        });
    }
}

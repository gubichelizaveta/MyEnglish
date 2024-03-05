package com.example.englishapp.config;

import com.example.englishapp.api.CollectionApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {
    public static final String API_URL = "http://10.0.2.2:5000/";

    public static CollectionApi create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(CollectionApi.class);
    }
}

package com.example.englishapp.api;

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
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CollectionApi {

    @GET("users")
    Call<ArrayList<User>> getAllUsers();

    @GET("users/{UserName}/{Password}")
    Call<Boolean> getUser(@Path("UserName") String UserName,@Path("Password") String Password);

    @POST("user/add")
    Call<String> register(@Body User user);

    @GET("getAllModules")
    Call<List<Module>> getModules();

    @GET("getCards/{ModuleTitle}")
    Call<List<Card>> getCards(@Path("ModuleTitle") String ModuleTitle);

    @GET("usersModule/{UserName}")
    Call<List<FavoriteModule>> getFavorites(@Path("UserName") String UserName);

    @POST("favoriteModule/add")
    Call<String> AddUsersModule(@Body FavoriteModule fvrModule);

    @DELETE("deleteFavorite/{ModuleTitle}/{UserName}")
    Call<String> DeleteUsersModule(@Path("ModuleTitle") String ModuleTitle,@Path("UserName") String UserName);

    @GET("getModulesTests/{ModuleTitle}")
    Call<List<TestDomain>> getTests(@Path("ModuleTitle") String ModuleTitle);

    @POST("TestResultAdd")
    Call<String> TestResultAdd(@Body TestResults testResults);

    @GET("UsersResult/{UserName}/{ModuleTitle}")
    Call<List<TestResultDomain>> getUsersResult(@Path("UserName") String UserName, @Path("ModuleTitle") String ModuleTitle);

}

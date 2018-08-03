package com.example.amanuel.myapplication.Client;

import com.example.amanuel.myapplication.model.Cat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CatApi {
    @GET("cats")
    Call<List<Cat>> getCats(@Query("page") int ind);
}

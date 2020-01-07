package com.example.loginapp.api;


import com.example.loginapp.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by delaroy on 3/22/17.
 */
public interface Service {
 @GET("/search/users?q=language:java+location:lagos")
 //  @GET("users")
    Call<ItemResponse> getItems();
}

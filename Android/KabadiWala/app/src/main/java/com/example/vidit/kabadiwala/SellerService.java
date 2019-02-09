package com.example.vidit.kabadiwala;

import org.json.JSONArray;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SellerService
{
    @GET("/.json?auth=OeuI7WZk0HBQdeGXiUMXW4zt2s0tykYiDy3iyai7")
    Call<FeedResponse> getFeed();

    @POST("/.json?auth=OeuI7WZk0HBQdeGXiUMXW4zt2s0tykYiDy3iyai7")
    Call<JSONArray> uploadFeed(@Body JSONArray card);
}

package com.example.vidit.kabadiwala;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BuyerService
{
    @GET("/.json?auth=OeuI7WZk0HBQdeGXiUMXW4zt2s0tykYiDy3iyai7")
    Call<ConsignmentsResponse> getConsignments();
}

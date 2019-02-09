package com.example.vidit.kabadiwala;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient
{
    private static Retrofit retrofit;
    private static BuyerService buyerService;
    private static SellerService sellerService;
    static Retrofit getInstance()
    {
        if(retrofit==null)
        {
            Retrofit.Builder builder=new Retrofit.Builder().baseUrl("https://kabadiwala-2.firebaseio.com").addConverterFactory(GsonConverterFactory.create());
            retrofit=builder.build();
        }
        return retrofit;
    }

    static BuyerService getBuyerService()
    {
        if(buyerService==null)
        {
            buyerService=getInstance().create(BuyerService.class);
        }
        return buyerService;
    }

    static SellerService getSellerService()
    {
        if(sellerService==null)
        {
            sellerService=getInstance().create(SellerService.class);
        }
        return sellerService;
    }
}

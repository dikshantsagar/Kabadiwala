package com.example.vidit.kabadiwala;

import android.view.View;
import android.widget.Button;

import com.google.gson.annotations.SerializedName;

public class Consignment
{
    int imageId;
    @SerializedName("name")
    String title;
    @SerializedName("price")
    String basePrice;
    @SerializedName("bid")
    String currentBid;
    @SerializedName("weight")
    String quantity;

    public Consignment(String title, String basePrice, String currentBid, String quantity)
    {
        this.imageId = R.drawable.scrap;
        this.title = title;
        this.basePrice = basePrice;
        this.currentBid = currentBid;
        this.quantity = quantity;
    }
}

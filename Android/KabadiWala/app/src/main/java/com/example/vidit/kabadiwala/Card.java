package com.example.vidit.kabadiwala;

import android.media.Image;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;

public class Card
{
    int imageId;
    @SerializedName("name")
    String title;
    String price;
    @SerializedName("weight")
    String quantity;
    String bid;

    public Card(int imageId, String title, String price, String quantity,String bid)
    {
        this.imageId=imageId;
        this.title=title;
        this.price=price;
        this.quantity=quantity;
        this.bid=bid;
    }
}

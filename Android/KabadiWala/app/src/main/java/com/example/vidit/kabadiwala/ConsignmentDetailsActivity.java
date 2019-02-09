package com.example.vidit.kabadiwala;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsignmentDetailsActivity extends AppCompatActivity {

    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consignment_details);
        Intent intent=getIntent();
        String title=intent.getStringExtra("Title");
        String currentBid=intent.getStringExtra("Current_Bid");
        String quantity=intent.getStringExtra("Quantity");
        position=Integer.parseInt(intent.getStringExtra("Position"));
        final TextView titleTextView,priceTextView,quantityTextView;
        Button bidNowButton;

        titleTextView=findViewById(R.id.detailsTitleTextView);
        quantityTextView=findViewById(R.id.quantityTextView);
        priceTextView=findViewById(R.id.currentBidTextView);
        bidNowButton=findViewById(R.id.bidNowButton);

        final ArrayList<Consignment> consignments=new ArrayList<>();

        titleTextView.setText(title);
        quantityTextView.setText(quantity);
        priceTextView.setText(currentBid);

        bidNowButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int bid=Integer.parseInt(priceTextView.getText().toString());
                bid=bid+5;
                priceTextView.setText(bid+"");
                BuyerMainActivity.availableConsignments.get(position).currentBid=String.valueOf(bid);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        BuyerMainActivity.adapter.notifyDataSetChanged();
    }
}

package com.example.vidit.kabadiwala;

import android.app.ActionBar;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyerMainActivity extends AppCompatActivity
{

    ListView availableConsignmentsListView;
    static ArrayList<Consignment> availableConsignments=new ArrayList<>();
    static ConsignmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_main);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayOptions(actionBar.getDisplayOptions()
                | ActionBar.DISPLAY_SHOW_CUSTOM);
        ImageView imageView1 = new ImageView(actionBar.getThemedContext());
        imageView1.setScaleType(ImageView.ScaleType.CENTER);
        imageView1.setImageResource(R.drawable.logo);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.RIGHT
                | Gravity.CENTER_VERTICAL);
        layoutParams.rightMargin = 40;
        availableConsignmentsListView=findViewById(R.id.consignmentListView);
        adapter=new ConsignmentAdapter(this, availableConsignments);
        availableConsignmentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Consignment consignment=availableConsignments.get(position);
                Intent intent=new Intent(BuyerMainActivity.this,ConsignmentDetailsActivity.class);
                intent.putExtra("Title",consignment.title);
                intent.putExtra("Base_Price",consignment.basePrice);
                intent.putExtra("Current_Bid",consignment.currentBid);
                intent.putExtra("Quantity",consignment.quantity);
                intent.putExtra("Position",String.valueOf(position));
                startActivity(intent);
            }
        });
        availableConsignmentsListView.setAdapter(adapter);
        Call<ConsignmentsResponse> call=ApiClient.getBuyerService().getConsignments();
        call.enqueue(new Callback<ConsignmentsResponse>()
        {
            @Override
            public void onResponse(Call<ConsignmentsResponse> call, Response<ConsignmentsResponse> response)
            {
                ConsignmentsResponse consignmentsResponse=response.body();
                ArrayList<Consignment> consignmentsList=consignmentsResponse.card;
                availableConsignments.clear();
                availableConsignments.addAll(consignmentsList);
                adapter.addAll(availableConsignments);
            }

            @Override
            public void onFailure(Call<ConsignmentsResponse> call, Throwable t) {

            }
        });
        adapter.notifyDataSetChanged();
    }
}

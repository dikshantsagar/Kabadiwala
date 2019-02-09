package com.example.vidit.kabadiwala;

import android.app.ActionBar;
import android.content.Intent;
import android.media.Image;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    static ArrayList<Card> feedArrayList=new ArrayList<>();
    static FeedAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        imageView1.setLayoutParams(layoutParams);
        actionBar.setCustomView(imageView1);
        ListView feedListView=findViewById(R.id.feedListView);
        adapter=new FeedAdapter(this,feedArrayList);
        BottomAppBar bottomAppBar=findViewById(R.id.bottomAppBar);
        FloatingActionButton addButton=findViewById(R.id.addButton);
        feedListView.setAdapter(adapter);
        bottomAppBar.inflateMenu(R.menu.main_menu);
        int imageId=R.drawable.scrap;
        ImageView imageView=findViewById(R.id.productImageView);
        //imageView.setImageResource(imageId);
        Call<FeedResponse> call=ApiClient.getSellerService().getFeed();
        call.enqueue(new Callback<FeedResponse>()
        {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response)
            {
                FeedResponse feedResponse=response.body();
                ArrayList<Card> cardsList=feedResponse.card;
                feedArrayList.clear();
                feedArrayList.addAll(cardsList);
                adapter.addAll(feedArrayList);
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {

            }
        });

        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(MainActivity.this,AddConsignmentActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.logoutButton:
                LoginActivity.firebaseAuth.getInstance().signOut();
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Call<FeedResponse> call=ApiClient.getSellerService().getFeed();
        call.enqueue(new Callback<FeedResponse>()
        {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response)
            {
                FeedResponse feedResponse=response.body();
                ArrayList<Card> cardsList=feedResponse.card;
                feedArrayList.clear();
                feedArrayList.addAll(cardsList);
                adapter.addAll(feedArrayList);
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {

            }
        });
    }
}

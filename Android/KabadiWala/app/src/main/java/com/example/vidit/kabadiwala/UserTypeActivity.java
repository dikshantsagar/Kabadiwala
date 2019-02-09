package com.example.vidit.kabadiwala;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class UserTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);
        Button sellerLoginButton=findViewById(R.id.sellerLoginButton);
        Button buyerLoginButton=findViewById(R.id.buyerLoginButton);

        sellerLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserTypeActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        buyerLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserTypeActivity.this,BuyerLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}

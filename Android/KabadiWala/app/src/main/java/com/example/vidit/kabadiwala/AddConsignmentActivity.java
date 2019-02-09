package com.example.vidit.kabadiwala;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;

import retrofit2.Callback;
import retrofit2.Response;

public class AddConsignmentActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_consignment);
        final EditText titleEditText,priceEditText,quantityEditText;
        Button imageUploadButton,uploadConsignmentButton;

        titleEditText=findViewById(R.id.addTitleEditText);
        priceEditText=findViewById(R.id.addBasePriceEditText);
        quantityEditText=findViewById(R.id.addQuantityEditText);

        imageUploadButton=findViewById(R.id.choosePhotoButton);
        uploadConsignmentButton=findViewById(R.id.postConsignmentButton);

        imageUploadButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        uploadConsignmentButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String title=titleEditText.getText().toString();
                String price=priceEditText.getText().toString();
                String quantity=quantityEditText.getText().toString();
                Card card1=new Card(0,title,price,quantity,0+"");
                MainActivity.feedArrayList.add(card1);
            }
        });
    }
}

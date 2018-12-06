package com.example.panaj.personalrestaurantguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RestaurantDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        Intent intetnt = getIntent();
        TextView idTxtView = findViewById(R.id.eTxtId);
        TextView nameTxtView = findViewById(R.id.eTxtName);
        TextView addressTxtView = findViewById(R.id.eTxtAddress);
        TextView phoneTxtView = findViewById(R.id.eTxtPhone);
        TextView tagTxtView = findViewById(R.id.eTxtTag);
        TextView rateTxtView = findViewById(R.id.eTxtRate);

        final String id = intetnt.getStringExtra("id");
        final String name = intetnt.getStringExtra("name");
        final String address = intetnt.getStringExtra("address");
        final String phone = intetnt.getStringExtra("phone");
        final String tag = intetnt.getStringExtra("tag");
        final String rate = intetnt.getStringExtra("rate");

        idTxtView.setText(id);
        nameTxtView.setText(name);
        addressTxtView.setText(address);
        phoneTxtView.setText(phone);
        tagTxtView.setText(tag);
        rateTxtView.setText(rate);
    }
}

package com.example.panaj.personalrestaurantguide;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.panaj.personalrestaurantguide.Helpers.RestaurantDbHelper;

public class RestaurantDetails extends AppCompatActivity {

    Button btnDelete ,btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        Intent intetnt = getIntent();
        final TextView nameTxtView = findViewById(R.id.eTxtName);
        final TextView addressTxtView = findViewById(R.id.eTxtAddress);
        final TextView phoneTxtView = findViewById(R.id.eTxtPhone);
        final TextView tagTxtView = findViewById(R.id.eTxtTag);
        final TextView desTxtView = findViewById(R.id.etxtDescription);
        final RatingBar bar = findViewById(R.id.ratingBar);

        final String id = intetnt.getStringExtra("id");
        final String name = intetnt.getStringExtra("name");
        final String address = intetnt.getStringExtra("address");
        final String phone = intetnt.getStringExtra("phone");
        final String tag = intetnt.getStringExtra("tag");
        final String des = intetnt.getStringExtra("des");
        final String rate = intetnt.getStringExtra("rate");


        nameTxtView.setText(name);
        addressTxtView.setText(address);
        phoneTxtView.setText(phone);
        tagTxtView.setText(tag);
        desTxtView.setText(des);
        bar.setRating(Float.parseFloat(rate));

        final RestaurantDbHelper dbHelper = new RestaurantDbHelper(this);
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dbHelper.delete(Integer.parseInt(id));
                    finish();
            }
        });

        btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String upName = ""+ nameTxtView.getText();
                String upAddress = ""+ addressTxtView.getText();
                String upPhone = ""+ phoneTxtView.getText();
                String upTag = ""+ tagTxtView.getText();
                String upDes = ""+ desTxtView.getText();
                int upRate = (int) bar.getRating();
                dbHelper.edit(Integer.parseInt(id),upName,upAddress,upPhone,upTag,upDes,upRate);
                finish();
            }
        });

        Button btnMaps = findViewById(R.id.btnViewLocation);
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),MapsActivity.class);
                startActivity(i);
            }
        });
    }
}

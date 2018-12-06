package com.example.panaj.personalrestaurantguide;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.panaj.personalrestaurantguide.Helpers.RestaurantDbHelper;

public class RestaurantDetails extends AppCompatActivity {

    Button btnDelete ,btnEdit;

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

        final RestaurantDbHelper dbHelper = new RestaurantDbHelper(this);
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dbHelper.delete(Integer.parseInt(id));
                    finish();
            }
        });
    }
}

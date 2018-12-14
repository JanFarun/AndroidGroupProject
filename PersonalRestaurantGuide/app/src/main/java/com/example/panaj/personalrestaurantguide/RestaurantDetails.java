package com.example.panaj.personalrestaurantguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.panaj.personalrestaurantguide.Helpers.RestaurantDbHelper;

public class RestaurantDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btnDelete ,btnEdit, btnShare;
    String[] tags = {"Vegetarian","Vegan","Organic","Thai","Chinese","European","Italian","Indian"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        Intent intetnt = getIntent();
        final TextView nameTxtView = findViewById(R.id.eTxtName);
        final TextView addressTxtView = findViewById(R.id.eTxtAddress);
        final TextView phoneTxtView = findViewById(R.id.eTxtPhone);
        final TextView tagTxtView = findViewById(R.id.txtTag);
        final Spinner spinner2 = findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tags);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(aa);


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
                String upTag = ""+ spinner2.getSelectedItem().toString();
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
        Button btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(android.content.Intent.ACTION_SEND);
                email.setType("plain/text");
                email.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{});
                email.putExtra(android.content.Intent.EXTRA_SUBJECT, "Restaurant Info");
                email.putExtra(android.content.Intent.EXTRA_TEXT,
                        "Restaurant Name:" + nameTxtView.getText().toString() +
                                "\nAddress: " + addressTxtView.getText().toString() +
                                "\nPhone #: " + phoneTxtView.getText().toString() +
                                //"\nType: " + tagTxtView.getText().toString() +
                                "\nType: " + spinner2.getSelectedItem().toString() +
                                "\nDescription: " + desTxtView.getText().toString() +
                                "\nRate: " + bar.getRating());
                startActivity(Intent.createChooser(email, "Send mail..."));
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

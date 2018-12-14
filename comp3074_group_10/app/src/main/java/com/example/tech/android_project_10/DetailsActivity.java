package com.example.tech.android_project_10;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    TextView tName,tAddress,tDescription,tTags,tPhone;
    RatingBar rbRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        final RestaurantDbHelper dbHelper = new RestaurantDbHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        Intent i = getIntent();
        Restaurant restaurant = (Restaurant) i.getSerializableExtra("restaurant");
            final int id = restaurant.getId();
            final String name = restaurant.getName();
            final String address = restaurant.getAddress();
            final String phone = restaurant.getPhone();
            final String description = restaurant.getDescription();
            final String tags = restaurant.getTags();
            final float rating = restaurant.getRating();

            tName = findViewById(R.id.textView_name);
            tAddress = findViewById(R.id.textView_address);
            tPhone = findViewById(R.id.textView_phone);
            tDescription = findViewById(R.id.textView_description);
            tTags = findViewById(R.id.textView_tags);
            rbRating = findViewById(R.id.ratingBar_details);
            tName.setText(name);
            tAddress.setText(address);
            tPhone.setText(phone);
            tDescription.setText(description);
            tTags.setText(tags);
            rbRating.setRating(rating);
        findViewById(R.id.btn_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                i.putExtra("address", address);
                startActivity(i);
            }
        });

        findViewById(R.id.updateBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), UpdateActivity.class);
                i.putExtra("id",id);
                i.putExtra("name", name);
                i.putExtra("address", address);
                i.putExtra("phone", phone);
                i.putExtra("description", description);
                i.putExtra("tags", tags);
                i.putExtra("rating", rating);
                startActivity(i);
                setResult(RESULT_OK);
            }
        });

        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deletePost(db, id);
                setResult(RESULT_OK);
                finish();
            }
        });

        findViewById(R.id.btn_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent email = new Intent(android.content.Intent.ACTION_SEND);
                        email.setType("plain/text");
                        email.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{});
                        email.putExtra(android.content.Intent.EXTRA_SUBJECT, "Restaurant Info");
                        email.putExtra(android.content.Intent.EXTRA_TEXT,
                                "Restaurant Name:" + tName.getText().toString() +
                                        "\nAddress: " + tAddress.getText().toString() +
                                        "\nPhone #: " + tPhone.getText().toString() +
                                        "\nType: " + tTags.getText().toString() +
                                        "\nDescription: " + tDescription.getText().toString() +
                                        "\nRate: " + rbRating.getRating());
                        startActivity(Intent.createChooser(email, "Send mail..."));
                    }
                });
    }

}

package com.example.tech.android_project_10;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.regex.Pattern;

public class UpdateActivity extends AppCompatActivity {
    EditText tName,tAddress,tPhone,tDescription,tTags;
    RatingBar rbRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        final RestaurantDbHelper dbHelper = new RestaurantDbHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        final Intent i = getIntent();
        final int id = i.getIntExtra("id",0);
        String name = i.getStringExtra("name");
        String address = i.getStringExtra("address");
        final String phone = i.getStringExtra("phone");
        String description = i.getStringExtra("description");
        String tags = i.getStringExtra("tags");
        float rating = i.getFloatExtra("rating",0);

        tName=findViewById(R.id.editText_updateName);
        tAddress=findViewById(R.id.editText_updateAddress);
        tPhone=findViewById(R.id.editText_updatePhone);
        tDescription=findViewById(R.id.editText_updateDescription);
        tTags=findViewById(R.id.editText_updateTags);
        rbRating=findViewById(R.id.ratingBar_updateRating);
        tName.setText(name);
        tAddress.setText(address);
        tPhone.setText(phone);
        tDescription.setText(description);
        tTags.setText(tags);
        rbRating.setRating(rating);

        findViewById(R.id.updateBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tName.getText().toString();
                String address = tAddress.getText().toString();

                String phone = "0";
                if(tPhone.getText().toString().isEmpty()) { }
                else {
                    phone = tPhone.getText().toString();
                    if (!Pattern.compile("[0-9]{1,10}").matcher(phone).matches()) {
                        Toast.makeText(UpdateActivity.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                String description = tDescription.getText().toString();
                String tags = tTags.getText().toString();
                float rating = rbRating.getRating();

                if (!name.equals("") || !address.equals("") || !tags.equals("")) {
                    dbHelper.updatePost(db, new Restaurant(name, address, phone, description, tags, rating), id);
                    Toast.makeText(UpdateActivity.this, "Data updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateActivity.this, "Data not updated", Toast.LENGTH_SHORT).show();
                }
                setResult(RESULT_OK);
                finish();
                Intent i = new Intent(v.getContext(),MainActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

package com.example.tech.android_project_10;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.regex.Pattern;

public class AddActivity extends AppCompatActivity {
    Button add_data, cancel;
    EditText add_name, add_address, add_description, add_tags, add_phone;
    RatingBar add_rating;
    String[] tags = {"Vegetarian","Vegan","Organic","Thai","Chinese","European","Italian","Indian"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final RestaurantDbHelper dbHelper = new RestaurantDbHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        cancel = findViewById(R.id.btn_cancel);
        add_data = findViewById(R.id.btn_add);
        add_name = findViewById(R.id.editText_addName);
        add_address = findViewById(R.id.editText_addAddress);
        add_phone = findViewById(R.id.editText_addPhone);
        add_description = findViewById(R.id.editText_addDescription);
        add_tags = findViewById(R.id.editText_addTags);
        add_rating = findViewById(R.id.ratingBar_add);
        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "0";
                if(add_phone.getText().toString().isEmpty()) { }
                else {
                    phone = add_phone.getText().toString();
                    if (!Pattern.compile("[0-9]{1,10}").matcher(phone).matches()) {
                        Toast.makeText(AddActivity.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                String name = add_name.getText().toString();
                String address = add_address.getText().toString();
                String description = add_description.getText().toString();
                String tags = add_tags.getText().toString();
                float rating = add_rating.getRating();
                if(!name.equals("") || !address.equals("") || !tags.equals("")){
                    dbHelper.addPost(db, new Restaurant(name, address, phone, description, tags, rating));
                    Toast.makeText(AddActivity.this, "Data added", Toast.LENGTH_SHORT).show();
                    add_name.setText("");
                    add_address.setText("");
                    add_phone.setText("");
                    add_description.setText("");
                    add_tags.setText("");
                    add_rating.setRating(0);
                } else {
                    Toast.makeText(AddActivity.this, "Data not added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}

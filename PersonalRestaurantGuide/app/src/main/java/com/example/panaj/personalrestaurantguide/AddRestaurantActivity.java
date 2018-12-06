package com.example.panaj.personalrestaurantguide;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.panaj.personalrestaurantguide.Helpers.Restaurant;
import com.example.panaj.personalrestaurantguide.Helpers.RestaurantDbHelper;

import java.util.ArrayList;
import java.util.List;

public class AddRestaurantActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button addRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        RestaurantDbHelper dbHelper = new RestaurantDbHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        addRestaurant = findViewById(R.id.btnAdd);
        addRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView nameTV = findViewById(R.id.eTxtName);
                TextView addressTV = findViewById(R.id.eTxtAddress);
                TextView phoneTV = findViewById(R.id.eTxtPhone);
                TextView tagTV = findViewById(R.id.eTxtTag);
                TextView desTV = findViewById(R.id.etxtDescription);
                RatingBar rb = findViewById(R.id.rgRate);

                String name = nameTV.getText().toString();
                String address =  addressTV.getText().toString();
                String phone = phoneTV.getText().toString();
                String tag = tagTV.getText().toString();
                String des = desTV.getText().toString();
                int rate = (int)rb.getRating();

                Spinner spinner = findViewById(R.id.spinner);
                spinner.setOnItemSelectedListener(this);
                List<String> list = new ArrayList<String>();
                list.add("Fast Food");
                list.add("Chinese");
                list.add("European");
                list.add("Italian");
                list.add("Indian");
                ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list);

                if (name.equals("")|| phone.equals("")||address.equals("")||tag.equals("")){
                    TextView error = findViewById(R.id.txtError);
                    error.setText("Missing input, add all your information.");
                }
                else {
                    RestaurantDbHelper.addPresenter(db, new Restaurant(name,address,phone,tag,des,rate));
                    finish();
                }
            }
        });

    }
}

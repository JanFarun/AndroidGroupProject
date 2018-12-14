package com.example.panaj.personalrestaurantguide;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import com.example.panaj.personalrestaurantguide.Helpers.Restaurant;
import com.example.panaj.personalrestaurantguide.Helpers.RestaurantDbHelper;

public class AddRestaurantActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button addRestaurant;
    String[] tags = {"Vegetarian","Vegan","Organic","Thai","Chinese","European","Italian","Indian"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        final Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tags);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        RestaurantDbHelper dbHelper = new RestaurantDbHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        addRestaurant = findViewById(R.id.btnAdd);
        addRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView nameTV = findViewById(R.id.eTxtName);
                TextView addressTV = findViewById(R.id.eTxtAddress);
                TextView phoneTV = findViewById(R.id.eTxtPhone);
                TextView desTV = findViewById(R.id.etxtDescription);
                RatingBar rb = findViewById(R.id.rgRate);

                String name = nameTV.getText().toString();
                String address =  addressTV.getText().toString();
                String phone = phoneTV.getText().toString();
                String tag = spinner.getSelectedItem().toString();
                String des = desTV.getText().toString();
                int rate = (int)rb.getRating();
                TextView error = findViewById(R.id.txtError);

                if (name.equals("")|| phone.equals("")||address.equals("")||tag.equals("")||des.equals("")){
                    error.setText("Missing input, add all your information.");
                }
                else if(!phone.matches("^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$")){
                    error.setText("Wrong phone number format!");
                }
                else if(rate==0){
                    error.setText("Please rate us!");
                }
                else {
                    RestaurantDbHelper.addPresenter(db, new Restaurant(name,address,phone,tag,des,rate));
                    finish();
                }
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

package com.example.panaj.personalrestaurantguide;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.panaj.personalrestaurantguide.Helpers.RestaurantDbHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAddRestaurant,btnAboutUs;
    RestaurantDbHelper dbHelper;
    ListView restaurantList;
    ArrayAdapter listAdapter;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAboutUs = findViewById(R.id.btnAboutUs);
        btnAddRestaurant = findViewById(R.id.btnAddRes);
        restaurantList = findViewById(R.id.listViewRestaurants);
        list = new ArrayList<>();
        listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        dbHelper = new RestaurantDbHelper(this);
        final RestaurantDbHelper dbHelper = new RestaurantDbHelper(this);
        Cursor data = dbHelper.getListContents();

        if (data.getCount() == 0){
            Toast.makeText(MainActivity.this,"EMPTY",Toast.LENGTH_SHORT).show();
        }else {
            while (data.moveToNext()){
                list.add(data.getString(1)+" "+data.getString(4));
                restaurantList.setAdapter(listAdapter);
            }
        }

        restaurantList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pos = restaurantList.getPositionForView(view);
                ArrayList list = new ArrayList();
                Cursor data = dbHelper.getDetailInfo(pos+1);
                while (data.moveToNext()){
                    list.add(data.getString(0));
                    list.add(data.getString(1));
                    list.add(data.getString(2));
                    list.add(data.getString(3));
                    list.add(data.getString(4));
                    list.add(data.getString(5));
                }
                String _id = list.get(0).toString();
                String name = list.get(1).toString();
                String address = list.get(2).toString();
                String phone = list.get(3).toString();
                String tag = list.get(4).toString();
                String rate = list.get(5).toString();
                Intent i = new Intent(view.getContext(),RestaurantDetails.class);
                i.putExtra("id",_id);
                i.putExtra("name",name);
                i.putExtra("address",address);
                i.putExtra("phone",phone);
                i.putExtra("tag",tag);
                i.putExtra("rate",rate);
                startActivity(i);

            }
        });

        btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),AboutUsActivity.class);
                startActivity(i);
            }
        });

        btnAddRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),AddRestaurantActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        Cursor data = dbHelper.getListContents();
        while (data.moveToNext()){
            list.add("Restaurant Name: "+data.getString(1)+"\nType of cuisine: "+data.getString(4));
            restaurantList.setAdapter(listAdapter);
        }
        listAdapter.notifyDataSetChanged();
        restaurantList.setAdapter(listAdapter);
    }


}

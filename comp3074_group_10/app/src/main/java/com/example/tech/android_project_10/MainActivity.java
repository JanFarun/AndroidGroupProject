package com.example.tech.android_project_10;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button refreshBtn, add_newBtn, aboutBtn;
    private ListView listView;
    private ArrayList<String> listItem;
    private ArrayAdapter<String> adapter;
    ArrayAdapter listAdapter;
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshBtn = findViewById(R.id.refreshBtn);
        add_newBtn = findViewById(R.id.add_newBtn);
        aboutBtn = findViewById(R.id.aboutBtn);

        final RestaurantDbHelper dbHelper = new RestaurantDbHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        listItem = new ArrayList<>();
        listView = findViewById(R.id.listView_main);
        long rows = DatabaseUtils.queryNumEntries(db, RestaurantContract.PostEntry.TABLE_NAME);
        if (rows > 0) {
            Cursor cursor = db.rawQuery(String.format("select * from %s", RestaurantContract.PostEntry.TABLE_NAME), null);
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    int index = cursor.getInt(0);
                    listItem.add(dbHelper.getPost(db, index).getName());
                    //+"\n"+dbHelper.getPost(db, index).getTags()

                    cursor.moveToNext();
                }
            }
            cursor.close();
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = listView.getItemAtPosition(position).toString();
                Intent i = new Intent(view.getContext(), DetailsActivity.class);
                i.putExtra("restaurant", dbHelper.getPost(db, ((TextView)view).getText().toString()));
                startActivityForResult(i, CODE);
            }
        });


        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),MainActivity.class);
                startActivity(i);
            }
        });
        add_newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),AddActivity.class);
                startActivity(i);
            }
        });
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),AboutActivity.class);
                startActivity(i);
            }
        });


        //***************************************SEARCH***************************************************
        list = new ArrayList<>();
        listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItem);
        TextView search = findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (MainActivity.this).adapter.getFilter().filter(s);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //******************************************************************************************

    }


    public static final int CODE = 1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CODE){
            if(resultCode == RESULT_OK)
                this.recreate();
        }
    }
}

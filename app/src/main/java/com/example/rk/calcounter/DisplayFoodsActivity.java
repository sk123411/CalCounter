package com.example.rk.calcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;

import data.CustomListViewAdapter;
import data.DatabaseHandler;
import model.Food;
import util.Util;

public class DisplayFoodsActivity extends AppCompatActivity {

    private DatabaseHandler db;
    private ArrayList<Food> foodsDB = new ArrayList<>();
    private TextView totalfood;
    private TextView totalCals;
    private RecyclerView recyclerView;
    private Food myFood;
    private CustomListViewAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_foods);

        totalfood = (TextView) findViewById(R.id.totalfooditems);
        totalCals = (TextView) findViewById(R.id.totalcalsitem);
        recyclerView = (RecyclerView) findViewById(R.id.recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        refreshDB();


    }

    private void refreshDB() {

        foodsDB.clear();
        db = new DatabaseHandler(getApplicationContext());

        ArrayList<Food> foodsDBitem = db.getFoods();

        if(foodsDBitem.size()>0){


            int CalsValue = db.totalCalories();
            int totalItems = db.getTotalItems();

            String formattedValue = Util.formatNumber(CalsValue);
            String formattedItems = Util.formatNumber(totalItems);

            totalCals.setText("Total Calories: " + formattedValue);
            totalfood.setText("Total Food Items: " + formattedItems);

            db.close();

            //Set UP Adapter
//
            foodAdapter = new CustomListViewAdapter(DisplayFoodsActivity.this,foodsDBitem);
            recyclerView.setAdapter(foodAdapter);
          //  foodAdapter.notifyDataSetChanged();


        }else {

            Toast.makeText(this,"You have not added any calories",Toast.LENGTH_LONG).show();
            finish();
        }




    }
}

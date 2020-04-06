package com.example.rk.calcounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import data.DatabaseHandler;
import model.Food;
import util.Util;

public class MainActivity extends AppCompatActivity {

    private EditText foodName, foodCalorie;
    private Button submit;
    private Button myCalories;
    private DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    db = new DatabaseHandler(MainActivity.this);
    foodName = (EditText) findViewById(R.id.foodEditText);
    foodCalorie = (EditText) findViewById(R.id.calorieEditText);
    submit = (Button) findViewById(R.id.submitB);
    myCalories = findViewById(R.id.myCaloriesButton);
    submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            savetoDB();
        }
    });


    myCalories.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this,DisplayFoodsActivity.class));
        }
    });



    }

    private void savetoDB() {

        Food food = new Food();

        String name = foodName.getText().toString().trim();
        String foodcals = foodCalorie.getText().toString().trim();

        int cals = Integer.parseInt(foodcals);

        if(name.equals("") || foodcals.equals("")){

            Toast.makeText(getApplicationContext(),"No empty fields allowed", Toast.LENGTH_LONG).show();

        }else {

            food.setFoodName(name);
            food.setCalories(cals);
            food.setRecordDate(String.valueOf(System.currentTimeMillis()));
            db.addFoodItem(food);
            db.close();

            // Clear the form
            foodName.setText("");
            foodCalorie.setText("");

            // Take User to Next Activity

            startActivity(new Intent(MainActivity.this, DisplayFoodsActivity.class));
        }





            }
}

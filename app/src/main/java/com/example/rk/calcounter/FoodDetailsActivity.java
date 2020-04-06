package com.example.rk.calcounter;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import data.DatabaseHandler;
import model.Food;

public class FoodDetailsActivity extends AppCompatActivity {

    private TextView foodName, Foodcol, dateText;
    private Button share,delete;
    private int foodid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
    foodName = (TextView) findViewById(R.id.foodiddets);
    Foodcol = (TextView) findViewById(R.id.calreal);
    dateText = (TextView) findViewById(R.id.detsDateText);
    share = (Button) findViewById(R.id.shareB);
    delete = (Button) findViewById(R.id.delete);

        final Food food = (Food) getIntent().getSerializableExtra("userObj");

        foodName.setText(food.getFoodName());
        Foodcol.setText(String.valueOf(food.getCalories()));
        dateText.setText(food.getRecordDate());

        foodid = food.getFoodid();

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharecals();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FoodDetailsActivity.this);
                builder.setTitle("Delete");
                builder.setMessage("Are you sure you want to delete item");
                builder.setNegativeButton("No",null);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                        db.deleteFoodItem(foodid);


                    Toast.makeText(getApplicationContext(),"Item Deleted",Toast.LENGTH_LONG).show();

                  // startActivity(new Intent(FoodDetailsActivity.this,DisplayFoodsActivity.class));

                        Intent intent = new Intent(FoodDetailsActivity.this, DisplayFoodsActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                        FoodDetailsActivity.this.finish();


                    }

                });
            builder.show();


            }


        });



    }
    public void sharecals(){

        StringBuilder datestring = new StringBuilder();

        String name = foodName.getText().toString();
        String cal = Foodcol.getText().toString();
        String date = dateText.getText().toString();

        datestring.append("FOOD: " + name + "\n");
        datestring.append("Calorie " + cal + "\n");
        datestring.append("Eaten on " + date);


        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_SUBJECT,"My colorie intake");
        i.putExtra(Intent.EXTRA_EMAIL,"reciptant@example.com");
        i.putExtra(Intent.EXTRA_TEXT,datestring.toString());

    try{
        startActivity(Intent.createChooser(i,"Send Email...."));

    }catch (ActivityNotFoundException e){

        Toast.makeText(getApplicationContext(),"Please install email clients before sending message",
                Toast.LENGTH_LONG).show();
    }


    }





}

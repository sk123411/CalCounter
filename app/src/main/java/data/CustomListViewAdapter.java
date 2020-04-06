package data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rk.calcounter.FoodDetailsActivity;
import com.example.rk.calcounter.R;

import java.util.ArrayList;
import java.util.List;

import model.Food;

/**
 * Created by RK on 28-03-2018.
 */

public class CustomListViewAdapter extends RecyclerView.Adapter<CustomListViewAdapter.MyViewHolder> {
    private Context context;
    private List<Food> foodList;



    public CustomListViewAdapter(Context context,List<Food> foodList){
        this.context = context;
        this.foodList = foodList;


    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView foodName;
        TextView foodCalories;
        TextView foodDate;

        MyViewHolder(View itemView) {
            super(itemView);


            foodName = (TextView) itemView.findViewById(R.id.fooditem);
            foodDate = (TextView) itemView.findViewById(R.id.dateitem);
            foodCalories = (TextView) itemView.findViewById(R.id.calories);



        }
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_row,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        final Food food = foodList.get(position);
        holder.foodName.setText(food.getFoodName());
        holder.foodCalories.setText(String.valueOf(food.getCalories()));
        holder.foodDate.setText(food.getRecordDate());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, FoodDetailsActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("userObj", food);
                i.putExtras(mBundle);

                context.startActivity(i);
            }
        });





    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }






    }



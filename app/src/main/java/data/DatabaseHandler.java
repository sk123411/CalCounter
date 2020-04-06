package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.util.Log;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Food;

/**
 * Created by RK on 28-03-2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private ArrayList<Food> foodList = new ArrayList<>();

    public DatabaseHandler(Context context) {
        super(context,Constants.DATABASE_NAME,null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create Table

        String CREATE_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY, " + Constants.FOOD_NAME +
                " TEXT, " + Constants.FOOD_NAME_CALORIES + " INT, " + Constants.FOOD_DATE + " LONG);";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);

        //create a new one

        onCreate(db);
    }

    //Get Total Items Saved

    public int getTotalItems() {
        int totalItems = 0;

        String query = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

      totalItems = cursor.getCount();
      cursor.close();

       return totalItems;
    }

    // Get Total Calories Consumed
    public int totalCalories(){
        int cals = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT SUM( " + Constants.FOOD_NAME_CALORIES  + " ) " +
                "FROM " + Constants.TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){

            cals = cursor.getInt(0);


        }

        cursor.close();
        db.close();


    return cals;
    }

    public void deleteFoodItem(int id){

        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.KEY_ID +" = ?" ,
                new String[]{String.valueOf(id)});

        db.close();

    }

    // add food to database
    public void addFoodItem(Food food){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.FOOD_NAME, food.getFoodName());
        values.put(Constants.FOOD_NAME_CALORIES, food.getCalories());
        values.put(Constants.FOOD_DATE, System.currentTimeMillis());

        db.insert(Constants.TABLE_NAME,null,values);

        Log.v("Added Food item", "Yesss!!");

        db.close();

    }

    public ArrayList<Food> getFoods(){
        foodList.clear();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Constants.TABLE_NAME, new String[]{Constants.KEY_ID, Constants.FOOD_NAME
        ,Constants.FOOD_NAME_CALORIES,Constants.FOOD_DATE} ,null,null,null,null,
                Constants.FOOD_DATE + " DESC");

        if(cursor.moveToFirst()){
            do {
                Food food = new Food();
                food.setFoodName(cursor.getString(cursor.getColumnIndex(Constants.FOOD_NAME)));
                food.setCalories(cursor.getInt(cursor.getColumnIndex(Constants.FOOD_NAME_CALORIES)));
                food.setFoodid(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID)));

                DateFormat dateFormat = DateFormat.getDateInstance();
                String date = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.FOOD_DATE))));

                food.setRecordDate(date);
                foodList.add(food);

            }while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

   return foodList;

    }




}

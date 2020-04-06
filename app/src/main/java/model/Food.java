package model;

import java.io.Serializable;

/**
 * Created by RK on 28-03-2018.
 */

public class Food implements Serializable {
    public static final long serialVersionUID = 10L;
    private String foodName;
    private int calories;
    private int foodid;
    private String recordDate;

    public Food(String foodName, int calories, int foodid, String recordDate) {
        this.foodName = foodName;
        this.calories = calories;
        this.foodid = foodid;
        this.recordDate = recordDate;
    }

    public Food(){

    }




    public int getFoodid() {
        return foodid;
    }

    public void setFoodid(int foodid) {
        this.foodid = foodid;
    }





    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }



}

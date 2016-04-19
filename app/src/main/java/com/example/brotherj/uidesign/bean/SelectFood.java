package com.example.brotherj.uidesign.bean;

/**
 * Created by User on 19/4/2016.
 */
public class SelectFood {
    Food food;
    int qty;
    public SelectFood(Food food,int qty){
        this.food = food;
        this.qty = qty;
    }

    public Food getFood() {
        return food;
    }

    public int getQty() {
        return qty;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}

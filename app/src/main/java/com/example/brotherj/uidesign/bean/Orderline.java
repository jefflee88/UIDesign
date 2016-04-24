package com.example.brotherj.uidesign.bean;

/**
 * Created by Dicky on 23/4/2016.
 */
public class Orderline {

    int orderNumber, quanitity, item_total;
    String foodId, pick_up, status, restaurantId;

    public Orderline (int orderNumber, String foodId, int quanitity, String pick_up, String status, int item_total, String restaurantId) {
        this.orderNumber = orderNumber;
        this.foodId = foodId;
        this.quanitity = quanitity;
        this.pick_up = pick_up;
        this.status = status;
        this.item_total = item_total;
        this.restaurantId = restaurantId;
    }

    public String getPick_up() {
        return pick_up;
    }

    public int getItem_total() {
        return item_total;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getQuanitity() {
        return quanitity;
    }

    public String getFoodId() {
        return foodId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getStatus() {
        return status;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public void setItem_total(int item_total) {
        this.item_total = item_total;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setPick_up(String pick_up) {
        this.pick_up = pick_up;
    }

    public void setQuanitity(int quanitity) {
        this.quanitity = quanitity;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.example.brotherj.uidesign.bean;

/**
 * Created by User on 10/4/2016.
 */
public class Order {
    int number;
    String date_time,all_pick_up,received_by_customer,Customerid,Driverid;
    public Order(int number,String date_time,String all_pick_up,String received_by_customer,String Customerid,String Driverid){
        this.number = number;
        this.date_time = date_time;
        this.all_pick_up = all_pick_up;
        this.received_by_customer = received_by_customer;
        this.Customerid = Customerid;
        this.Driverid = Driverid;
    }

    public int getNumber() {
        return number;
    }

    public String getAll_pick_up() {
        return all_pick_up;
    }

    public String getCustomerid() {
        return Customerid;
    }

    public String getDate_time() {
        return date_time;
    }

    public String getDriverid() {
        return Driverid;
    }

    public String getReceived_by_customer() {
        return received_by_customer;
    }

    public void setAll_pick_up(String all_pick_up) {
        this.all_pick_up = all_pick_up;
    }

    public void setCustomerid(String customerid) {
        Customerid = customerid;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public void setDriverid(String driverid) {
        Driverid = driverid;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setReceived_by_customer(String received_by_customer) {
        this.received_by_customer = received_by_customer;
    }
}

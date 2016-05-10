package com.example.brotherj.uidesign.Data;

import com.example.brotherj.uidesign.bean.Customer;
import com.example.brotherj.uidesign.bean.Driver;
import com.example.brotherj.uidesign.bean.Food;
import com.example.brotherj.uidesign.bean.Order;
import com.example.brotherj.uidesign.bean.Orderline;
import com.example.brotherj.uidesign.bean.Restaurant;
import com.example.brotherj.uidesign.bean.SelectFood;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by User on 29/3/2016.
 */
//杰屎
public class SaveData {
    public static boolean isFood;
    public static Customer customer;
    public static Driver driver;
    public static Restaurant restaurant;
    public static Food resSearchFood;
    public static ArrayList<Restaurant> cusSearchRestaurant;
    public static Restaurant cusChooseRestaurant;
    public static Food cusChooseFood;
    public static ArrayList<Food> cusSearchFood;
    public static ArrayList<SelectFood> cusChooseFoods = new ArrayList<SelectFood>();
    public static ArrayList<Orderline> resOrderline =  new ArrayList<Orderline>();
    public static Order resOrder;
    public static String AllQty;
    public static String AllPrice;
    public static Order driveChooseOrder;
    public static Order driveChooseMyOrder;
    public static String location;
    public static String tempCheck;
}

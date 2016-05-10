package com.example.brotherj.uidesign.JsonClass;

import android.os.Build;
import android.os.StrictMode;
import android.util.Log;


import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.bean.Customer;
import com.example.brotherj.uidesign.bean.Driver;
import com.example.brotherj.uidesign.bean.Food;
import com.example.brotherj.uidesign.bean.Order;
import com.example.brotherj.uidesign.bean.Orderline;
import com.example.brotherj.uidesign.bean.Restaurant;
import com.example.brotherj.uidesign.bean.SelectFood;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetJson {
    public static void changeVersion() {
        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build());
        }
    }

    public static void getUserDetail(String userId, String userPw, String type) {
        try {
            String url = "http://10.0.2.2/fyp_connect/get_user_details.php?id=" + userId + "&password=" + userPw;
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            Log.d("user12341  ",json.toString() );
            try {
                if (type.equals("customer")) {
                    for (int i = 0; i < json.getJSONArray("user").length(); i++) {
                        JSONObject jsonObj = json.getJSONArray("user").getJSONObject(i);
                        String id = jsonObj.getString("id");
                        String name = jsonObj.getString("name");
                        String address = jsonObj.getString("address");
                        String email = jsonObj.getString("email");
                        String telNum = jsonObj.getString("telNum");
                        String payment = jsonObj.getString("payment");
                        String credit_card_number = jsonObj.getString("credit_card_number");
                        String credit_card_security_code = jsonObj.getString("credit_card_security_code");

                        String Userid = jsonObj.getString("Userid");
                        Log.d("user12341  ",json.toString() );
                        SaveData.customer = new Customer(id, name, address, email, telNum,payment,credit_card_number,credit_card_security_code, Userid);
                    }
                }
                if (type.equals("restaurant")) {
                    for (int i = 0; i < json.getJSONArray("order").length(); i++) {
                        JSONObject jsonObj = json.getJSONArray("order").getJSONObject(i);
                        String id = jsonObj.getString("id");
                        String name = jsonObj.getString("name");
                        String address = jsonObj.getString("address");
                        String type1 = jsonObj.getString("type");
                        String telNum = jsonObj.getString("telNum");
                        String Userid = jsonObj.getString("Userid");
                        SaveData.restaurant = new Restaurant(id, name, address, type1, telNum, Userid);
                    }
                }
                if (type.equals("driver")) {
                    for (int i = 0; i < json.getJSONArray("order").length(); i++) {
                        JSONObject jsonObj = json.getJSONArray("order").getJSONObject(i);
                        String id = jsonObj.getString("id");
                        String name = jsonObj.getString("name");
                        String telNum = jsonObj.getString("telNum");
                        String Userid = jsonObj.getString("Userid");
                        SaveData.driver = new Driver(id, name, telNum, Userid);
                    }

                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static boolean CheckUser(String userId, String userPw) {
        boolean check = false;
        try {
            String url = "http://10.0.2.2/fyp_connect/get_user_details.php?id=" + userId + "&password=" + userPw;
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            try {
                if (json.getInt("success") == 1) {
                    check = true;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return check;
    }

    public static String CheckUserType(String userId, String userPw) {
        String type = "";
        try {
            String url = "http://10.0.2.2/fyp_connect/get_user_details.php?id=" + userId + "&password=" + userPw;
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            try {
                if (json.getString("type").equals("customer"))
                    type = "customer";
                if (json.getString("type").equals("driver"))
                    type = "driver";
                if (json.getString("type").equals("restaurant"))
                    type = "restaurant";
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return type;
    }

    public static ArrayList<Food> getRestFoodDetail(String userId) {
        ArrayList<Food> food = new ArrayList<Food>();
        try {
            String url = "http://10.0.2.2/fyp_connect/get_restaurant_food.php?userid=" + userId;
            url = url.replaceAll(" ","%20");
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            try {
                for (int i = 0; i < json.getJSONArray("food").length(); i++) {
                    JSONObject jsonObj = json.getJSONArray("food").getJSONObject(i);
                    String foodId = jsonObj.getString("id");
                    String foodName = jsonObj.getString("name");
                    String foodType = jsonObj.getString("type");
                    String foodPrice = jsonObj.getString("price");
                    String foodImage = jsonObj.getString("image");
                    String foodRestId = jsonObj.getString("Restaurantid");
                    food.add(new Food(foodId, foodName, foodType, foodPrice, foodImage, foodRestId));
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return food;
    }

    public static ArrayList<Restaurant> searchRestaurant(String keyword, String type) {
        ArrayList<Restaurant> obj = new ArrayList<Restaurant>();
        try {
            String url = "http://10.0.2.2/fyp_connect/search_details.php?type=" + type + "&keyword=" + keyword;
            url = url.replaceAll(" ","%20");
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            try {
                if(type.equals("restaurant"))
                    for (int i = 0; i < json.getJSONArray("restaurant").length(); i++) {
                        JSONObject jsonObj = json.getJSONArray("restaurant").getJSONObject(i);
                        String restaurantId = jsonObj.getString("id");
                        String restaurantName = jsonObj.getString("name");
                        String restaurantAddress = jsonObj.getString("address");
                        String restaurantType = jsonObj.getString("type");
                        String restaurantTelNum = jsonObj.getString("telNum");
                        String restaurantUserid = jsonObj.getString("Userid");
                        obj.add(new Restaurant(restaurantId, restaurantName, restaurantAddress, restaurantType, restaurantTelNum, restaurantUserid));
                    }

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return obj;
    }

    public static ArrayList<Food> searchFood(String keyword, String type) {
        ArrayList<Food> obj = new ArrayList<Food>();
        try {
            String url = "http://10.0.2.2/fyp_connect/search_details.php?type=" + type + "&keyword=" + keyword;
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            try {
                    for (int i = 0; i < json.getJSONArray("food").length(); i++) {
                        JSONObject jsonObj = json.getJSONArray("food").getJSONObject(i);
                        String foodId = jsonObj.getString("id");
                        String foodName = jsonObj.getString("name");
                        String foodType = jsonObj.getString("type");
                        String foodPrice = jsonObj.getString("price");
                        String foodImage = jsonObj.getString("image");
                        String foodRestId = jsonObj.getString("Restaurantid");
                        obj.add(new Food(foodId, foodName, foodType, foodPrice, foodImage, foodRestId));
                    }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return obj;
    }
    public static void modifyFood(Food food) {
        try {
            String url = "http://10.0.2.2/fyp_connect/update_food.php?id=" + food.getId() + "&name=" + food.getName() + "&type=" + food.getType() + "&price=" + food.getPrice() + "&image=" + food.getImage();
            url = url.replaceAll(" ","%20");
            Log.d("Url   :::::::", url);
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static void addFood(Food food) {
        try {
            String url = "http://10.0.2.2/fyp_connect/create_food.php?name=" + food.getName() + "&type=" + food.getType() + "&price=" + food.getPrice() + "&image=" + food.getImage()+"&Restaurantid="+SaveData.restaurant.getId();
            url = url.replaceAll(" ","%20");
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static void creatOrder(ArrayList<SelectFood> food,int total){
        try {
            String url = "http://10.0.2.2/fyp_connect/create_order.php?customerid="+SaveData.customer.getId()+"&order_total="+total;
            url = url.replaceAll(" ","%20");
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            int number = 0;
            try {
                number = json.getInt("number");
                for(int i = 0;i<food.size();i++)
                creatOrderLine(number,food.get(i).getQty(),food.get(i).getFood());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    public static void creatOrderLine(int number,int qty,Food food) {
        try {
            String url = "http://10.0.2.2/fyp_connect/create_orderline.php?ordernumber="+number+"&foodid="+food.getId()+"&quanitity="+qty+"&item_total="+(Integer.parseInt(food.getPrice())*qty)+"&Restaurantid="+food.getRestaurantid();
            url = url.replaceAll(" ","%20");
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }


    public static ArrayList<Orderline> restaurantGetOrderline(){
        ArrayList<Orderline> obj = new ArrayList<Orderline>();
        try {
            String url = "http://10.0.2.2/fyp_connect/restaurant_get_orderline.php?restaurantid="+SaveData.restaurant.getId();
            url = url.replaceAll(" ","%20");
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            try {
                for (int i = 0; i < json.getJSONArray("orderline").length(); i++) {
                    JSONObject jsonObj = json.getJSONArray("orderline").getJSONObject(i);
                    int orderNumber = jsonObj.getInt("Ordernumber");
                    String foodId = jsonObj.getString("Foodid");
                    String pick_up = jsonObj.getString("pick_up");
                    String status = jsonObj.getString("status");
                    int quanitity = jsonObj.getInt("quanitity");
                    int item_total = jsonObj.getInt("item_total");
                    String restaurantId = jsonObj.getString("Restaurantid");
                    obj.add(new Orderline(orderNumber,foodId,quanitity,pick_up,status,item_total,restaurantId)
                    );
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return obj;
    }

    public static ArrayList<Order> restaurantGetOrder(ArrayList<Orderline> odl){
        ArrayList<Order> obj = new ArrayList<Order>();
        try {
            String location = "SELECT * FROM `order` WHERE ";
            for(int i = 0;i<odl.size(); i++){
                if(odl.size()-1 != i)
                    location += "number=" + odl.get(i).getOrderNumber() + " OR ";
                else if(odl.size()-1 == i)
                    location += "number=" + odl.get(i).getOrderNumber();
            }
            Log.d("location ::::", location);
            String url = "http://10.0.2.2/fyp_connect/restaurant_get_order.php?sql="+location;
            url = url.replaceAll(" ","%20");
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            Log.d("reply ::::", reply);
            JSONObject json = new JSONObject(reply);
            try {
                for (int i = 0; i < json.getJSONArray("order").length(); i++) {
                    JSONObject jsonObj = json.getJSONArray("order").getJSONObject(i);
                    int number = jsonObj.getInt("number");
                    String date_time = jsonObj.getString("date_time");
                    String all_pick_up = jsonObj.getString("all_pick_up");
                    String received_by_customer = jsonObj.getString("received_by_customer");
                    int order_total = jsonObj.getInt("order_total");
                    String Customerid = jsonObj.getString("Customerid");
                    String Driverid = jsonObj.getString("Driverid");
                    obj.add(new Order(number,date_time,all_pick_up,received_by_customer,order_total,Customerid,Driverid));
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return obj;

    }

    public static ArrayList<Order> restaurantGetTotalOrder(ArrayList<Orderline> odl){
        ArrayList<Order> obj = new ArrayList<Order>();
        try {
            String location = "SELECT * FROM `order` WHERE ";
            for(int i = 0;i<odl.size(); i++){
                if(odl.size()-1 != i)
                    location += "number=" + odl.get(i).getOrderNumber() + " OR ";
                else if(odl.size()-1 == i)
                    location += "number=" + odl.get(i).getOrderNumber();
            }
            location += " ORDER BY order_total";
            Log.d("location ::::", location);
            String url = "http://10.0.2.2/fyp_connect/restaurant_get_order.php?sql="+location;
            url = url.replaceAll(" ","%20");
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            Log.d("reply ::::", reply);
            JSONObject json = new JSONObject(reply);
            try {
                for (int i = 0; i < json.getJSONArray("order").length(); i++) {
                    JSONObject jsonObj = json.getJSONArray("order").getJSONObject(i);
                    int number = jsonObj.getInt("number");
                    String date_time = jsonObj.getString("date_time");
                    String all_pick_up = jsonObj.getString("all_pick_up");
                    String received_by_customer = jsonObj.getString("received_by_customer");
                    int order_total = jsonObj.getInt("order_total");
                    String Customerid = jsonObj.getString("Customerid");
                    String Driverid = jsonObj.getString("Driverid");
                    obj.add(new Order(number,date_time,all_pick_up,received_by_customer,order_total,Customerid,Driverid));
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return obj;

    }

    public static ArrayList<Order> restaurantGetTimeOrder(ArrayList<Orderline> odl){
        ArrayList<Order> obj = new ArrayList<Order>();
        try {
            String location = "SELECT * FROM `order` WHERE ";
            for(int i = 0;i<odl.size(); i++){
                if(odl.size()-1 != i)
                    location += "number=" + odl.get(i).getOrderNumber() + " OR ";
                else if(odl.size()-1 == i)
                    location += "number=" + odl.get(i).getOrderNumber();
            }
            location += " ORDER BY date_time";
            Log.d("location ::::", location);
            String url = "http://10.0.2.2/fyp_connect/restaurant_get_order.php?sql="+location;
            url = url.replaceAll(" ","%20");
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            Log.d("reply ::::", reply);
            JSONObject json = new JSONObject(reply);
            try {
                for (int i = 0; i < json.getJSONArray("order").length(); i++) {
                    JSONObject jsonObj = json.getJSONArray("order").getJSONObject(i);
                    int number = jsonObj.getInt("number");
                    String date_time = jsonObj.getString("date_time");
                    String all_pick_up = jsonObj.getString("all_pick_up");
                    String received_by_customer = jsonObj.getString("received_by_customer");
                    int order_total = jsonObj.getInt("order_total");
                    String Customerid = jsonObj.getString("Customerid");
                    String Driverid = jsonObj.getString("Driverid");
                    obj.add(new Order(number,date_time,all_pick_up,received_by_customer,order_total,Customerid,Driverid));
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return obj;

    }

    public static ArrayList<Orderline> restaurantGetOrderlineForOrder(Order order){
        ArrayList<Orderline> obj = new ArrayList<Orderline>();
        try {
            String url = "http://10.0.2.2/fyp_connect/restaurant_ordernumber_get_orderline.php?number="+order.getNumber()+"&restaurantid="+SaveData.restaurant.getId();
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            try {
                for (int i = 0; i < json.getJSONArray("orderline").length(); i++) {
                    JSONObject jsonObj = json.getJSONArray("orderline").getJSONObject(i);
                    int orderNumber = jsonObj.getInt("Ordernumber");
                    String foodId = jsonObj.getString("Foodid");
                    String pick_up = jsonObj.getString("pick_up");
                    String status = jsonObj.getString("status");
                    int quanitity = jsonObj.getInt("quanitity");
                    int item_total = jsonObj.getInt("item_total");
                    String restaurantId = jsonObj.getString("Restaurantid");
                    obj.add(new Orderline(orderNumber,foodId,quanitity,pick_up,status,item_total,restaurantId)
                    );
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return obj;
    }

    public static String getFoodType(String food) {
        String str = "";
        try {
            String url = "http://10.0.2.2/fyp_connect/id_get_food_type.php?foodid=" + food;
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            try {
                    str = json.getString("type");
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return str;
    }
    public static String getFoodName(String food) {
        String str = "";
        try {
            String url = "http://10.0.2.2/fyp_connect/id_get_food_name.php?foodid=" + food;
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            try {
                str = json.getString("name");
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return str;
    }

    public static String getCusLocation(String id) {
        String str = "";
        try {
            String url = "http://10.0.2.2/fyp_connect/id_get_customer_address.php?customerid=" + id;
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            try {
                str = json.getString("address");
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return str;
    }
    public static void setCompleted(int ordernum) {
        try {
            String url = "http://10.0.2.2/fyp_connect/restaurant_update_order_status.php?ordernumber="+ordernum+"&restaurantid="+SaveData.restaurant.getId();
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    public static void setCustomerPayment(String payment,String Credit_card_number,String Credit_card_security_code){
        try {
        String url = "http://10.0.2.2/fyp_connect/update_customer.php?id="+SaveData.customer.getId()+"&name="+SaveData.customer.getName()+"&address="+SaveData.customer.getAddress()+"&email="+SaveData.customer.getEmail()+"&telNum="+SaveData.customer.getTelNum()+"&payment="+payment+"&cardnumber="+Credit_card_number+"&securitycode="+Credit_card_security_code;
        url = url.replaceAll(" ","%20");
        URL urlObj = new URL(url);
        HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
        client.setDoInput(true);
        client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        client.setRequestMethod("GET");
        client.connect();
        InputStream input = client.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        String reply = result.toString();
        JSONObject json = new JSONObject(reply);
    } catch (Exception e1) {
        e1.printStackTrace();
    }


    }
    public static ArrayList<Order> driveGetOrder(){
        ArrayList<Order> obj = new ArrayList<Order>();
        try {
            String url = "http://10.0.2.2/fyp_connect/driver_get_order.php";
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            try {
                for (int i = 0; i < json.getJSONArray("order").length(); i++) {
                    JSONObject jsonObj = json.getJSONArray("order").getJSONObject(i);
                    int number = jsonObj.getInt("number");
                    String date_time = jsonObj.getString("date_time");
                    String all_pick_up = jsonObj.getString("all_pick_up");
                    String received_by_customer = jsonObj.getString("received_by_customer");
                    int order_total = jsonObj.getInt("order_total");
                    String Customerid = jsonObj.getString("Customerid");
                    String Driverid = jsonObj.getString("Driverid");
                    obj.add(new Order(number,date_time,all_pick_up,received_by_customer,order_total,Customerid,Driverid));
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return obj;
    }

    public static ArrayList<Order> driveGetTotalOrder(){
        ArrayList<Order> obj = new ArrayList<Order>();
        try {
            String url = "http://10.0.2.2/fyp_connect/driver_get_order_qty.php";
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            try {
                for (int i = 0; i < json.getJSONArray("order").length(); i++) {
                    JSONObject jsonObj = json.getJSONArray("order").getJSONObject(i);
                    int number = jsonObj.getInt("number");
                    String date_time = jsonObj.getString("date_time");
                    String all_pick_up = jsonObj.getString("all_pick_up");
                    String received_by_customer = jsonObj.getString("received_by_customer");
                    int order_total = jsonObj.getInt("order_total");
                    String Customerid = jsonObj.getString("Customerid");
                    String Driverid = jsonObj.getString("Driverid");
                    obj.add(new Order(number,date_time,all_pick_up,received_by_customer,order_total,Customerid,Driverid));
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return obj;
    }

    public static ArrayList<Order> driveGetTimeOrder(){
        ArrayList<Order> obj = new ArrayList<Order>();
        try {
            String url = "http://10.0.2.2/fyp_connect/driver_get_order_time.php";
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            try {
                for (int i = 0; i < json.getJSONArray("order").length(); i++) {
                    JSONObject jsonObj = json.getJSONArray("order").getJSONObject(i);
                    int number = jsonObj.getInt("number");
                    String date_time = jsonObj.getString("date_time");
                    String all_pick_up = jsonObj.getString("all_pick_up");
                    String received_by_customer = jsonObj.getString("received_by_customer");
                    int order_total = jsonObj.getInt("order_total");
                    String Customerid = jsonObj.getString("Customerid");
                    String Driverid = jsonObj.getString("Driverid");
                    obj.add(new Order(number,date_time,all_pick_up,received_by_customer,order_total,Customerid,Driverid));
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return obj;
    }

    public static void handle(){
        try {
            String url = "http://10.0.2.2/fyp_connect/driver_handle_order.php?driverid="+SaveData.driver.getId()+"&number="+SaveData.driveChooseOrder.getNumber();
            url = url.replaceAll(" ","%20");
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    public static ArrayList<Order> cusGetOrder(){
        ArrayList<Order> obj = new ArrayList<Order>();
        try {
            String url = "http://10.0.2.2/fyp_connect/customer_get_order2.php?id="+SaveData.customer.getId();
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            try {
                for (int i = 0; i < json.getJSONArray("order").length(); i++) {
                    JSONObject jsonObj = json.getJSONArray("order").getJSONObject(i);
                    int number = jsonObj.getInt("number");
                    String date_time = jsonObj.getString("date_time");
                    String all_pick_up = jsonObj.getString("all_pick_up");
                    String received_by_customer = jsonObj.getString("received_by_customer");
                    int order_total = jsonObj.getInt("order_total");
                    String Customerid = jsonObj.getString("Customerid");
                    String Driverid = jsonObj.getString("Driverid");
                    obj.add(new Order(number,date_time,all_pick_up,received_by_customer,order_total,Customerid,Driverid));
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return obj;
    }
    public static String orderGetOrderlineTotal(int id){
        String obj = "";
        try {
            String url = "http://10.0.2.2/fyp_connect/get_order_count.php?number="+id;
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            obj = Integer.toString(json.getInt("number"));


        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return obj;
    }
    public static ArrayList<Order> driveGetMyOrder(){
        ArrayList<Order> obj = new ArrayList<Order>();
        try {
            String url = "http://10.0.2.2/fyp_connect/driver_get_order2.php?id="+SaveData.driver.getId();
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            try {
                for (int i = 0; i < json.getJSONArray("order").length(); i++) {
                    JSONObject jsonObj = json.getJSONArray("order").getJSONObject(i);
                    int number = jsonObj.getInt("number");
                    String date_time = jsonObj.getString("date_time");
                    String all_pick_up = jsonObj.getString("all_pick_up");
                    String received_by_customer = jsonObj.getString("received_by_customer");
                    int order_total = jsonObj.getInt("order_total");
                    String Customerid = jsonObj.getString("Customerid");
                    String Driverid = jsonObj.getString("Driverid");
                    obj.add(new Order(number,date_time,all_pick_up,received_by_customer,order_total,Customerid,Driverid));
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return obj;
    }

    public static ArrayList<Order> driveGetTotalMyOrder(){
        ArrayList<Order> obj = new ArrayList<Order>();
        try {
            String url = "http://10.0.2.2/fyp_connect/driver_get_order2_qty.php?id="+SaveData.driver.getId();
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            try {
                for (int i = 0; i < json.getJSONArray("order").length(); i++) {
                    JSONObject jsonObj = json.getJSONArray("order").getJSONObject(i);
                    int number = jsonObj.getInt("number");
                    String date_time = jsonObj.getString("date_time");
                    String all_pick_up = jsonObj.getString("all_pick_up");
                    String received_by_customer = jsonObj.getString("received_by_customer");
                    int order_total = jsonObj.getInt("order_total");
                    String Customerid = jsonObj.getString("Customerid");
                    String Driverid = jsonObj.getString("Driverid");
                    obj.add(new Order(number,date_time,all_pick_up,received_by_customer,order_total,Customerid,Driverid));
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return obj;
    }

    public static ArrayList<Order> driveGetTimeMyOrder(){
        ArrayList<Order> obj = new ArrayList<Order>();
        try {
            String url = "http://10.0.2.2/fyp_connect/driver_get_order2_time.php?id="+SaveData.driver.getId();
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            try {
                for (int i = 0; i < json.getJSONArray("order").length(); i++) {
                    JSONObject jsonObj = json.getJSONArray("order").getJSONObject(i);
                    int number = jsonObj.getInt("number");
                    String date_time = jsonObj.getString("date_time");
                    String all_pick_up = jsonObj.getString("all_pick_up");
                    String received_by_customer = jsonObj.getString("received_by_customer");
                    int order_total = jsonObj.getInt("order_total");
                    String Customerid = jsonObj.getString("Customerid");
                    String Driverid = jsonObj.getString("Driverid");
                    obj.add(new Order(number,date_time,all_pick_up,received_by_customer,order_total,Customerid,Driverid));
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return obj;
    }
    public static ArrayList<Food> cusSearchFood(String keyword, String type,String min,String max) {
        ArrayList<Food> obj = new ArrayList<Food>();
        try {
            String url = "http://10.0.2.2/fyp_connect/advance_search_food.php?keyword="+keyword+"&type="+type+"&min="+min+"&max="+max;
            url = url.replaceAll(" ","%20");
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            try {
                for (int i = 0; i < json.getJSONArray("food").length(); i++) {
                    JSONObject jsonObj = json.getJSONArray("food").getJSONObject(i);
                    String foodId = jsonObj.getString("id");
                    String foodName = jsonObj.getString("name");
                    String foodType = jsonObj.getString("type");
                    String foodPrice = jsonObj.getString("price");
                    String foodImage = jsonObj.getString("image");
                    String foodRestId = jsonObj.getString("Restaurantid");
                    obj.add(new Food(foodId, foodName, foodType, foodPrice, foodImage, foodRestId));
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return obj;
    }
    public static ArrayList<Restaurant> cusSearchRestaurant(String keyword, String type,String district) {
        ArrayList<Restaurant> obj = new ArrayList<Restaurant>();
        try {
            String url = "http://10.0.2.2/fyp_connect/advance_search_restaurant.php?keyword="+keyword+"&type="+type+"&district=" + district;
            url = url.replaceAll(" ","%20");
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            client.setRequestMethod("GET");
            client.connect();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();
            JSONObject json = new JSONObject(reply);
            try {
                if(type.equals("restaurant"))
                    for (int i = 0; i < json.getJSONArray("restaurant").length(); i++) {
                        JSONObject jsonObj = json.getJSONArray("restaurant").getJSONObject(i);
                        String restaurantId = jsonObj.getString("id");
                        String restaurantName = jsonObj.getString("name");
                        String restaurantAddress = jsonObj.getString("address");
                        String restaurantType = jsonObj.getString("type");
                        String restaurantTelNum = jsonObj.getString("telNum");
                        String restaurantUserid = jsonObj.getString("Userid");
                        obj.add(new Restaurant(restaurantId, restaurantName, restaurantAddress, restaurantType, restaurantTelNum, restaurantUserid));
                    }

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return obj;
    }
}


package com.example.brotherj.uidesign.JsonClass;

import android.os.Build;
import android.os.StrictMode;
import android.util.Log;


import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.bean.Customer;
import com.example.brotherj.uidesign.bean.Driver;
import com.example.brotherj.uidesign.bean.Food;
import com.example.brotherj.uidesign.bean.Order;
import com.example.brotherj.uidesign.bean.Restaurant;

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
                        int credit_card_number = jsonObj.getInt("credit_card_number");
                        int credit_card_security_code = jsonObj.getInt("credit_card_security_code");

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

    public static void creatOrder(int qty,Food food){
        try {
            String url = "http://10.0.2.2/fyp_connect/create_order.php?customerid="+SaveData.customer.getId();
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
                number = json.getInt("success");
                creatOrderLine(number,qty,food);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    public static void creatOrderLine(int number,int qty,Food food) {
        try {
            String url = "http://10.0.2.2/fyp_connect/create_orderline.php?ordernumber="+number+"&foodid="+food.getId()+"&quanitity="+qty+"&Restaurantid="+food.getRestaurantid();
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
}

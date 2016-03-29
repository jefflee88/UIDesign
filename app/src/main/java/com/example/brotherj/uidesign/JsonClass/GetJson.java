package com.example.brotherj.uidesign.JsonClass;

import android.os.Build;
import android.os.StrictMode;
import android.util.Log;


import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.bean.Customer;
import com.example.brotherj.uidesign.bean.Driver;
import com.example.brotherj.uidesign.bean.Restaurant;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetJson {
    public static void changeVersion() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
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
            try {
                if(type.equals("customer")){
                    for(int i=0; i<json.getJSONArray("user").length();i++) {
                        JSONObject jsonObj = json.getJSONArray("user").getJSONObject(i);
                        String id = jsonObj.getString("id");
                        String name = jsonObj.getString("name");
                        String address = jsonObj.getString("address");
                        String email = jsonObj.getString("email");
                        String telNum = jsonObj.getString("telNum");
                        String Userid = jsonObj.getString("Userid");
                        SaveData.customer=new Customer(id, name, address, email, telNum, Userid);
                    }
                }
                if(type.equals("restaurant")) {
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
                if(type.equals("driver")){
                    for(int i=0; i<json.getJSONArray("order").length();i++) {
                        JSONObject jsonObj = json.getJSONArray("order").getJSONObject(i);
                        String id = jsonObj.getString("id");
                        String name = jsonObj.getString("name");
                        String telNum = jsonObj.getString("telNum");
                        String Userid = jsonObj.getString("Userid");
                        SaveData.driver=new Driver(id, name, telNum, Userid);
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

    public static String CheckUserType(String userId,String userPw) {
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
}

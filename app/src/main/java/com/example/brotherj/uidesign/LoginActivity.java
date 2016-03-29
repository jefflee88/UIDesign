package com.example.brotherj.uidesign;

import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.JsonClass.GetJson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button btnReset = (Button) this.findViewById(R.id.btnReset);
        Button btnLogin = (Button) this.findViewById(R.id.btnLogin);
        GetJson.changeVersion();
        btnReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoginActivity.this.finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText pwd = (EditText) findViewById(R.id.edtPwd);
                EditText userName = (EditText) findViewById(R.id.edtUserid);
                String name = userName.getText().toString();
                String password = pwd.getText().toString();
                boolean checkLogin = GetJson.CheckUser(name,password);
                if(checkLogin == true){
                    Toast.makeText(getApplicationContext(), "Hello " + name + "!!", Toast.LENGTH_SHORT).show();
                    String type = GetJson.CheckUserType(name, password);
                    if(type.equals("customer")){
                        GetJson.getUserDetail(name,password,"customer");
                        Intent Intent = new Intent(view.getContext(), CustomerActivity.class);
                        startActivityForResult(Intent, 0);
                    }else if(type.equals("restaurant")){
                        Intent Intent = new Intent(view.getContext(), RestaurantActivity.class);
                        startActivityForResult(Intent, 0);
                    }else if(type.equals("driver")){
                        GetJson.getUserDetail(name,password,"driver");
                        Intent Intent = new Intent(view.getContext(), DriverActivity.class);
                        startActivityForResult(Intent, 0);
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "please input again!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}

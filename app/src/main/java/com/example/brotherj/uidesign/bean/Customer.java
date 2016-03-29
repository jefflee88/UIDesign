package com.example.brotherj.uidesign.bean;

/**
 * Created by User on 29/3/2016.
 */
public class Customer {
    String id,name,address,email,telNum,Userid;
    public Customer(String id,String name,String address,String email,String telNum,String Userid){
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.telNum = telNum;
        this.Userid = Userid;
    }
    public String getId(){return id;}
    public String getName(){return name;}
    public String getAddress(){return address;}
    public String getEmail(){return email;}
    public String getTelNum(){return telNum;}
    public String getUserid(){return Userid;}
    public void setId(String id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setAddress(String address){this.address = address;}
    public void setEmail(String email){this.email = email;}
    public void setTelNum(String telNum){this.telNum = telNum;}
    public void setUserid(String Userid){this.Userid = Userid;}
}

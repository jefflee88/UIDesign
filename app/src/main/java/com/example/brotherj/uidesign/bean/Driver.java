package com.example.brotherj.uidesign.bean;

/**
 * Created by User on 29/3/2016.
 */
public class Driver {
    String id,name,telNum,Userid;
    public Driver(String id,String name,String telNum,String Userid){
        this.id = id;
        this.name = name;
        this.telNum = telNum;
        this.Userid = Userid;
    }
    public String getId(){return id;}
    public String getName(){return name;}
    public String getTelNum(){return telNum;}
    public String getUserid(){return Userid;}

    public void setId(String id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setTelNum(String telNum) {this.telNum = telNum;}
    public void setUserid(String userid) {Userid = userid;}
}

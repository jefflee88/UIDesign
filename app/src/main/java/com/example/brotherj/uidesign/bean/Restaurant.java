package com.example.brotherj.uidesign.bean;

/**
 * Created by User on 29/3/2016.
 */
public class Restaurant {
    String id,name,address,type,telNum,Userid;
    public Restaurant(String id,String name,String address,String type,String telNum,String Userid){
        this.id=id;
        this.name=name;
        this.address=address;
        this.type=type;
        this.telNum=telNum;
        this.Userid=Userid;
    }


    public String getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTelNum() {
        return telNum;
    }

    public String getType() {
        return type;
    }

    public String getUserid() {
        return Userid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }
}

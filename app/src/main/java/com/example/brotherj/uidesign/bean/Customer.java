package com.example.brotherj.uidesign.bean;

/**
 * Created by User on 29/3/2016.
 */
public class Customer {
    String id,name,address,email,telNum,Userid,payment;
    int credit_card_number,credit_card_security_code;
    public Customer(String id,String name,String address,String email,String telNum,String payment,int credit_card_number,int credit_card_security_code,String Userid){
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.telNum = telNum;
        this.payment = payment;
        this.credit_card_number = credit_card_number;
        this.credit_card_security_code = credit_card_security_code;
        this.Userid = Userid;

    }

    public int getCredit_card_number() {
        return credit_card_number;
    }

    public int getCredit_card_security_code() {
        return credit_card_security_code;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public void setCredit_card_number(int credit_card_number) {
        this.credit_card_number = credit_card_number;
    }

    public void setCredit_card_security_code(int credit_card_security_code) {
        this.credit_card_security_code = credit_card_security_code;
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

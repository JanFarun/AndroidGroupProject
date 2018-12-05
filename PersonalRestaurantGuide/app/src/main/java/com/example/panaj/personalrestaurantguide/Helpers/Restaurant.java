package com.example.panaj.personalrestaurantguide.Helpers;

import android.support.annotation.NonNull;

public class Restaurant {

    private String name;
    private String address;
    private String phoneNumber;
    private String tag;
    private double rate = 0;

    public Restaurant(String name,String address,String phoneNumber,String tag,double rate){
        this.name = name;
        this.address = address;
        this.phoneNumber= phoneNumber;
        this.tag = tag;
        this.rate = rate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Restaurant Name: "+name+"Type: "+tag;
    }


}

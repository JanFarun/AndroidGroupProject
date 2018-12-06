package com.example.panaj.personalrestaurantguide.Helpers;

import android.support.annotation.NonNull;

public class Restaurant {
    private long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String tag;
    private String description;
    private int rate ;

    public Restaurant(String name, String address, String phoneNumber, String tag,String description, int rate){
        this.name = name;
        this.address = address;
        this.phoneNumber= phoneNumber;
        this.tag = tag;
        this.description = description;
        this.rate = rate;
    }


    public Restaurant(Long id, String name, String address, String phoneNumber, String tag,String description, int rate){
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber= phoneNumber;
        this.tag = tag;
        this.description = description;
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Restaurant Name: "+name+"Type: "+tag;
    }


}

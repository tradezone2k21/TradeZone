package com.example.everythingstore.buyer.register;

public class BuyerSignUpModel {
    String name,phone,address,city,pincode,state;

    public BuyerSignUpModel(String name, String phone, String address, String city, String pincode, String state) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.pincode = pincode;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

package com.example.everythingstore.seller.post_login.AddItems;

import android.net.Uri;

import com.example.everythingstore.R;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;



public class AddItemModel {
    String itemID="",itemName,itemDesc,sizes,sellerName,sellerPhone;
    int quantity;
    Float cost;
    String imageUriList;
    long dateListed;


    public AddItemModel() {
    }



    public AddItemModel(String itemID, String itemName, String itemDesc, String sizes, int quantity, Float cost, String imageUriList) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.sizes = sizes;
        this.quantity = quantity;
        this.cost = cost;
        this.imageUriList = imageUriList;
        this.dateListed=System.currentTimeMillis();
        this.sellerName= Prefs.getString("PrefsSellerName","Null");
        this.sellerPhone=Prefs.getString("PrefsSellerPhone","NUll");

    }

    public long getDateListed() {
        return dateListed;
    }

    public void setDateListed(long dateListed) {
        this.dateListed = dateListed;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getImageUriList() {
        return imageUriList;
    }

    public void setImageUriList(String imageUriList) {
        this.imageUriList = imageUriList;
    }
}

package com.example.everythingstore.buyer.post_login.buy_items;

public class BuyerItemModel {
    private String name,description;
    int cost;
    Integer imgId=null;

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public BuyerItemModel(int imgId, String name, String description, int cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.imgId = imgId;
    }



    public BuyerItemModel(String name, String description, int cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}

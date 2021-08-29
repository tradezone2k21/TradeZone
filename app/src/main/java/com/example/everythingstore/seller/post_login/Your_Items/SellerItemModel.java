package com.example.everythingstore.seller.post_login.Your_Items;

public class SellerItemModel {
    private String name,description;
    int cost;
    Integer imgId=null;

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public SellerItemModel(int imgId, String name, String description, int cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.imgId = imgId;
    }



    public SellerItemModel(String name, String description, int cost) {
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

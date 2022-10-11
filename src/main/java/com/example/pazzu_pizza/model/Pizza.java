package com.example.pazzu_pizza.model;

public class Pizza {
    private String name;
    private String imgSrc;
    private double price;

    public void setName(String name) {
        this.name = name;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public double getPrice() {
        return price;
    }

}

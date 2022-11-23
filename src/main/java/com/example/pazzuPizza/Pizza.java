package com.example.pazzuPizza;

import java.sql.Blob;

public class Pizza {
    private String name;


    private String thin_25;
    private String thin_30;
    private String thin_40;

    private String thick_25;
    private String thick_30;
    private String thick_40;
    private String imgSrc;

    public Blob getBlob() {
        return blob;
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
    }

    private Blob blob;


    public Pizza(String name, String thin_25, String thin_30, String thin_40, String thick_25, String thick_30, String thick_40, String imgSrc) {
        this.name = name;
        this.thin_25 = thin_25;
        this.thin_30 = thin_30;
        this.thin_40 = thin_40;
        this.thick_25 = thick_25;
        this.thick_30 = thick_30;
        this.thick_40 = thick_40;
        this.imgSrc = imgSrc;
    }



    public String getSize() {
        return size;
    }

    public String getTypeDough() {
        return typeDough;
    }

    private String size;

    public int getCounterPizza() {
        return counterPizza;
    }

    public void setCounterPizza(int counterPizza) {
        this.counterPizza = counterPizza;
    }

    private int counterPizza=0;
    private String typeDough;

    public Pizza(String name, String image, double price, String size, String typeDough) {
        this.name = name;
        this.imgSrc = image;
        this.size = size;
        this.typeDough = typeDough;
    }

    public Pizza() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }


    public String getName() {
        return name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public String getThin_25() {
        return thin_25;
    }

    public void setThin_25(String thin_25) {
        this.thin_25 = thin_25;
    }

    public String getThin_30() {
        return thin_30;
    }

    public void setThin_30(String thin_30) {
        this.thin_30 = thin_30;
    }

    public String getThin_40() {
        return thin_40;
    }

    public void setThin_40(String thin_40) {
        this.thin_40 = thin_40;
    }

    public String getThick_25() {
        return thick_25;
    }

    public void setThick_25(String thick_25) {
        this.thick_25 = thick_25;
    }

    public String getThick_30() {
        return thick_30;
    }

    public void setThick_30(String thick_30) {
        this.thick_30 = thick_30;
    }

    public String getThick_40() {
        return thick_40;
    }

    public void setThick_40(String thick_40) {
        this.thick_40 = thick_40;
    }


}

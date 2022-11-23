package com.example.pazzuPizza;

public class PizzaBasket {
    private String name;
    private int sizePizza;
    private String doughType;
    private int price;
    private String imgPath;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private String address;
    private String comment;
    private int counter=0;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    public PizzaBasket(String name, int sizePizza, String doughType, int price, int counter) {
        this.name = name;
        this.sizePizza = sizePizza;
        this.doughType = doughType;
        this.price = price;
        this.counter = counter;
    }

    public PizzaBasket(String name, int sizePizza, String doughType, int price, String imgPath) {
        this.name = name;
        this.sizePizza = sizePizza;
        this.doughType = doughType;
        this.price = price;
        this.imgPath = imgPath;
        counter++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSizePizza() {
        return sizePizza;
    }

    public void setSizePizza(int sizePizza) {
        this.sizePizza = sizePizza;
    }

    public String getDoughType() {
        return doughType;
    }

    public void setDoughType(String doughType) {
        this.doughType = doughType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}

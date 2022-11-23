package com.example.pazzuPizza;

public class PizzaOrders {
    private String namePizza;
    private String typeTest;
    private String pizzaSize;
    private int amount;

    public PizzaOrders(String namePizza, String typeTest, String pizzaSize, int amount, int price) {

        this.namePizza = namePizza;
        this.typeTest = typeTest;
        this.pizzaSize = pizzaSize;
        this.amount = amount;
        this.price = price;
    }

    private int price;

    public String getNamePizza() {
        return namePizza;
    }

    public void setNamePizza(String namePizza) {
        this.namePizza = namePizza;
    }

    public String getTypeTest() {
        return typeTest;
    }

    public void setTypeTest(String typeTest) {
        this.typeTest = typeTest;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

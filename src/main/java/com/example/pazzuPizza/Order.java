package com.example.pazzuPizza;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Order {
    @FXML
    private Label number;

    @FXML
    private Label pizzaLabel;

    @FXML
    private Label price;

    @FXML
    private Label sizePizza;

    @FXML
    private Label typeTest;

    public void setInfo(PizzaBasket pizzaBasket){
        pizzaLabel.setText(pizzaBasket.getName());
        typeTest.setText(pizzaBasket.getDoughType());
        sizePizza.setText(String.valueOf(pizzaBasket.getSizePizza()));
        number.setText(String.valueOf(pizzaBasket.getCounter()));
        price.setText(String.valueOf(pizzaBasket.getPrice()));
    }
}

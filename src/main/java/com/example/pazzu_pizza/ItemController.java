package com.example.pazzu_pizza;

import com.example.pazzu_pizza.model.Pizza;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemController {
    @FXML
    private ImageView imgLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    private Pizza pizza;

    public void setData(Pizza pizza) {
        this.pizza = pizza;
        nameLabel.setText(pizza.getName());
        priceLabel.setText(HelloApplication.CURRENCY + pizza.getPrice());
        Image image = new Image(getClass().getResourceAsStream(pizza.getImgSrc()));
        imgLabel.setImage(image);
    }
}

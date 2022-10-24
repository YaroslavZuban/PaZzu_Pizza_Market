package com.example.pazzu_pizza;

import com.example.pazzu_pizza.model.Pizza;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ItemController {
    @FXML
    private ImageView imgLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private void click(MouseEvent mouseEvent){
        myListener.onClickListener(pizza);
    }

    private Pizza pizza;
    private MyListener myListener;

    public void setData(Pizza pizza, MyListener myListener) {
        this.pizza = pizza;
        this.myListener=myListener;
        nameLabel.setText(pizza.getName());
        priceLabel.setText(HelloApplication.CURRENCY + pizza.getPrice());
        Image image = new Image(getClass().getResourceAsStream(pizza.getImgSrc()));
        imgLabel.setImage(image);
    }

}

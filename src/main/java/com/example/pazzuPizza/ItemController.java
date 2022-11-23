package com.example.pazzuPizza;

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
    private void click(MouseEvent mouseEvent){
        myListener.onClickListener(pizza);
    }

    private Pizza pizza;
    private MyListener myListener;

    public void setData(Pizza pizza, MyListener myListener) {
        this.pizza = pizza;
        this.myListener=myListener;
        nameLabel.setText(pizza.getName());

        Image image = new Image("file:"+pizza.getImgSrc());
        imgLabel.setImage(image);
    }

}

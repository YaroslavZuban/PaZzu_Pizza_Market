package com.example.pazzu_pizza;

import com.example.pazzu_pizza.model.Pizza;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CharacteristicsPizza {
    @FXML
    private ImageView imgLabel;

    @FXML
    private Label name;

    @FXML
    private Label number;

    @FXML
    private Label price;

    @FXML
    private Label typeAndSize;
    private Pizza pizza;



    public void setInfo(Pizza pizza){
        this.name.setText(pizza.getName());
        this.typeAndSize.setText("размер "+pizza.getSize()+
                ", тип теста "+pizza.getTypeDough());
        this.number.setText("1");
        this.price.setText(String.valueOf(pizza.getPrice()));

        Image image = new Image(getClass().getResourceAsStream(pizza.getImgSrc()));
        this.imgLabel.setImage(image);
    }

    @FXML
    void add(ActionEvent event) {
        int number= Integer.parseInt(this.number.getText());
        number++;

        this.number.setText(String.valueOf(number));
    }

    @FXML
    void minus(ActionEvent event) {
        int number= Integer.parseInt(this.number.getText());

        if(number!=1){
            number--;
            this.number.setText(String.valueOf(number));
        }
    }
}

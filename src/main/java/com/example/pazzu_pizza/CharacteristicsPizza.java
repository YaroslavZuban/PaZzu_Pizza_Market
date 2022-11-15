package com.example.pazzu_pizza;

import com.example.pazzu_pizza.model.Pizza;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CharacteristicsPizza {
    @FXML
    private ImageView imgLabel;

    @FXML
    private Label name;

    @FXML
    private Label number;

    @FXML
    private Label price;

     private int pricePizza;

    @FXML
    private AnchorPane windowsPane;

    @FXML
    private Label typeAndSize;
    private Pizza pizza;

    public CharacteristicsPizza() {

    }


    public void setInfo(PizzaBasket pizza){
        this.name.setText(pizza.getName());
        this.typeAndSize.setText("размер "+pizza.getSizePizza()+" см"+
                ", тип теста "+pizza.getDoughType());
        pricePizza=pizza.getPrice();
        this.price.setText(String.valueOf(pizza.getPrice()*pizza.getCounter()));
        this.number.setText(String.valueOf(pizza.getCounter()));
        Image image = new Image("file:"+pizza.getImgPath());
        this.imgLabel.setImage(image);
    }

    @FXML
    void add(ActionEvent event) {
        int number= Integer.parseInt(this.number.getText());
        number++;

        this.price.setText(String.valueOf(number*pricePizza));
        this.number.setText(String.valueOf(number));
    }

    @FXML
    void minus(ActionEvent event) {
        int number= Integer.parseInt(this.number.getText());
        number--;

       /* if(number==0){
            Stage ss = (Stage) windowsPane.getScene().getWindow();//береться параметры стратого она и закрывается
            ss.close();
        }else{
            this.price.setText(String.valueOf(number*pricePizza));
            this.number.setText(String.valueOf(number));
        }*/
    }
}

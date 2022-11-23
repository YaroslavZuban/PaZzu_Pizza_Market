package com.example.pazzuPizza;

import com.example.pazzuPizza.BasketController;
import com.example.pazzuPizza.Pizza;
import com.example.pazzuPizza.PizzaBasket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.MalformedURLException;

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
    private PizzaBasket pb;

    public CharacteristicsPizza() {

    }

    public void setInfo(PizzaBasket pizza) {
        pb=pizza;
        this.name.setText(pizza.getName());
        this.typeAndSize.setText("размер " + pizza.getSizePizza() + " см" +
                ", тип теста " + pizza.getDoughType());
        pricePizza = pizza.getPrice();
        this.price.setText(String.valueOf(pizza.getPrice() * pizza.getCounter()));
        this.number.setText(String.valueOf(pizza.getCounter()));
        Image image = new Image("file:" + pizza.getImgPath());
        this.imgLabel.setImage(image);
    }

    @FXML
    void add(ActionEvent event) {
        int number = Integer.parseInt(this.number.getText());
        number++;

        this.price.setText(String.valueOf(number * pricePizza));
        this.number.setText(String.valueOf(number));
    }

    @FXML
    void minus(ActionEvent event) throws MalformedURLException {

        int number = Integer.parseInt(this.number.getText());

        if(number>0) {
            number--;

            if (number <= 0) {

                for (int i = 0; i < BasketController.basket.size(); i++) {
                    PizzaBasket tempP = BasketController.basket.get(i);

                    if (pb.getName().equals(tempP.getName()) && pb.getDoughType().equals(tempP.getDoughType()) &&
                            pb.getSizePizza() == tempP.getSizePizza()) {
                        System.out.println("условие выполняется");
                        BasketController.basket.remove(i);
                    }
                }

            }


            this.price.setText(String.valueOf(number * pricePizza));
            this.number.setText(String.valueOf(number));
        }
    }
}

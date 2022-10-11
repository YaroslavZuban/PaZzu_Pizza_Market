package com.example.pazzu_pizza;

import com.example.pazzu_pizza.model.Pizza;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private VBox chosenPizzaCard;

    @FXML
    private Label namePizzaLabel;

    @FXML
    private ImageView pizzaImg;

    @FXML
    private Label pricePizza;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Pizza> pizza = new ArrayList<>();

    private List<Pizza> getData() {
        List<Pizza> pizza = new ArrayList<>();
        Pizza pizz;

        for (int i = 0; i < 20; i++) {
            pizz = new Pizza();
            pizz.setName("Пипирони");
            pizz.setPrice(6.00);
            pizz.setImgSrc("img/pepperoni.png");
            pizza.add(pizz);
        }

        return pizza;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pizza.addAll(getData());
        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < pizza.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("Item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(pizza.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
package com.example.pazzu_pizza;

import com.example.pazzu_pizza.model.Pizza;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BasketController implements Initializable {
    public static HelloController helloController;
    public static User user;

    public void setBasket(List<Pizza> basket) {
        this.basket = basket;
    }

    private List<Pizza> basket;


    @FXML
    private TextField cityText;

    @FXML
    private TextField codeText;

    @FXML
    private TextArea commentText;

    @FXML
    private TextField entranceText;

    @FXML
    private TextField flatText;

    @FXML
    private TextField floorText;

    @FXML
    private GridPane grid;

    @FXML
    private TextField houseText;

    @FXML
    private TextField nameCompanyText;

    @FXML
    private TextField outsideText;

    @FXML
    private Label priceText;

    @FXML
    void confirmOrderAction(ActionEvent event) {

    }

    @FXML
    void inputAction(ActionEvent event) {

    }

    @FXML
    void marketAction(ActionEvent event) {

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        basket=helloController.getBasket();

        int row = 1;

        try {

            for (int i = 0; i < basket.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("characteristicsPizza.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CharacteristicsPizza characteristicsPizza=fxmlLoader.getController();
                characteristicsPizza.setInfo(basket.get(i));

                grid.add(anchorPane,0, row++);

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

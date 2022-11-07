package com.example.pazzu_pizza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class AdminController {
    @FXML
    private GridPane grid;

    @FXML
    private Label name;
    private Boolean isMaybeAddPizza=true;
    private Boolean isMaybeOrders=true;

    @FXML
    void addItem(ActionEvent event) throws IOException {
        if(isMaybeAddPizza) {
            grid.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("addAdminPizza.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            grid.add(anchorPane, 1, 1);
            isMaybeAddPizza=false;
            isMaybeOrders=true;
        }
    }

    @FXML
    void exitAction(ActionEvent event) {

    }

    @FXML
    void marketAction(ActionEvent event) {

    }

    @FXML
    void ordersAction(ActionEvent event) throws IOException {
        if(isMaybeOrders){
            grid.getChildren().clear();

            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("delivery.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            grid.add(anchorPane, 1, 1);
            isMaybeAddPizza=true;
            isMaybeOrders=false;
        }
    }

}


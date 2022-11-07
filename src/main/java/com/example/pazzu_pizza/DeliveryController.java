package com.example.pazzu_pizza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class DeliveryController {
    @FXML
    private TextArea commentText;

    @FXML
    private GridPane grid;

    @FXML
    private Label nameLabel;

    @FXML
    private Label phone;

    @FXML
    private Label placeDeliveryLabel_1;

    @FXML
    private Label placeDeliveryLabel_2;

    @FXML
    private AnchorPane windowsDelivery;

    @FXML
    void orderFulfillmentAction(ActionEvent event) {

    }

}

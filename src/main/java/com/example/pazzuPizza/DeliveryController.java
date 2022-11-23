package com.example.pazzuPizza;

import com.example.pazzuPizza.Order;
import com.example.pazzuPizza.PizzaBasket;
import com.example.pazzuPizza.User;
import com.example.pazzuPizza.DatabaseOrders;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.List;

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
   private TextArea address;

    @FXML
    private AnchorPane windowsDelivery;

    @FXML
    private Button deliveryButton;
    private User user=new User();

    @FXML
    void orderFulfillmentAction(ActionEvent event) {
        deliveryButton.setStyle("-fx-background-color: #00C622;");
        deliveryButton.setText("Заказ обработан");
        DatabaseOrders databaseOrders=new DatabaseOrders();
        databaseOrders.deleteOrder(user);
    }

    public void setInfo(List<PizzaBasket> list) throws IOException {
        int row=1;

        for (int i = 0; i < list.size(); i++) {
            PizzaBasket pizzaBasket=list.get(i);

            if(i==0){
                user.setTelephone(pizzaBasket.getUser().getTelephone());
                user.setName(pizzaBasket.getUser().getName());

                phone.setText(pizzaBasket.getUser().getTelephone());
                commentText.setText(pizzaBasket.getComment());
                address.setText(pizzaBasket.getAddress());
                nameLabel.setText(pizzaBasket.getUser().getName());
            }

            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("pizzaOrder.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            Order order=fxmlLoader.getController();
            order.setInfo(pizzaBasket);

            grid.add(anchorPane, 0, row++);

            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_COMPUTED_SIZE);

            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_COMPUTED_SIZE);

            GridPane.setMargin(anchorPane, new Insets(10));
        }
    }

}

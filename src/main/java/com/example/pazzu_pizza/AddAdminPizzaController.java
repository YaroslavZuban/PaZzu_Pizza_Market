package com.example.pazzu_pizza;

import com.example.pazzu_pizza.animations.Movement;
import com.example.pazzu_pizza.model.Pizza;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddAdminPizzaController {

    @FXML
    public AnchorPane addWindow;
    @FXML
    private TextField namePizza;

    @FXML
    private TextField pathImg;

    @FXML
    private TextField thick_25;

    @FXML
    private TextField thick_30;

    @FXML
    private TextField thick_40;

    @FXML
    private TextField thin_25;
    @FXML
    private TextField thin_30;

    @FXML
    private TextField thin_40;

    @FXML
    void addImg(ActionEvent event) {
        SearchFile.search(null, pathImg);
    }

    @FXML
    void addPizza(ActionEvent event) throws InterruptedException, IOException, SQLException {
        if (namePizza.getText().equals("")) {
            Movement.fieldMovement("Название пиццы", null, addWindow);
        } else if (thin_25.getText().equals("")) {
            Movement.fieldMovement("Цена тонкого 25 см", null, addWindow);
        } else if (thin_30.getText().equals("")) {
            Movement.fieldMovement("Цена тонкого 30 см", null, addWindow);
        } else if (thin_40.getText().equals("")) {
            Movement.fieldMovement("Цена тонкого 40 см", null, addWindow);
        } else if (thick_25.getText().equals("")) {
            Movement.fieldMovement("Цена толстого 25 см", null, addWindow);
        } else if (thick_25.getText().equals("")) {
            Movement.fieldMovement("Цена толстого 30 см", null, addWindow);
        } else if (thick_25.getText().equals("")) {
            Movement.fieldMovement("Цена толстого 40 см", null, addWindow);
        } else if (pathImg.getText().equals("")) {
            Movement.fieldMovement("Фото", null, addWindow);
        } else {
            DatabasePizza databasePizza = new DatabasePizza();

            String name = namePizza.getText();

            String thin_25_p = thin_25.getText();
            String thin_30_p = thin_30.getText();
            String thin_40_p = thin_40.getText();

            String thick_25_p = thick_25.getText();
            String thick_30_p = thick_30.getText();
            String thick_40_p = thick_40.getText();

            String pathFile = pathImg.getText();

            Pizza pizza = new Pizza(name, thin_25_p, thin_30_p, thin_40_p,
                    thick_25_p, thick_30_p, thick_40_p, pathFile);

            databasePizza.singUpPizza(pizza);

            namePizza.setText("");

            thin_25.setText("");
            thin_30.setText("");
            thin_40.setText("");

            thick_25.setText("");
            thick_30.setText("");
            thick_40.setText("");

            pathImg.setText("");

        }
    }
}

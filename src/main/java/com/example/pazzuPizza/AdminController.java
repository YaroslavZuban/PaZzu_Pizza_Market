package com.example.pazzuPizza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminController {

    @FXML
    private BorderPane window;
    @FXML
    private GridPane grid;

    @FXML
    private Label name;
    private Boolean isMaybeAddPizza = true;
    private Boolean isMaybeOrders = true;
    public static User user;
    public static List<PizzaBasket> basket;
    public List<User> nameUser = new ArrayList<>();

    @FXML
    void addItem(ActionEvent event) throws IOException {
        if (isMaybeAddPizza) {
            grid.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("addAdminPizza.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            grid.add(anchorPane, 1, 1);
            isMaybeAddPizza = false;
            isMaybeOrders = true;
        }
    }

    private Map<String, List<PizzaBasket>> getData() throws SQLException {
        DatabaseOrders data = new DatabaseOrders();
        Map<String, List<PizzaBasket>> map = new HashMap<>();
        ResultSet resultSet = data.getOrder();

        while (resultSet.next()) {
            String telephone = resultSet.getString(4);
            List<PizzaBasket> list;

            if (map.get(telephone) == null) {
                list = new ArrayList<>();
            } else {
                list = new ArrayList<>(map.get(telephone));
            }

            User tempUser = new User();
            tempUser.setName(resultSet.getString(2));
            tempUser.setTelephone(telephone);

            nameUser.add(tempUser);

            String namePizza = resultSet.getString(6);
            String testType = resultSet.getString(7);

            int pizzaSize = resultSet.getInt(8);
            int number = resultSet.getInt(9);
            int price = resultSet.getInt(10);

            PizzaBasket pizzaBasket = new PizzaBasket(namePizza, pizzaSize, testType, price, number);
            pizzaBasket.setUser(tempUser);
            list.add(pizzaBasket);

            String address = resultSet.getString(3);
            String comment = resultSet.getString(5);

            pizzaBasket.setComment(comment);
            pizzaBasket.setAddress(address);
            map.put(telephone, list);
        }

        return map;
    }


    @FXML
    void exitAction(ActionEvent event) throws IOException {
        HelloController.user = null;
        Stage ss = (Stage) window.getScene().getWindow();//береться параметры стратого она и закрывается
        ss.close();//закрытия окна

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("market.fxml"));//считывание дизайн самого интерфейса

        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());//запуск дизайн
        stage.setTitle("PaZzU Pizza");//название окна
        //   stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("bit.png")));//установление иконки
        stage.setScene(scene);//установка Scene для Stage
        stage.setResizable(false);//запрещает пользователю изменять размер окна
        stage.show();//Попытки показать это окно, установив для видимости значение true
    }

    @FXML
    void marketAction(ActionEvent event) throws IOException {
        HelloController.user = user;
        HelloController.basket = basket;

        Stage ss = (Stage) window.getScene().getWindow();//береться параметры стратого она и закрывается
        ss.close();//закрытия окна

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("market.fxml"));//считывание дизайн самого интерфейса

        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());//запуск дизайн
        stage.setTitle("PaZzU Pizza");//название окна
        //   stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("bit.png")));//установление иконки
        stage.setScene(scene);//установка Scene для Stage
        stage.setResizable(false);//запрещает пользователю изменять размер окна
        stage.show();//Попытки показать это окно, установив для видимости значение true
    }

    @FXML
    void ordersAction(ActionEvent event) throws IOException, SQLException {
        if (isMaybeOrders) {
            Map<String, List<PizzaBasket>> map = getData();
            int row = 1;
            grid.getChildren().clear();

            for (Map.Entry<String, List<PizzaBasket>> entry : map.entrySet()) {
                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("delivery.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                DeliveryController deliveryController=fxmlLoader.getController();
                deliveryController.setInfo(entry.getValue());

                grid.add(anchorPane, 0, row++);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }

            isMaybeAddPizza = true;
            isMaybeOrders = false;
        }
    }

    @FXML
    public void updateAction(ActionEvent actionEvent) {

    }
}


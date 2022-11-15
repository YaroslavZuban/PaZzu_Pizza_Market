package com.example.pazzu_pizza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminController {

    @FXML
    private BorderPane window;
    @FXML
    private GridPane grid;

    @FXML
    private Label name;
    private Boolean isMaybeAddPizza=true;
    private Boolean isMaybeOrders=true;
    public static User user;
    public static List<PizzaBasket> basket;

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
    void exitAction(ActionEvent event) throws IOException {
        HelloController.user=null;
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


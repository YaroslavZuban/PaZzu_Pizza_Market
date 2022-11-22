package com.example.pazzu_pizza;

import com.example.pazzu_pizza.model.Pizza;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BasketController implements Initializable {
    public static HelloController helloController;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        BasketController.user = user;
    }

    public static User user;

    public void setBasket(List<PizzaBasket> basket) {
        this.basket = basket;
    }

    public static List<PizzaBasket> basket;
    @FXML
    public Button inputButton;
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
    private BorderPane window;

    @FXML
    void confirmOrderAction(ActionEvent event) {
        if (cityText.getText().equals("") || outsideText.getText().equals("") || houseText.getText().equals("")) {

        } else if (user == null) {

        } else if (basket == null) {

        } else {
            DatabaseOrders data = new DatabaseOrders();

            String address = "Город: " + cityText.getText() + " улица: " + outsideText.getText() + " дом: " + houseText.getText() + " квартира: "
                    + flatText.getText();

            if (!entranceText.getText().equals("")) {
                address += " подъезд: " + entranceText.getText();
            }

            if (!codeText.getText().equals("")) {
                address += " код домофона: " + codeText.getText();
            }

            if (!floorText.getText().equals("")) {
                address += " этаж: " + floorText.getText();
            }

            if (!nameCompanyText.getText().equals("")) {
                address += " название компании: " + nameCompanyText.getText();
            }

            String comment = commentText.getText();

            System.out.println(user.getName());
            System.out.println(user.getTelephone());

            for (int i = 0; i < basket.size(); i++) {
                data.singOrder(user, basket.get(i), address, comment);
            }

            grid.getChildren().clear();
            basket.clear();
            cityText.setText("");
            outsideText.setText("");
            houseText.setText("");
            flatText.setText("");
            entranceText.setText("");
            codeText.setText("");
            floorText.setText("");
            nameCompanyText.setText("");
            commentText.setText("");
        }
    }

    @FXML
    void inputAction(ActionEvent event) throws IOException {
        if (inputButton.getText().equals("Выход")) {
            user = null;
            inputButton.setText("Вход");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("input.fxml"));

            Stage stage = new Stage();//создается stage для запуска нового окна
            Scene scene = new Scene(loader.load());//загружается дизайн с fxml

            stage.setTitle("PaZzu-Pizza");//название нового окна
            // stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("bit.png")));//значок нового окна
            stage.setScene(scene);//установка Scene для Stage
            stage.setResizable(false);//запрещает пользователю изменять размер окна
            stage.show();//Попытки показать это окно, установив для видимости значение true

            InputController inputController = loader.getController();
            inputController.setBasketController(this);

        }
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
    public void updateAction(ActionEvent event){
        grid.getChildren().clear();

        int row = 1;

        try {

            for (int i = 0; i < basket.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("characteristicsPizza.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CharacteristicsPizza characteristicsPizza = fxmlLoader.getController();
                characteristicsPizza.setInfo(basket.get(i));

                grid.add(anchorPane, 0, row++);

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        basket = helloController.getBasket();
        user = helloController.getUser();

        int row = 1;

        if (user != null) {
            inputButton.setText("Выход");
        }

        try {
            for (int i = 0; i < basket.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("characteristicsPizza.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CharacteristicsPizza characteristicsPizza = fxmlLoader.getController();
                characteristicsPizza.setInfo(basket.get(i));

                grid.add(anchorPane, 0, row++);

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

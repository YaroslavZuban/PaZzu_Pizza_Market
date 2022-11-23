package com.example.pazzuPizza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private Label data;

    @FXML
    private Label email;

    @FXML
    private Label name;

    @FXML
    private Label surname;

    @FXML
    private Label telephone;

    @FXML
    public Button inputButton;

    @FXML
    private BorderPane window;

    public static User user;
    public static List<PizzaBasket> basket = new ArrayList<>();

    public static void setUser(User user) {
        ProfileController.user = user;
    }

    @FXML
    void inputAction(ActionEvent event) throws IOException {
        if(inputButton.getText().equals("Выход")){
            user=null;
            marketAction(new ActionEvent());
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("input.fxml"));

            Stage stage = new Stage();//создается stage для запуска нового окна
            Scene scene = new Scene(loader.load());//загружается дизайн с fxml

            stage.setTitle("PaZzu-Pizza");//название нового окна
            // stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("bit.png")));//значок нового окна
            stage.setScene(scene);//установка Scene для Stage
            stage.setResizable(false);//запрещает пользователю изменять размер окна
            stage.show();//Попытки показать это окно, установив для видимости значение true

            InputController inputController = loader.getController();
            inputController.setProfileController(this);

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (user != null) {
            inputButton.setText("Выход");

            DatabaseHandler handler=new DatabaseHandler();

            ResultSet resultSet = handler.getAllUser(user);

            try {
                while (resultSet.next()) {

                    if(user.getEmail().equals(resultSet.getString(4))) {
                        String name = resultSet.getString(2);
                        String surname = resultSet.getString(3);
                        String email = resultSet.getString(4);
                        String telephone = resultSet.getString(6);
                        String births = resultSet.getString(7);

                        user.setName(name);
                        user.setSurname(surname);
                        user.setEmail(email);
                        user.setTelephone(telephone);
                        user.setBirth(births);

                        this.name.setText(name);
                        this.surname.setText(surname);
                        this.email.setText(email);
                        this.telephone.setText(telephone);
                        this.data.setText(births);

                        break;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

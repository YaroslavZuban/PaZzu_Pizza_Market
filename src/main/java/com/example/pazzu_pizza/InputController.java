package com.example.pazzu_pizza;

import com.example.pazzu_pizza.animations.Movement;
import com.example.pazzu_pizza.animations.Shake;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InputController {
    @FXML
    private AnchorPane windowInput;
    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    private HelloController helloController;
    private User user;

    @FXML
    void authorizationActon(ActionEvent event) throws InterruptedException {
        String loginText = login.getText().trim();
        String passwordText = password.getText().trim();

        if (loginText.equals("")) {
            Movement.fieldMovement("логин", login, windowInput);
        } else if (passwordText.equals("")) {
            Movement.fieldMovement("пароль", password, windowInput);
        } else {
            try {
                loginUser(loginText, passwordText);

                helloController.setUser(user);
                helloController.registrationButton.setText(user.getEmail());
                helloController.inputButton.setText("Выход");

                Stage stage = (Stage) windowInput.getScene().getWindow();
                stage.close();
            } catch (SQLException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void loginUser(String loginText, String passwordText) throws SQLException, InterruptedException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        this.user = new User();

        user.setEmail(loginText);
        user.setPassword(passwordText);

        ResultSet resultSet = databaseHandler.getUser(user);
        HelloController helloController = new HelloController();
        int counter = 0;

        while (resultSet.next()) {
            counter++;
        }

        if (counter >= 1) {

        } else {
            Shake shakeLogin = new Shake(login);
            Shake shakePassword = new Shake(password);

            shakeLogin.playAnim();
            shakePassword.playAnim();
        }
    }

    public void setMainController(HelloController _helloController) {
        this.helloController = _helloController;
        helloController.setUser(user);
    }
}

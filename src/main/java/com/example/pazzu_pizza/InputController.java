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
    private BasketController basketController;

    private ProfileController profileController;
    private User user;
    private int counter = 0;

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

                if (counter == 1) {

                    if(this.helloController!=null) {
                        helloController.setUser(user);
                        helloController.registrationButton.setText(user.getEmail());
                        helloController.inputButton.setText("Выход");
                    }else if(this.basketController!=null){
                        BasketController.user=user;
                        basketController.inputButton.setText("Выход");
                    }else if(this.profileController!=null){
                        ProfileController.user=user;
                        profileController.inputButton.setText("Выход");
                    }

                    Stage stage = (Stage) windowInput.getScene().getWindow();
                    stage.close();
                } else {
                    Error.error("Такого пользователя не существует", windowInput);
                }
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

        ResultSet resultSet = databaseHandler.getAllUser(user);

        while (resultSet.next()) {
            String l = resultSet.getString(4);
            String p = resultSet.getString(5);

            if (resultSet.getString(4).equals(loginText) &&
                    resultSet.getString(5).equals(passwordText)) {
                counter++;
            }

        }

        if (counter == 0) {
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

    public void setBasketController(BasketController _basketController) {
        this.basketController = _basketController;
        basketController.setUser(user);
    }

    public void setProfileController(ProfileController _profileController) {
        this.profileController = _profileController;
        ProfileController.setUser(user);
    }

}

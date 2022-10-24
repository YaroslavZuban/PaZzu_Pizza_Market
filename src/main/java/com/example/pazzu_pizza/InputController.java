package com.example.pazzu_pizza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class InputController {
    @FXML
    private AnchorPane windowInput;
    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    void authorizationActon(ActionEvent event) {
        String loginText=login.getText().trim();
        String passwordText=password.getText().trim();

        if(!loginText.equals("") && !passwordText.equals("")){
            loginUser(loginText,passwordText);
        }else {
            Error.error("Не заполнили некоторые данные.",windowInput);
        }
    }

    private void loginUser(String loginText, String passwordText) {

    }

}

package com.example.pazzu_pizza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RegistrationController {
    @FXML
    private DatePicker data;

    @FXML
    private TextField email;

    @FXML
    private TextField name;

    @FXML
    private TextField password;

    @FXML
    private TextField surname;

    @FXML
    private TextField telephone;

    @FXML
    private AnchorPane windowAuthorization;

    @FXML
    void authorizationActon(ActionEvent event) {
        DatabaseHandler databaseHandler=new DatabaseHandler();

        if (email.getText().equals("") || name.getText().equals("") || password.getText().equals("") ||
                surname.getText().equals("") || telephone.getText().equals("")||String.valueOf(data.getValue()).equals("")){
            Error.error("Не заполненно поле",windowAuthorization);
        }else{
            databaseHandler.singUpUser( name.getText(),surname.getText(),email.getText(),
                    password.getText(), telephone.getText(), String.valueOf(data.getValue()));
        }
    }

}

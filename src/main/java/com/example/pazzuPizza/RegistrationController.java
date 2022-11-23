package com.example.pazzuPizza;

import com.example.pazzuPizza.Error;
import com.example.pazzuPizza.User;
import com.example.pazzuPizza.animations.Movement;
import com.example.pazzuPizza.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationController {
    @FXML
    private DatePicker data;

    @FXML
    private TextField email;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private TextField surname;

    @FXML
    private TextField telephone;

    @FXML
    private AnchorPane windowAuthorization;

    @FXML
    void authorizationActon(ActionEvent event) throws IOException, CloneNotSupportedException, InterruptedException, SQLException {
        if (name.getText().equals("")) {
            Movement.fieldMovement("имя", name, windowAuthorization);
        } else if (surname.getText().equals("")) {
            Movement.fieldMovement("фамилию", surname, windowAuthorization);
        } else if (email.getText().equals("")) {
            Movement.fieldMovement("email", email, windowAuthorization);
        } else if (password.getText().equals("")) {
            Movement.fieldMovement("пароль", password, windowAuthorization);
        } else if (telephone.getText().equals("")) {
            Movement.fieldMovement("телефон", telephone, windowAuthorization);
        } else if (String.valueOf(data.getValue()).equals("")) {
            Movement.fieldMovement("дату", data, windowAuthorization);
        } else {
            signUpNewUser();

            Stage stage = (Stage) windowAuthorization.getScene().getWindow();
            stage.close();
        }
    }

    private void signUpNewUser() throws SQLException {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String name_new = name.getText();
        String surname_new = surname.getText();
        String email_new = email.getText();
        String password_new = password.getText();
        String telephone_new = telephone.getText();
        String births = String.valueOf(data.getValue());

        User user = new User(name_new, surname_new, email_new, password_new, telephone_new, births);

        ResultSet resultSet = databaseHandler.getAllUser(user);

        int count = 0;
        while (resultSet.next()) {
            String login = resultSet.getString(4);


            if (resultSet.getString(4).equals(email_new)) {
                count++;
            }

        }

        if (count == 0) {
            databaseHandler.singUpUser(user);
        } else {
            Error.error("Данный email уже занят!", windowAuthorization);
        }
    }

}

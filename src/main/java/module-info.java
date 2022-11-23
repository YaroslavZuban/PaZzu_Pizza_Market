module com.example.pazzuPizza {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop;


    opens com.example.pazzuPizza to javafx.fxml;
    exports com.example.pazzuPizza;
}
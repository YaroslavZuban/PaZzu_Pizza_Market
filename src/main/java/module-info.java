module com.example.pazzu_pizza {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop;


    opens com.example.pazzu_pizza to javafx.fxml;
    exports com.example.pazzu_pizza;
    exports com.example.pazzu_pizza.model;
    opens com.example.pazzu_pizza.model to javafx.fxml;
}
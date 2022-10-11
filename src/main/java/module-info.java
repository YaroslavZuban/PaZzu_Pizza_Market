module com.example.pazzu_pizza {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pazzu_pizza to javafx.fxml;
    exports com.example.pazzu_pizza;
    exports com.example.pazzu_pizza.model;
    opens com.example.pazzu_pizza.model to javafx.fxml;
}
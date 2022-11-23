package com.example.pazzuPizza;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

   /*     FXMLLoader loader=new FXMLLoader(getClass().getResource("/com/example/pazzuPizza/" +
                HelloController.class.getClass().getCanonicalName().substring(18).replaceAll("Controller", "") +
                ".fxml"));*/


   /*     FXMLLoader loader = new FXMLLoader(HelloApplication.this.getClass().getResource("/fxml/" + HelloController.class.getClass().getCanonicalName().substring(18).replaceAll("Controller", "") + ".fxml"));
         Parent root = (Parent) loader.load();
*/

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("market.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PaZzU Pizza");
        stage.setScene(scene);
        stage.show();

       /* Parent root = FXMLLoader.load(HelloApplication.class.getClass().getResource("market.fxml"));*/
      /*  Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
    }

    public static void main(String[] args) {
        launch();
    }
}
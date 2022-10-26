package com.example.pazzu_pizza.animations;

import com.example.pazzu_pizza.Error;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Movement {
    public static void fieldMovement(String textError, Node node, Pane pane) throws InterruptedException {
        Error.error("Введите " + textError, pane);

        Shake shake = new Shake(node);
        shake.playAnim();
    }
}

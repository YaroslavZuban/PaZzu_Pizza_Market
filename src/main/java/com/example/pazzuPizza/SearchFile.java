package com.example.pazzuPizza;

import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SearchFile {
    public static void search(File file, TextField textField) {//функция поиска f
        FileChooser fileChooser = new FileChooser();//позволяет пользвателю перемещать систему файлов чтоы выбрать один или более файлов
        fileChooser.setTitle("Выбрать фото");//название окна
        Stage stage = (Stage) textField.getScene().getWindow();//вывод окна на экран
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));//какой тип файла мы будем искать

        file = fileChooser.showOpenDialog(stage);//присвоению файла который выбран в окне

        if (file != null) {//если файл не найден, то выводим ошибку
            textField.setText(String.valueOf(file));
        }
    }
}
package com.example.pazzu_pizza;

import com.example.pazzu_pizza.model.Pizza;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private VBox chosenPizzaCard;

    @FXML
    private Label namePizzaLabel;

    @FXML
    public Button inputButton;

    @FXML
    public Button registrationButton;

    @FXML
    private ImageView pizzaImg;

    @FXML
    private Label pricePizza;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    private Button length_25Button;

    @FXML
    private Button length_30Button;

    @FXML
    private Button length_40Button;

    @FXML
    private Button thnButton;

    @FXML
    private Button traditionalButton;

    private String doughType = "Тонкое";
    private String sizePizza = "25 см";

    private List<Pizza> pizza = new ArrayList<>();

    private List<Pizza> basket=new ArrayList<>();
    private Image image;
    private MyListener myListener;
    private User user;

    @FXML
    private AnchorPane window;

    private String path="img/pepperoni.png";
    public List<Pizza> getBasket() {
        return basket;
    }

    private List<Pizza> getData() {
        List<Pizza> pizza = new ArrayList<>();
        Pizza pizz = new Pizza();


        pizz = new Pizza();
        pizz.setName("Пипирони");
        pizz.setPrice(6);
        pizz.setImgSrc("img/pepperoni.png");
        pizza.add(pizz);

        pizz = new Pizza();
        pizz.setName("Маргарита");
        pizz.setPrice(5);
        pizz.setImgSrc("img/Маргарита.png");
        pizza.add(pizz);

        pizz = new Pizza();
        pizz.setName("Барбекю курица");
        pizz.setPrice(7);
        pizz.setImgSrc("img/barbecue chicken (1).png");
        pizza.add(pizz);

        pizz = new Pizza();
        pizz.setName("Сырная");
        pizz.setPrice(5);
        pizz.setImgSrc("img/cheese.png");
        pizza.add(pizz);

        pizz = new Pizza();
        pizz.setName("Бургерная");
        pizz.setPrice(6);
        pizz.setImgSrc("img/cheeseburger pizza.png");
        pizza.add(pizz);

        pizz = new Pizza();
        pizz.setName("Много пипирони");
        pizz.setPrice(9);
        pizz.setImgSrc("img/Crazy pepperoni.png");
        pizza.add(pizz);

        pizz = new Pizza();
        pizz.setName("Кисло-сладкая");
        pizz.setPrice(6);
        pizz.setImgSrc("img/Sweet and sour chicken.png");
        pizza.add(pizz);

        pizz = new Pizza();
        pizz.setName("Пипирони с перцем");
        pizz.setPrice(7);
        pizz.setImgSrc("img/Пепперони Фреш с перцем.png");
        pizza.add(pizz);

        pizz = new Pizza();
        pizz.setName("Четыре сезона");
        pizz.setPrice(6);
        pizz.setImgSrc("img/Четыре сезона.png");
        pizza.add(pizz);


        return pizza;
    }


    public HelloController getThis(){
        return this;
    }

    @FXML
    void basketActon(ActionEvent event) throws IOException {
        Stage ss = (Stage) window.getScene().getWindow();//береться параметры стратого она и закрывается
        ss.close();//закрытия окна

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("basket.fxml"));//считывание дизайн самого интерфейса

        BasketController.helloController=this;

        Stage stage=new Stage();
        Scene scene = new Scene(fxmlLoader.load());//запуск дизайн
        stage.setTitle("PaZzU Pizza");//название окна
     //   stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("bit.png")));//установление иконки
        stage.setScene(scene);//установка Scene для Stage
        stage.setResizable(false);//запрещает пользователю изменять размер окна
        stage.show();//Попытки показать это окно, установив для видимости значение true
    }

    private void setChosenPizza(Pizza pizza) {
        namePizzaLabel.setText(pizza.getName());
        pricePizza.setText(String.valueOf(pizza.getPrice()));
        path=pizza.getImgSrc();
        image = new Image(getClass().getResourceAsStream(pizza.getImgSrc()));
        pizzaImg.setImage(image);
        chosenPizzaCard.setStyle("-fx-background-radius: 30;");
    }

    @FXML
    void addBasketAction(ActionEvent event) {
        Pizza temp=new Pizza(namePizzaLabel.getText(),path,Double.parseDouble(pricePizza.getText()),
                sizePizza,doughType);

        basket.add(temp);
    }


    /***
     * Функция которая созддает пиццы в окнах
     * Доработать нужно, чтобы пицца считывалась с базы данных
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pizza.addAll(getData());

        if (pizza.size() > 0) {
            setChosenPizza(pizza.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Pizza pizza) {
                    setChosenPizza(pizza);
                }
            };
        }

        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < pizza.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("Item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(pizza.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void length_25(ActionEvent event) {
        length_25Button.setStyle("-fx-background-color: #FFFFFF;");
        length_30Button.setStyle(" -fx-background-color: #B1B5B6;");
        length_40Button.setStyle(" -fx-background-color: #B1B5B6;");
        sizePizza = "25 см";
    }

    @FXML
    void length_30(ActionEvent event) {
        length_25Button.setStyle("-fx-background-color: #B1B5B6;");
        length_30Button.setStyle(" -fx-background-color: #FFFFFF;");
        length_40Button.setStyle(" -fx-background-color: #B1B5B6;");
        sizePizza = "30 см";
    }

    @FXML
    void length_40(ActionEvent event) {
        length_25Button.setStyle("-fx-background-color: #B1B5B6;");
        length_30Button.setStyle(" -fx-background-color: #B1B5B6;");
        length_40Button.setStyle(" -fx-background-color: #FFFFFF;");
        sizePizza = "40 см";
    }

    @FXML
    void thinAction(ActionEvent event) {
        thnButton.setStyle("-fx-background-color: #FFFFFF;");
        traditionalButton.setStyle("-fx-background-color: #B1B5B6;");
        doughType = "Тонкое";
    }

    @FXML
    void traditionalAction(ActionEvent event) {
        thnButton.setStyle("-fx-background-color: #B1B5B6;");
        traditionalButton.setStyle("-fx-background-color: #FFFFFF;");
        doughType = "Традиционное";
    }


    @FXML
    void inputAction(ActionEvent event) throws IOException {
        if(inputButton.getText().equals("Выход")){
            user=null;
            inputButton.setText("Вход");
            registrationButton.setText("Регистрация");
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("input.fxml"));

            Stage stage = new Stage();//создается stage для запуска нового окна
            Scene scene = new Scene(loader.load());//загружается дизайн с fxml

            stage.setTitle("PaZzu-Pizza");//название нового окна
            // stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("bit.png")));//значок нового окна
            stage.setScene(scene);//установка Scene для Stage
            stage.setResizable(false);//запрещает пользователю изменять размер окна
            stage.show();//Попытки показать это окно, установив для видимости значение true

            InputController inputController = loader.getController();
            inputController.setMainController(this);
        }
    }

    @FXML
    void registrationAction(ActionEvent event) throws IOException {
        if(registrationButton.getText().equals("Регистрация")){
            windowWord("registration.fxml");
        }else if(registrationButton.getText().equals("admin")){

        }else{

        }

    }

    private void windowWord(String fxml) throws IOException {
      /*  Stage ss = (Stage) window.getScene().getWindow();//береться параметры стратого она и закрывается
        ss.close();//закрытия окна
*/
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxml));//берется fxml ф

        Stage stage = new Stage();//создается stage для запуска нового окна
        Scene scene = new Scene(loader.load());//загружается дизайн с fxml

        stage.setTitle("PaZzu-Pizza");//название нового окна
        // stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("bit.png")));//значок нового окна
        stage.setScene(scene);//установка Scene для Stage
        stage.setResizable(false);//запрещает пользователю изменять размер окна
        stage.show();//Попытки показать это окно, установив для видимости значение true
    }

    public void setUser(User user) {
        this.user = user;
    }
}

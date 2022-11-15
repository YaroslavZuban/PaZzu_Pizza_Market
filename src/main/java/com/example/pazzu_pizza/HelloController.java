package com.example.pazzu_pizza;

import com.example.pazzu_pizza.model.Pizza;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public static User user;
    public static List<PizzaBasket> basket = new ArrayList<>();

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

    @FXML
    private TextField pizzaSearchText;

    private static Boolean isLive = true;


    private String doughType = "Тонкое";
    private String sizePizza = "25";

    private BasketController basketController = new BasketController();

    private List<Pizza> pizza = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    private Pizza mainPizza;
    private static List<Pizza> pizza_copy;


    @FXML
    private AnchorPane window;

    @FXML
    private Button search;

    private String path = "img/pepperoni.png";
    private ProfileController profileController;

    public List<PizzaBasket> getBasket() {
        return basket;
    }

    public User getUser() {
        return user;
    }

    @FXML
    private void searchPizzaAction(ActionEvent event) throws IOException {
        if (pizzaSearchText.getText().equals("")) {
            pizza = pizza_copy;
        } else {
            pizza = new ArrayList<>();

            for (int i = 0; i < pizza_copy.size(); i++) {
                if (pizza_copy.get(i).getName().toLowerCase().contains(pizzaSearchText.getText())) {
                    pizza.add(pizza_copy.get(i));
                }
            }
        }

        int column = 0;
        int row = 0;

        grid = new GridPane();

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
    }

    private List<Pizza> getData() throws SQLException, IOException {
        DatabasePizza data = new DatabasePizza();

        List<Pizza> pizza = new ArrayList<>();
        Pizza pizz = new Pizza();

        ResultSet resultSet = data.getPizza(pizz);

        while (resultSet.next()) {
            String name = resultSet.getString(2);

            int thin_25 = resultSet.getInt(3);
            int thin_30 = resultSet.getInt(4);
            int thin_40 = resultSet.getInt(5);

            int thick_25 = resultSet.getInt(6);
            int thick_30 = resultSet.getInt(7);
            int thick_40 = resultSet.getInt(8);

            Blob blob = resultSet.getBlob(9);

            BufferedImage image = ImageIO.read(blob.getBinaryStream());
            String pathImage = name + ".png";

            File outputImage = new File(pathImage);
            ImageIO.write(image, "png", outputImage);

            pizz = new Pizza(name, String.valueOf(thin_25), String.valueOf(thin_30), String.valueOf(thin_40), String.valueOf(thick_25),
                    String.valueOf(thick_30), String.valueOf(thick_40), pathImage);

            pizza.add(pizz);
        }

        return pizza;
    }


    public HelloController getThis() {
        return this;
    }


    private void setChosenPizza(Pizza pizza) {
        mainPizza = pizza;
        namePizzaLabel.setText(pizza.getName());
        path = pizza.getImgSrc();
        image = new Image("file:" + path);
        pricePizza.setText(pizza.getThin_25());
        pizzaImg.setImage(image);
        chosenPizzaCard.setStyle("-fx-background-radius: 30;");
    }

    @FXML
    void addBasketAction(ActionEvent event) {
        Boolean isDuplicate = true;

        for (int i = 0; i < basket.size(); i++) {
            String namePizza = basket.get(i).getName();
            String sizePizzaBasket = String.valueOf(basket.get(i).getSizePizza());
            String typePizza = basket.get(i).getDoughType();

            if (namePizzaLabel.getText().equals(namePizza) &&
                    sizePizza.equals(sizePizzaBasket) &&
                    doughType.equals(typePizza)) {
                basket.get(i).setCounter(basket.get(i).getCounter() + 1);
                isDuplicate = false;
                break;
            }
        }

        if (isDuplicate) {
            PizzaBasket pizzaBasket = new PizzaBasket(namePizzaLabel.getText(),
                    Integer.parseInt(sizePizza), doughType, Integer.parseInt(pricePizza.getText()),
                    path);
            basket.add(pizzaBasket);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (user != null) {
            registrationButton.setText(user.getEmail());
            inputButton.setText("Выход");
        }

        try {
            pizza.addAll(getData());
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

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

        pizza_copy = new ArrayList<>(pizza);
    }

    @FXML
    void length_25(ActionEvent event) {
        length_25Button.setStyle("-fx-background-color: #FFFFFF;");
        length_30Button.setStyle(" -fx-background-color: #B1B5B6;");
        length_40Button.setStyle(" -fx-background-color: #B1B5B6;");
        sizePizza = "25";

        if (doughType.equals("Тонкое")) {
            pricePizza.setText(mainPizza.getThin_25());
        } else {
            pricePizza.setText(mainPizza.getThick_25());
        }
    }

    @FXML
    void length_30(ActionEvent event) {
        length_25Button.setStyle("-fx-background-color: #B1B5B6;");
        length_30Button.setStyle(" -fx-background-color: #FFFFFF;");
        length_40Button.setStyle(" -fx-background-color: #B1B5B6;");
        sizePizza = "30";

        if (doughType.equals("Тонкое")) {
            pricePizza.setText(mainPizza.getThin_30());
        } else {
            pricePizza.setText(mainPizza.getThick_30());
        }
    }

    @FXML
    void length_40(ActionEvent event) {
        length_25Button.setStyle("-fx-background-color: #B1B5B6;");
        length_30Button.setStyle(" -fx-background-color: #B1B5B6;");
        length_40Button.setStyle(" -fx-background-color: #FFFFFF;");
        sizePizza = "40";

        if (doughType.equals("Тонкое")) {
            pricePizza.setText(mainPizza.getThin_40());
        } else {
            pricePizza.setText(mainPizza.getThick_40());
        }
    }

    @FXML
    void thinAction(ActionEvent event) {
        thnButton.setStyle("-fx-background-color: #FFFFFF;");
        traditionalButton.setStyle("-fx-background-color: #B1B5B6;");
        doughType = "Тонкое";

        if (sizePizza.equals("25")) {
            pricePizza.setText(mainPizza.getThin_25());
        } else if (sizePizza.equals("30")) {
            pricePizza.setText(mainPizza.getThin_30());
        } else {
            pricePizza.setText(mainPizza.getThin_40());
        }
    }

    @FXML
    void traditionalAction(ActionEvent event) {
        thnButton.setStyle("-fx-background-color: #B1B5B6;");
        traditionalButton.setStyle("-fx-background-color: #FFFFFF;");
        doughType = "Традиционное";

        if (sizePizza.equals("25")) {
            pricePizza.setText(mainPizza.getThick_25());
        } else if (sizePizza.equals("30")) {
            pricePizza.setText(mainPizza.getThick_30());
        } else {
            pricePizza.setText(mainPizza.getThick_40());
        }
    }


    @FXML
    void inputAction(ActionEvent event) throws IOException {
        if (inputButton.getText().equals("Выход")) {
            user = null;
            inputButton.setText("Вход");
            registrationButton.setText("Регистрация");
        } else {
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
        System.out.println(registrationButton.getText());

        if (registrationButton.getText().equals("Регистрация")) {
            windowWord("registration.fxml", false);
        } else if (registrationButton.getText().equals("admin")) {
            AdminController.user = user;
            AdminController.basket = basket;
            windowWord("admin.fxml", true);
        } else {
            ProfileController.user = user;
            ProfileController.basket = basket;
            windowWord("profile.fxml", true);

        }
    }

    @FXML
    void basketActon(ActionEvent event) throws IOException {
        Stage ss = (Stage) window.getScene().getWindow();//береться параметры стратого она и закрывается
        ss.close();//закрытия окна

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("basket.fxml"));//считывание дизайн самого интерфейса

        BasketController.helloController = this;

        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());//запуск дизайн
        stage.setTitle("PaZzU Pizza");//название окна
        //   stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("bit.png")));//установление иконки
        stage.setScene(scene);//установка Scene для Stage
        stage.setResizable(false);//запрещает пользователю изменять размер окна
        stage.show();//Попытки показать это окно, установив для видимости значение true
    }

    private void windowWord(String fxml, boolean isNecessary) throws IOException {
        if (isNecessary) {
            Stage ss = (Stage) window.getScene().getWindow();//береться параметры стратого она и закрывается
            ss.close();//закрытия окна
        }

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

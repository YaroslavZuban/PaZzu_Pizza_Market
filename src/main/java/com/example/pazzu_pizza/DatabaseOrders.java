package com.example.pazzu_pizza;

import com.example.pazzu_pizza.model.Pizza;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.List;

public class DatabaseOrders extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHOST + ":" + dbPORT + "/" + dbNAME;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUSERS, dbPASS);

        return dbConnection;
    }

    public void singOrder(User user, PizzaBasket pizza, String address, String comment) {
        String insert = "INSERT INTO " + ConstOrders.ORDERS_TABLE + "(" + ConstOrders.ORDERS_NAME + "," +
                ConstOrders.ORDERS_ADDRESS + "," + ConstOrders.ORDERS_PHONE + "," + ConstOrders.ORDERS_COMMENT + "," +
                ConstOrders.ORDERS_NAME_PIZZA + "," + ConstOrders.ORDERS_TEST_TYPE + "," + ConstOrders.ORDERS_PIZZA_SIZE + ","
                + ConstOrders.ORDERS_AMOUNT + "," + ConstOrders.ORDERS_PRICE + ")" + "VALUES(?,?,?,?,?,?,?,?,?)";

        PreparedStatement prSt = null;

        try {
            prSt = getPreparedStatement(user, pizza, address, comment, insert);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    public ResultSet getOrder(){
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + ConstOrders.ORDERS_TABLE;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resultSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }

    private PreparedStatement getPreparedStatement(User user, PizzaBasket pizza, String address, String comment, String select) throws SQLException, ClassNotFoundException, IOException {

        PreparedStatement prSt;
        prSt = getDbConnection().prepareStatement(select);

        prSt.setString(1, user.getName());
        prSt.setString(2, address);
        prSt.setString(3, user.getTelephone());
        prSt.setString(4, comment);
        prSt.setString(5, pizza.getName());
        prSt.setString(6, pizza.getDoughType());
        prSt.setString(7, String.valueOf(pizza.getSizePizza()));
        prSt.setString(8, String.valueOf(pizza.getCounter()));
        prSt.setString(9, String.valueOf(pizza.getCounter()*pizza.getPrice()));

        return prSt;
    }
}

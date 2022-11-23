package com.example.pazzuPizza;

import java.io.IOException;
import java.sql.*;

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

    public void deleteOrder(User user) {
        ResultSet resultSet = null;

        String delete = "DELETE FROM " + ConstOrders.ORDERS_TABLE + " WHERE name=? AND telephone=?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = getOrdersDelete(user,delete);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public ResultSet getOrder() {
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

    private PreparedStatement getOrdersDelete(User user, String delete) throws SQLException, ClassNotFoundException {
        PreparedStatement prSt;
        prSt = getDbConnection().prepareStatement(delete);

        prSt.setString(1,user.getName());
        prSt.setString(2,user.getTelephone());

        return prSt;
    }

    private PreparedStatement getPreparedStatement(User user, PizzaBasket pizza, String address,
                                                   String comment, String select) throws SQLException, ClassNotFoundException, IOException {

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
        prSt.setString(9, String.valueOf(pizza.getCounter() * pizza.getPrice()));

        return prSt;
    }
}

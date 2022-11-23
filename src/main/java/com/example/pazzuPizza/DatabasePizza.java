package com.example.pazzuPizza;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;

public class DatabasePizza extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHOST + ":" + dbPORT + "/" + dbNAME;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUSERS, dbPASS);

        return dbConnection;
    }


    public void singUpPizza(Pizza pizza) throws IOException, SQLException {

        String insert = "INSERT INTO " + ConstPizza.PIZZA_TABLE + "(" + ConstPizza.PIZZA_NAME + "," +
                ConstPizza.PIZZA_THIN_25 + "," + ConstPizza.PIZZA_THIN_30 + "," + ConstPizza.PIZZA_THIN_40 + "," +
                ConstPizza.PIZZA_THICK_25 + "," + ConstPizza.PIZZA_THICK_30 + "," + ConstPizza.PIZZA_THICK_40 + ","
                + ConstPizza.PIZZA_IMG + ")" + "VALUES(?,?,?,?,?,?,?,?)";


        PreparedStatement prSt = null;
        try {
            prSt = getPreparedStatement(pizza, insert);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public ResultSet getPizza(Pizza pizza) {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + ConstPizza.PIZZA_TABLE /*+ " WHERE " +
                ConstPizza.PIZZA_NAME + "=? AND " + ConstPizza.PIZZA_THIN_25 + "=? AND " +
                ConstPizza.PIZZA_THIN_30 + "=? AND " + ConstPizza.PIZZA_THIN_40 + "=? AND " +
                ConstPizza.PIZZA_THICK_25 + "=? AND " + ConstPizza.PIZZA_THICK_30 + "=? AND " +
                ConstPizza.PIZZA_THICK_40 + "=? AND " + ConstPizza.PIZZA_IMG + "=?"*/;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resultSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }


    private PreparedStatement getPreparedStatement(Pizza pizza, String select) throws SQLException, ClassNotFoundException, IOException {

        PreparedStatement prSt;
        prSt = getDbConnection().prepareStatement(select);

        prSt.setString(1, pizza.getName());

        prSt.setString(2, pizza.getThin_25());
        prSt.setString(3, pizza.getThin_30());
        prSt.setString(4, pizza.getThin_40());

        prSt.setString(5, pizza.getThick_25());
        prSt.setString(6, pizza.getThick_30());
        prSt.setString(7, pizza.getThick_40());

        BufferedImage image= ImageIO.read(new File(pizza.getImgSrc()));

        Blob blob=dbConnection.createBlob();
        OutputStream outputStream=blob.setBinaryStream(1);
        ImageIO.write(image,"png",outputStream);
        outputStream.close();

        prSt.setBlob(8, blob);
        return prSt;
    }
}

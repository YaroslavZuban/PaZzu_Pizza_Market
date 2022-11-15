package com.example.pazzu_pizza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHOST + ":" + dbPORT + "/" + dbNAME;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUSERS, dbPASS);

        return dbConnection;
    }

    public void singUpUser(User user) {
        String insert = "INSERT INTO " + ConstUser.USER_TABLE + "(" + ConstUser.USERS_NAME + "," +
                ConstUser.USERS_SURNAME + "," + ConstUser.USERS_EMAIL + "," + ConstUser.USERS_PASSWORD + "," +
                ConstUser.USERS_TELEPHONE + "," + ConstUser.USERS_BIRTHS + ")" +
                "VALUES(?,?,?,?,?,?)";

        PreparedStatement prSt = null;
        try {
            prSt = getPreparedStatement(user, insert);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public ResultSet existenceTest(User user) {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + ConstUser.USER_TABLE + " WHERE " +
                ConstUser.USERS_EMAIL + "=?";


        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            ;
            prSt.setString(1, user.getEmail());

            resultSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }

    public ResultSet getAllUser(User user) {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + ConstUser.USER_TABLE + " WHERE " +
                ConstUser.USERS_EMAIL + "=? AND " + ConstUser.USERS_PASSWORD + "=?";


        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            ;
            prSt.setString(1, user.getEmail());
            prSt.setString(2, user.getPassword());

            resultSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }


    private PreparedStatement getPreparedStatement(User user, String select) throws SQLException, ClassNotFoundException {
        PreparedStatement prSt;
        prSt = getDbConnection().prepareStatement(select);

        prSt.setString(1, user.getName());
        prSt.setString(2, user.getSurname());
        prSt.setString(3, user.getEmail());
        prSt.setString(4, user.getPassword());
        prSt.setString(5, user.getTelephone());
        prSt.setString(6, user.getBirth());
        return prSt;
    }
}

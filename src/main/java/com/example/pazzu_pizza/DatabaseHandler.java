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
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_NAME + "," +
                Const.USERS_SURNAME + "," + Const.USERS_EMAIL + "," + Const.USERS_PASSWORD + "," +
                Const.USERS_TELEPHONE + "," + Const.USERS_BIRTHS + ")" +
                "VALUES(?,?,?,?,?,?)";

        PreparedStatement prSt = null;
        try {
            prSt = getPreparedStatement(user, insert);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public ResultSet getUser(User user) {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USERS_NAME + "=? AND " + Const.USERS_SURNAME + "=? AND " +
                Const.USERS_EMAIL + "=? AND " + Const.USERS_PASSWORD + "=? AND " +
                Const.USERS_TELEPHONE + "=? AND " + Const.USERS_BIRTHS + "=?";

        PreparedStatement prSt = null;

        try {
            prSt = getPreparedStatement(user, select);

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

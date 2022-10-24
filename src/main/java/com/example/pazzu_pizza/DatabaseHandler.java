package com.example.pazzu_pizza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql//" + dbHOST + ":" + dbPORT + "/" + dbNAME;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUSERS, dbPASS);

        return dbConnection;
    }

    public void singUpUser(String name, String surname, String email,
                           String password, String telephone, String data) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_NAME + "," +
                Const.USERS_SURNAME + "," + Const.USERS_EMAIL + "," + Const.USERS_PASSWORD + "," +
                Const.USERS_TELEPHONE + "," + Const.USERS_BIRTHS + ")" +
                "VALUES(?,?,?,?,?,?)";

        PreparedStatement prSt= null;
        try {
            prSt = getDbConnection().prepareStatement(insert);

            prSt.setString(1,name);
            prSt.setString(2,surname);
            prSt.setString(3,email);
            prSt.setString(4,password);
            prSt.setString(5,telephone);
            prSt.setString(6,data);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}

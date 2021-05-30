package org.coursemed.tools;

import java.sql.*;

// Class for general database functions
public class DbTools {
    private static Connection connection;

    public static void startConnection(String connectionString) {
        try {
            connection = DriverManager.getConnection(connectionString);

            executeQuery("PRAGMA foreign_keys = ON;");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void stopConnection() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static boolean executeQuery(String query) {
        try (Statement statement = connection.createStatement()) {

            statement.execute(query);

            statement.close();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public static Statement createStatement() {
        try {
            return connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public static PreparedStatement prepareStatement(String query) {
        try {
            return connection.prepareStatement(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public static DatabaseMetaData getMetaData() {
        try {
            return connection.getMetaData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public static boolean doesTableExist(String tableName) {
        try {
            ResultSet resultSet = connection.getMetaData().getTables(null, null, tableName, new String[]{"TABLE"});

            boolean exists = resultSet.next();

            resultSet.close();

            return exists;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public static int getTableSize(String tableName) {
        int size = 0;

        ResultSet resultSet;

        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery("SELECT * FROM " + tableName);

            while (resultSet.next()) {
                size++;
            }

            return size;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return -1;
    }

    public static boolean isTableEmpty(String tableName) {
        return getTableSize(tableName) == 0;
    }
}

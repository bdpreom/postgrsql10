package com.monitor;


import java.sql.DriverManager;
import java.sql.SQLException;

import static com.monitor.ConnectionSettings.connectionSettings;
import static com.monitor.LocationSettings.fileLocations;
import static com.monitor.MemorySettings.memorySettings;

public class Main {

    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_CONNECTION = "jdbc:postgresql://192.168.4.176/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "tigerit";

    public static java.sql.Connection getDBConnection() {

        java.sql.Connection dbConnection = null;

        try {

            Class.forName(DB_DRIVER);

        } catch (ClassNotFoundException e) {

            System.out.println(e.getMessage());

        }

        try {

            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);
            return dbConnection;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return dbConnection;

    }


    public static void main(String[] argv) {

        try {
            System.out.println("---------------------file locations----------------------------------");
            fileLocations();
            System.out.println("---------------------connection settings----------------------------------");
            connectionSettings();
            System.out.println("---------------------memory settings----------------------------------");
            memorySettings();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

}

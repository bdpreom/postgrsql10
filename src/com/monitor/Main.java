package com.monitor;


import java.sql.DriverManager;
import java.sql.SQLException;

import static com.monitor.ConnectionSettings.connectionSettings;
import static com.monitor.LocationSettings.fileLocations;
import static com.monitor.MemorySettings.memorySettings;
import static com.monitor.PlannerSettings.plannerSettings;
import static com.monitor.activeConn.activeConn;
import static com.monitor.connLock.connLock;
import static com.monitor.transacAge.transacAge;
import static com.monitor.indexUsed.indexUsed;
import static com.monitor.crudStatTable.crudStatTable;
import static com.monitor.tableIO.tableIO;
import static com.monitor.tableindexStat.tableindexStat;



public class Main {

    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_CONNECTION = "jdbc:postgresql://192.168.4.176/dcrc";
    private static final String DB_USER = "dcrc";
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
//            System.out.println("---------------------file locations----------------------------------");
//            fileLocations();
//            System.out.println("---------------------connection settings----------------------------------");
//            connectionSettings();
//            System.out.println("---------------------memory settings----------------------------------");
////            memorySettings();
//            System.out.println("---------------------planner settings----------------------------------");
//            plannerSettings();
//            System.out.println("---------------------active connection----------------------------------");
//            activeConn();
//            connLock();
//              transacAge();
//            System.out.println("----index being used -------------");
              indexUsed();
               crudStatTable();
               tableIO();
               System.out.println("--------------------------------table/row/index/column size ------------------------------------");

               tableindexStat();
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

}

package com.monitor;


import java.sql.DriverManager;
import java.sql.SQLException;

import static com.monitor.BloatDetail.bloatDetail;
import static com.monitor.ConnectionSettings.connectionSettings;
import static com.monitor.DatabaseConflicts.databaseConflicts;
import static com.monitor.IndexHitRate.indexhitRate;
import static com.monitor.locationSetting.fileLocations;
import static com.monitor.LockStat.lockStat;
import static com.monitor.MasterActivity.masterActivity;
import static com.monitor.MemorySettings.memorySettings;
import static com.monitor.Oid.oid;
import static com.monitor.PlannerSettings.plannerSettings;
import static com.monitor.Replication.replication;
import static com.monitor.Subscription.subscription;
import static com.monitor.TableStat.tableStat;
import static com.monitor.TransactionRate.transactionRate;
import static com.monitor.VaccumeTime.vaccumeTime;
import static com.monitor.WaitingQuery.waitingQuery;
import static com.monitor.activeConn.activeConn;
import static com.monitor.connLock.connLock;
import static com.monitor.transacAge.transacAge;
import static com.monitor.indexUsed.indexUsed;
import static com.monitor.crudStatTable.crudStatTable;
import static com.monitor.tableIO.tableIO;
import static com.monitor.tableindexStat.tableindexStat;



public class Main {

    // db connection settings

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
           System.out.println("---------------------file locations----------------------------------"); fileLocations();
           System.out.println("---------------------connection settings----------------------------------"); connectionSettings();
           System.out.println("---------------------memory settings----------------------------------");memorySettings();
           System.out.println("---------------------planner settings----------------------------------");plannerSettings();
           System.out.println("---------------------active connection----------------------------------"); activeConn();
           System.out.println("------------connection lock------------------");connLock();
           System.out.println("transactionAge"); transacAge();
           System.out.println("----index being used -------------"); indexUsed();
           System.out.println("crud stat");crudStatTable();
           System.out.println("table io");tableIO();
           System.out.println("--------------------------------table/row/index/column size ------------------------------------");tableindexStat();
            System.out.println("-------oid----------");oid();
            System.out.println("------------------------transaction rate---------");transactionRate();
            System.out.println("----------vaccume history-------------");vaccumeTime();
            System.out.println("-----------------------lock statistics-----------");lockStat();
            System.out.println("master activity");masterActivity();
            System.out.println("-----------------waiting info on query-------------");waitingQuery();
            System.out.println(" table stat");tableStat();
            System.out.println("database conflicst");databaseConflicts();
            System.out.println("subscription");subscription();
            System.out.println("replication");replication();
            System.out.println("index hit rate");indexhitRate();
            System.out.println("bloat detail");bloatDetail();





        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

}

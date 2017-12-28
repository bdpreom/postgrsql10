package com.monitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class TransactionRate {

    public static void transactionRate() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String transactionRateSQL = "SELECT current_database(), datname, now(), xact_commit + xact_rollback, xact_rollback \n" +
                "    FROM pg_stat_database WHERE datname = current_database();";



        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(transactionRateSQL);
            // execute select SQL stetement
            ResultSet rs7 = statement.executeQuery(transactionRateSQL);
            System.out.println(rs7);





            while (rs7.next()) {

                String current_database  = rs7.getString("current_database");
                String datname = rs7.getString("datname");
                String now = rs7.getString("now");
                String column = rs7.getString("?column?");
                String xact_rollback = rs7.getString("xact_rollback");




                System.out.println("current_database: " +current_database);
                System.out.println("datname: "  +datname);
                System.out.println("now: " +now);
                System.out.println("column:" +column);
                System.out.println("xact_rollback:" +xact_rollback);



            }


        }


        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        finally {

            if (statement != null) {
                statement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }




    }
}

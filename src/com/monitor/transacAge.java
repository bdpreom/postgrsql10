package com.monitor;

import jdk.nashorn.internal.ir.ObjectNode;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class transacAge {

    public static void transacAge() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String transacAgeSQL = "SELECT max(now() - xact_start) FROM pg_stat_activity\n" +
                "WHERE state IN ('idle in transaction', 'active');";



        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println( transacAgeSQL);
            // execute select SQL stetement
            ResultSet rs1 = statement.executeQuery( transacAgeSQL);





            while (rs1.next()) {

                String max = rs1.getString("max");

                System.out.println("max transaction age : " + max);


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

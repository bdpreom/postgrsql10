package com.monitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class connLock {


    public static void connLock() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String connLockSQL = "SELECT count(distinct pid) FROM pg_locks WHERE granted = false;;";



        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(connLockSQL);
            // execute select SQL stetement
            ResultSet rs1 = statement.executeQuery(connLockSQL);





            while (rs1.next()) {

                String count = rs1.getString("count");

                System.out.println("count : " + count);


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

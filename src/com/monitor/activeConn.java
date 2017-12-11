package com.monitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class activeConn {

    public static void activeConn() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String activeConnSQL = "SELECT state, count(*) FROM pg_stat_activity GROUP BY state;";



        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(activeConnSQL);
            // execute select SQL stetement
            ResultSet rs1 = statement.executeQuery(activeConnSQL);





            while (rs1.next()) {

                String state = rs1.getString("state");
                String count = rs1.getString("count");




                System.out.println("state : " + state );
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

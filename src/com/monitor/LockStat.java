package com.monitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class LockStat {
    public static void lockStat() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String lockStatSQL = "SELECT locktype, granted, count(*) FROM pg_locks GROUP BY locktype,\n" +
                " granted;";



        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(lockStatSQL);
            // execute select SQL stetement
            ResultSet rs7 = statement.executeQuery(lockStatSQL);
            System.out.println(rs7);




            while (rs7.next()) {

                String locktype  = rs7.getString("locktype");
                String granted = rs7.getString("granted");
                String count = rs7.getString("count");





                System.out.println("locktype: " +locktype);
                System.out.println("granted: "  +granted);
                System.out.println("count: " +count);



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

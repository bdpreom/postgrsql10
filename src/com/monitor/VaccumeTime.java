package com.monitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class VaccumeTime {
    public static void vaccumeTime() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String vaccumeTimeSQL = "SELECT current_database(), relname, now(), last_vacuum, last_autovacuum\n" +
                "           FROM pg_stat_user_tables;";



        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(vaccumeTimeSQL);
            // execute select SQL stetement
            ResultSet rs7 = statement.executeQuery(vaccumeTimeSQL);
            System.out.println(rs7);




            while (rs7.next()) {

                String current_database  = rs7.getString("current_database");
                String relname = rs7.getString("relname");
                String now = rs7.getString("now");
                String last_vacuum = rs7.getString("last_vacuum");
                String last_autovacuum = rs7.getString("last_autovacuum");




                System.out.println("current_database: " +current_database);
                System.out.println("relname: "  +relname);
                System.out.println("now: " +now);
                System.out.println("last_vacuum:" +last_vacuum);
                System.out.println("last_autovacuum:" +last_autovacuum);



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



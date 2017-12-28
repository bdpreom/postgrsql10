package com.monitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class WaitingQuery {

    public static void waitingQuery() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String waitingQuerySQL = "SELECT pid,datname, wait_event_type, wait_event,query FROM pg_stat_activity WHERE wait_event is NOT NULL LIMIT 50;\n";



        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(waitingQuerySQL);
            // execute select SQL stetement
            ResultSet rs7 = statement.executeQuery(waitingQuerySQL);
            System.out.println(rs7);




            while (rs7.next()) {

                String pid  = rs7.getString("pid");
                String datname = rs7.getString("datname");
                String wait_event_type = rs7.getString("wait_event_type");
                String wait_event = rs7.getString("wait_event");
                String query = rs7.getString("query");







                System.out.println("pid: " +pid);
                System.out.println("datname: "  +datname);
                System.out.println("wait_event_type: " +wait_event_type);
                System.out.println(" wait_event:" +wait_event);
                System.out.println("query:"  +query);



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

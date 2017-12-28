package com.monitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class MasterActivity {
    public static void masterActivity() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String MasterActivitySQL = "SELECT datname,usename,application_name,client_addr,client_port,backend_start,query_start,state,query from pg_stat_activity;";



        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(MasterActivitySQL);
            // execute select SQL stetement
            ResultSet rs1 = statement.executeQuery(MasterActivitySQL);





            while (rs1.next()) {

                String datname = rs1.getString("datname");
                String usename = rs1.getString("usename");
                String application_name = rs1.getString("application_name");
                String client_addr = rs1.getString("client_addr");
                String client_port = rs1.getString("client_port");
                String backend_start = rs1.getString("backend_start");
                String query_start = rs1.getString("query_start");
                String state = rs1.getString("state");
                String query = rs1.getString("query");
                String wait_event = rs1.getString("wait_event");
                String wait_event_type = rs1.getString("wait_event_type");



                System.out.println("database name : " + datname);
                System.out.println("username :" +usename);
                System.out.println("application name: " +application_name );
                System.out.println("client address: " +client_addr);
                System.out.println("backend start :" +backend_start);
                System.out.println("query start:" +query_start);
                System.out.println("state :" +state);
                System.out.println("port :" +client_port);
                System.out.println("query detail:" +query);
                System.out.println("wait event:" +wait_event);
                System.out.println("wait event type :" +wait_event_type);



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

package com.monitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class Replication {

    public static void replication() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String replicationSQL = "select usename,application_name,client_addr,client_hostname,client_port,backend_start,sync_priority,sync_state from pg_stat_replication;\n";



        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(replicationSQL);
            // execute select SQL stetement
            ResultSet rs1 = statement.executeQuery(replicationSQL);





            while (rs1.next()) {

                String usename = rs1.getString("max");
                String application_name = rs1.getString("max");
                String client_addr = rs1.getString("max");
                String client_hostname = rs1.getString("max");
                String client_port = rs1.getString("max");
                String backend_start = rs1.getString("max");


                System.out.println("usename  : " + usename);
                System.out.println("application_name :" +application_name);
                System.out.println("client_addr :" +client_addr);
                System.out.println("client_hostname :" +client_hostname);
                System.out.println("client_port :" +client_port);
                System.out.println("backend_start :" +backend_start);



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

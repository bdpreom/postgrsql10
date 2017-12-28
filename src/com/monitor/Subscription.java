package com.monitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class Subscription {
    public static void subscription() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String subscriptionSQL = "select pid,subname,received_lsn,last_msg_send_time,last_msg_receipt_time from pg_stat_subscription;\n";



        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(subscriptionSQL);
            // execute select SQL stetement
            ResultSet rs1 = statement.executeQuery(subscriptionSQL);





            while (rs1.next()) {

                String pid = rs1.getString("pid");
                String subname = rs1.getString("subname");
                String received_lsn = rs1.getString("received_lsn");
                String last_msg_send_time = rs1.getString("last_msg_send_time");
                String last_msg_receipt_time = rs1.getString("last_msg_receipt_time");



                System.out.println(" pid : " + pid);
                System.out.println(" subname :" +subname);
                System.out.println("received_lsn :" +received_lsn);
                System.out.println("last_msg_send_time :" +last_msg_send_time);
                System.out.println("last_msg_receipt_time :" +last_msg_receipt_time);


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

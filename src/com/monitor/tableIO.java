package com.monitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class tableIO {

    public static void tableIO() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String tableIOSQL = "SELECT relname,cast(heap_blks_hit as numeric) / (heap_blks_hit + heap_blks_read) AS hit_pct,\n" +
                "\n" +
                "heap_blks_hit,heap_blks_read\n" +
                "\n" +
                "FROM pg_statio_user_tables WHERE (heap_blks_hit + heap_blks_read)>0 ORDER BY hit_pct;";



        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println( tableIOSQL);
            // execute select SQL stetement
            ResultSet rs1 = statement.executeQuery(tableIOSQL);





            while (rs1.next()) {

                String relname = rs1.getString("relname");
                String hit_pct = rs1.getString("hit_pct");
                String heap_blks_hit = rs1.getString("heap_blks_hit");
                String heap_blks_read = rs1.getString("heap_blks_read");

                System.out.println("relname :" +relname);
                System.out.println("hit_pct :" +hit_pct);
                System.out.println("heap_blks_hit :" +heap_blks_hit);
                System.out.println("heap_blks_read:"+heap_blks_read);



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

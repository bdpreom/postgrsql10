package com.monitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class TableStat {
    public static void tableStat() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String tableStatSQL = "select schemaname,relname,heap_blks_read,heap_blks_hit,idx_blks_read,idx_blks_hit from pg_statio_all_tables;\n";



        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println( tableStatSQL);
            // execute select SQL stetement
            ResultSet rs1 = statement.executeQuery(tableStatSQL);





            while (rs1.next()) {

                String schemaname = rs1.getString("schemaname");
                String relname = rs1.getString("relname");
                String heap_blks_read = rs1.getString("heap_blks_read");
                String heap_blks_hit = rs1.getString("heap_blks_hit");
                String idx_blks_read = rs1.getString("idx_blks_read");
                String idx_blks_hit = rs1.getString("idx_blks_hit");



                System.out.println("schemaname : " +schemaname);
                System.out.println("relname:" +relname);
                System.out.println("heap_blks_read:" +heap_blks_read);
                System.out.println("heap_blks_hit:" +heap_blks_hit);
                System.out.println("idx_blks_read :" +idx_blks_read);
                System.out.println("idx_blks_hit:" +idx_blks_hit);


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

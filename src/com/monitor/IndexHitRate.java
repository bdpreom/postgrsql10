package com.monitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class IndexHitRate {
    public static void indexhitRate() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String indexhitRateSQL = "SELECT current_database() as db_name, relname as table_name,idx_scan as index_hit, seq_scan as index_miss FROM pg_stat_user_tables;\n";



        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(indexhitRateSQL);
            // execute select SQL stetement
            ResultSet rs1 = statement.executeQuery(indexhitRateSQL);





            while (rs1.next()) {

                String db_name = rs1.getString("db_name");
                String table_name = rs1.getString("table_name");
                String index_hit = rs1.getString("index_hit");
                String index_miss = rs1.getString("index_miss");




                System.out.println("db_name : " +db_name);
                System.out.println("table_name:" +table_name);
                System.out.println("index_hit:" +index_hit);
                System.out.println("index_miss:" +index_miss);



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

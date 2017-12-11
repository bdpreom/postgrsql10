package com.monitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class tableindexStat {
    public static void tableindexStat() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String tableindexStatSQL = "SELECT table_schema,TABLE_NAME,row_estimate,pg_size_pretty(total_bytes) AS total\n" +
                "  , pg_size_pretty(index_bytes) AS INDEX\n" +
                "  , pg_size_pretty(toast_bytes) AS toast\n" +
                "  , pg_size_pretty(table_bytes) AS TABLE\n" +
                "FROM (\n" +
                "       SELECT *, total_bytes-index_bytes-COALESCE(toast_bytes,0) AS table_bytes FROM (\n" +
                "                                                                                       SELECT c.oid,nspname AS table_schema, relname AS TABLE_NAME\n" +
                "                                                                                         , c.reltuples AS row_estimate\n" +
                "                                                                                         , pg_total_relation_size(c.oid) AS total_bytes\n" +
                "                                                                                         , pg_indexes_size(c.oid) AS index_bytes\n" +
                "                                                                                         , pg_total_relation_size(reltoastrelid) AS toast_bytes\n" +
                "                                                                                       FROM pg_class c\n" +
                "                                                                                         LEFT JOIN pg_namespace n ON n.oid = c.relnamespace\n" +
                "                                                                                       WHERE relkind = 'r'\n" +
                "                                                                                     ) a\n" +
                "     ) a;";



        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(tableindexStatSQL);
            // execute select SQL stetement
            ResultSet rs7 = statement.executeQuery(tableindexStatSQL);
            System.out.println(rs7);





            while (rs7.next()) {

                String table_schema = rs7.getString("table_schema");
                String table_name = rs7.getString("table_name");
                String row_estimate = rs7.getString("row_estimate");
                String total= rs7.getString("total");
                String index= rs7.getString("index");
                String toast= rs7.getString("toast");
                String table= rs7.getString("table");



                System.out.println("table_schema: " +table_schema);
                System.out.println("table_name:" +table_name);
                System.out.println("row_estimate:" +row_estimate );
                System.out.println("total:" +total);
                System.out.println("index :" +index);
                System.out.println("toast :" +toast);
                System.out.println("table :"+table);



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

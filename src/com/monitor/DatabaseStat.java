package com.monitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class DatabaseStat {
    public static void databaseStat() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String databaseStatSQL = "SELECT datid,datname,xact_commit,xact_rollback,tup_returned,tup_fetched,tup_inserted,tup_updated,tup_deleted,conflicts,deadlocks,stats_reset from pg_stat_database;\n";



        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(databaseStatSQL);
            // execute select SQL stetement
            ResultSet rs1 = statement.executeQuery(databaseStatSQL);





            while (rs1.next()) {
                String datid = rs1.getString("datid");
                String datname = rs1.getString("datname");
                String xact_commit = rs1.getString("xact_commit");
                String xact_rollback = rs1.getString("xact_rollback");
                String tup_returned = rs1.getString("tup_returned");
                String tup_fetched = rs1.getString("tup_fetched");
                String tup_inserted = rs1.getString("tup_inserted");
                String tup_updated = rs1.getString("tup_updated");
                String tup_deleted = rs1.getString("tup_deleted");
                String conflicts = rs1.getString("conflicts");
                String deadlocks = rs1.getString("deadlocks");
                String stats_reset = rs1.getString("stats_reset");

                System.out.println("datid : " + datid);
                System.out.println("datname :" +datname);
                System.out.println("xact_commit :" +xact_commit);
                System.out.println("xact_rollback :" +xact_rollback);
                System.out.println("tup_returned :" +tup_returned);
                System.out.println("tup_fetched :" +tup_fetched);
                System.out.println("tup_inserted :" +tup_inserted);
                System.out.println("tup_updated :" +tup_updated);
                System.out.println("tup_deleted :" +tup_deleted);
                System.out.println("conflicts: " +conflicts);
                System.out.println("deadlocks :" +deadlocks);
                System.out.println("stats_reset :" +stats_reset);





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

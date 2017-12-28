package com.monitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class DatabaseConflicts {
    public static void databaseConflicts() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String databaseConflictsSQL = "select datid,datname,confl_tablespace,confl_lock,confl_deadlock from pg_stat_database_conflicts;\n";



        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(databaseConflictsSQL);
            // execute select SQL stetement
            ResultSet rs1 = statement.executeQuery(databaseConflictsSQL);





            while (rs1.next()) {

                String datid = rs1.getString("datid");
                String datname = rs1.getString("datname");
                String confl_tablespace = rs1.getString("confl_tablespace");
                String confl_lock = rs1.getString("confl_lock");
                String confl_deadlock = rs1.getString("confl_deadlock");


                System.out.println("database id : " + datid);
                System.out.println("datname :" +datname);
                System.out.println("confl_tablespace :" +confl_tablespace);
                System.out.println("confl_lock :" +confl_lock);
                System.out.println("confl_deadlock :" +confl_deadlock);


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

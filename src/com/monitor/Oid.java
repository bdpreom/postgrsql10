package com.monitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class Oid {

    public static void oid() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String oidSQL = "SELECT oid, relname FROM pg_class WHERE relkind = 'r' \n" +
                "AND relname NOT LIKE 'pg_%' AND relname NOT LIKE 'sql_%';";



        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(oidSQL);
            // execute select SQL stetement
            ResultSet rs7 = statement.executeQuery(oidSQL);
            System.out.println(rs7);





            while (rs7.next()) {

                String oid = rs7.getString("oid");
                String relname = rs7.getString("relname");


                System.out.println("oid: " +oid);
                System.out.println("relname:" +relname);



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

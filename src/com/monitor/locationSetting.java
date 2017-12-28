package com.monitor;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class locationSetting {

    public static void fileLocations() throws SQLException {

        java.sql.Connection dbConnection = null;
        Statement statement = null;

        //Query 1 : Finding file locations

        String selectTableSQL = "SELECT name, setting FROM pg_settings WHERE category = 'File Locations' ORDER BY name;";


        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(selectTableSQL);
            // execute select SQL stetement
            ResultSet rs = statement.executeQuery(selectTableSQL);

            while (rs.next()) {

                String name = rs.getString("name");
                String setting = rs.getString("setting");

                System.out.println("name : " + name);
                System.out.println("settings : " + setting);

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

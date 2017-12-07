package com.monitor;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class MemorySettings {

    public static void memorySettings() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String memorySQL = " SELECT name, setting, unit FROM pg_settings WHERE category ILIKE '%memory%' ORDER\n" +
                "BY name;";

        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(memorySQL);
            // execute select SQL stetement
            ResultSet rs2 = statement.executeQuery(memorySQL);

            while (rs2.next()) {

                String name = rs2.getString("name");
                String setting = rs2.getString("setting");
                String unit = rs2.getString("unit");

                System.out.println("name: " +name);
                System.out.println("settings: " +setting);
                System.out.println("unit :" +unit);




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

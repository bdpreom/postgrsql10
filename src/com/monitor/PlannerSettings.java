package com.monitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class PlannerSettings {

    public static void plannerSettings() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String plannerSQL = "SELECT category, string_agg(name || '=' || setting, E'\\n' ORDER BY name) As settings\n" +
                "FROM pg_settings\n" +
                "WHERE category LIKE '%Planner%'\n" +
                "GROUP BY category\n" +
                "ORDER BY category;";

        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(plannerSQL);
            // execute select SQL stetement
            ResultSet rs3 = statement.executeQuery(plannerSQL);

            while (rs3.next()) {

                String category = rs3.getString("category");
                String settings = rs3.getString("settings");

                System.out.println("category: " +category);
                System.out.println("settings: " +settings);



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

package com.monitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class ConnectionSettings {

    public static void connectionSettings() throws SQLException{


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String connectionSQL = "SELECT name, context , unit ,\n" +
                "setting, boot_val, reset_val\n" +
                "FROM pg_settings\n" +
                "WHERE name IN ( 'listen_addresses', 'max_connections', 'shared_buffers', 'effec\n" +
                "tive_cache_size', 'work_mem', 'maintenance_work_mem')\n" +
                "ORDER BY context, name;";



        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(connectionSQL);
            // execute select SQL stetement
            ResultSet rs1 = statement.executeQuery(connectionSQL);





            while (rs1.next()) {

                String name = rs1.getString("name");
                String context = rs1.getString("context");
                String unit = rs1.getString("unit");
                String setting = rs1.getString("setting");
                String boot_val = rs1.getString("boot_val");
                String reset_val = rs1.getString("reset_val");




                System.out.println("name : " + name +" ++ " +"context:" + context + "++" +  "  " +"unit:" +unit);
                System.out.println("setting : " + setting);
                System.out.println("boot_val : " + boot_val );
                System.out.println("reset_val : " + reset_val);

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

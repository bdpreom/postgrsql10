package com.monitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class crudStatTable {


    public static void crudStatTable() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String crudStatTableSQL = "SELECT relname,cast(n_tup_ins AS numeric) / (n_tup_ins + n_tup_upd + n_tup_del) AS ins_pct,cast(n_tup_upd AS numeric) / (n_tup_ins + n_tup_upd + n_tup_del) AS upd_pct, cast(n_tup_del AS numeric) / (n_tup_ins\n" +
                "\n" +
                "+ n_tup_upd + n_tup_del) AS del_pct\n" +
                "\n" +
                "FROM pg_stat_user_tables\n" +
                "\n" +
                "WHERE (n_tup_ins + n_tup_upd + n_tup_del) > 0\n" +
                "\n" +
                "ORDER BY relname;";



        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(crudStatTableSQL);
            // execute select SQL stetement
            ResultSet rs1 = statement.executeQuery(crudStatTableSQL);





            while (rs1.next()) {

                String relname = rs1.getString("relname");
                String ins_pct = rs1.getString("ins_pct");
                String upd_pct = rs1.getString("upd_pct");
                String del_pct = rs1.getString("del_pct");






                System.out.println("relname : " +relname );
                System.out.println("ins_pct : " +ins_pct);
                System.out.println("upd_pct:" +upd_pct);
                System.out.println("del_pct: " +del_pct );


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


package com.monitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class indexUsed {

    public static void indexUsed() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String indexUsedSQL = "SELECT relname,seq_tup_read,idx_tup_fetch,cast(idx_tup_fetch AS numeric) / (idx_tup_fetch + seq_tup_read) \n" +
                "AS idx_tup_pct FROM pg_stat_user_tables WHERE (idx_tup_fetch + seq_tup_read)>0 ORDER BY idx_tup_pct;\n";



        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(indexUsedSQL);
            // execute select SQL stetement
            ResultSet rs7 = statement.executeQuery(indexUsedSQL);
            System.out.println(rs7);





            while (rs7.next()) {

                String relname = rs7.getString("relname");
                String seq_tup_read = rs7.getString("seq_tup_read");
                String idx_tup_fetch = rs7.getString("idx_tup_fetch");
                String idx_tup_pct= rs7.getString("idx_tup_pct");


                System.out.println("relname: " +relname);
                System.out.println("seq_tup_read:" +seq_tup_read);
                System.out.println("idx_tup_fetch:" +idx_tup_fetch );
                System.out.println("idx_tup_pct:" +idx_tup_pct);


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

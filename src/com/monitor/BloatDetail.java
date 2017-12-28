package com.monitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.monitor.Main.getDBConnection;

public class BloatDetail {

    public static void bloatDetail() throws SQLException {


        java.sql.Connection dbConnection = null;
        Statement statement = null;


        // finding connection settings
        String bloatDetailSQL = "SELECT\n" +
                "  current_database(), schemaname, tablename, /*reltuples::bigint, relpages::bigint, otta,*/\n" +
                "  ROUND((CASE WHEN otta=0 THEN 0.0 ELSE sml.relpages::FLOAT/otta END)::NUMERIC,1) AS tbloat,\n" +
                "  CASE WHEN relpages < otta THEN 0 ELSE bs*(sml.relpages-otta)::BIGINT END AS wastedbytes,\n" +
                "  iname, /*ituples::bigint, ipages::bigint, iotta,*/\n" +
                "  ROUND((CASE WHEN iotta=0 OR ipages=0 THEN 0.0 ELSE ipages::FLOAT/iotta END)::NUMERIC,1) AS ibloat,\n" +
                "  CASE WHEN ipages < iotta THEN 0 ELSE bs*(ipages-iotta) END AS wastedibytes\n" +
                "FROM (\n" +
                "       SELECT\n" +
                "         schemaname, tablename, cc.reltuples, cc.relpages, bs,\n" +
                "         CEIL((cc.reltuples*((datahdr+ma-\n" +
                "                              (CASE WHEN datahdr%ma=0 THEN ma ELSE datahdr%ma END))+nullhdr2+4))/(bs-20::FLOAT)) AS otta,\n" +
                "         COALESCE(c2.relname,'?') AS iname, COALESCE(c2.reltuples,0) AS ituples, COALESCE(c2.relpages,0) AS ipages,\n" +
                "         COALESCE(CEIL((c2.reltuples*(datahdr-12))/(bs-20::FLOAT)),0) AS iotta -- very rough approximation, assumes all cols\n" +
                "       FROM (\n" +
                "              SELECT\n" +
                "                ma,bs,schemaname,tablename,\n" +
                "                (datawidth+(hdr+ma-(CASE WHEN hdr%ma=0 THEN ma ELSE hdr%ma END)))::NUMERIC AS datahdr,\n" +
                "                (maxfracsum*(nullhdr+ma-(CASE WHEN nullhdr%ma=0 THEN ma ELSE nullhdr%ma END))) AS nullhdr2\n" +
                "              FROM (\n" +
                "                     SELECT\n" +
                "                       schemaname, tablename, hdr, ma, bs,\n" +
                "                       SUM((1-null_frac)*avg_width) AS datawidth,\n" +
                "                       MAX(null_frac) AS maxfracsum,\n" +
                "                       hdr+(\n" +
                "                         SELECT 1+COUNT(*)/8\n" +
                "                         FROM pg_stats s2\n" +
                "                         WHERE null_frac<>0 AND s2.schemaname = s.schemaname AND s2.tablename = s.tablename\n" +
                "                       ) AS nullhdr\n" +
                "                     FROM pg_stats s, (\n" +
                "                                        SELECT\n" +
                "                                          (SELECT current_setting('block_size')::NUMERIC) AS bs,\n" +
                "                                          CASE WHEN SUBSTRING(v,12,3) IN ('8.0','8.1','8.2') THEN 27 ELSE 23 END AS hdr,\n" +
                "                                          CASE WHEN v ~ 'mingw32' THEN 8 ELSE 4 END AS ma\n" +
                "                                        FROM (SELECT version() AS v) AS foo\n" +
                "                                      ) AS constants\n" +
                "                     GROUP BY 1,2,3,4,5\n" +
                "                   ) AS foo\n" +
                "            ) AS rs\n" +
                "         JOIN pg_class cc ON cc.relname = rs.tablename\n" +
                "         JOIN pg_namespace nn ON cc.relnamespace = nn.oid AND nn.nspname = rs.schemaname AND nn.nspname <> 'information_schema'\n" +
                "         LEFT JOIN pg_index i ON indrelid = cc.oid\n" +
                "         LEFT JOIN pg_class c2 ON c2.oid = i.indexrelid\n" +
                "     ) AS sml\n" +
                "ORDER BY wastedbytes DESC";



        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(bloatDetailSQL);
            // execute select SQL stetement
            ResultSet rs1 = statement.executeQuery(bloatDetailSQL);





            while (rs1.next()) {

                String current_database = rs1.getString("current_database");
                String schemaname = rs1.getString("schemaname");
                String tablename = rs1.getString("tablename");
                String tbloat = rs1.getString("tbloat");
                String wastedbytes = rs1.getString("wastedbytes");
                String iname = rs1.getString("iname");
                String ibloat = rs1.getString("ibloat");
                String wastedibytes = rs1.getString("wastedibytes");



                System.out.println("current_database : " +current_database);
                System.out.println("schemaname:" +schemaname);
                System.out.println("tablename:" +tablename);
                System.out.println("tbloat:" +tbloat);
                System.out.println("wastedbytes :" +wastedbytes);
                System.out.println("iname:" +iname);
                System.out.println("ibloat:" +ibloat);
                System.out.println("wastedibytes :" +wastedibytes);


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

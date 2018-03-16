package com.flowergarden.run;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class Run {

    public static void main(String[] args) throws IOException {

        File file = new File("flowergarden.db");
        String url = "jdbc:sqlite:"+file.getCanonicalFile().toURI();
        System.out.println(url);
        try(Connection conn = DriverManager.getConnection(url)) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from flower");
            //
            System.out.println("\n\nResultSet metadata info:");
            ResultSetMetaData meta = rs.getMetaData();
            int n = meta.getColumnCount();
            for (int i=1;i<=n;i++)  {
                System.out.printf("%d = %s <%s>", i,
                        meta.getColumnName(i),
                        meta.getColumnTypeName(i));
                if (meta.isNullable(i)==meta.columnNoNulls)
                    System.out.print(" NOT NULL");
                if (meta.isAutoIncrement(i)) System.out.print(" AUTO"); System.out.println("");
            }
            //
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

        /*File file = new File("flowergarden.db)");
        String url = "jdbc:sqlite:" + file.getCanonicalFile().toURI();
        //url = "jdbc:sqlite:file/flowergarden.db"; // для мака по идее штука;
        System.out.println(url);

        // try(Connection conn = DriverManager.getConnection(jdbc:sqlite:" + file.getCanonicalFile().toURI()){
        // как вариант;

        try (Connection conn = DriverManager.getConnection(url)){
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select");
            conn.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        *//*finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }*/

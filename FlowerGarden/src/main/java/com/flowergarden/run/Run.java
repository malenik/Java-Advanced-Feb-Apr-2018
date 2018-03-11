package com.flowergarden.run;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class Run {

    public static void main(String[] args) throws IOException {

        File file = new File("flowergarden.db)");
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

        /*finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }

}
package com.example.sqlinjectiondemo;

import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class UserService {

    static Connection conn = null;

    public static void connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqli", "awbd", "awbd");
        } catch (SQLException var2) {
            var2.printStackTrace();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }


    public Boolean authenticate(String username, String password) throws SQLException {

        connectDB();

        // Exemplu SQL Injection

        String sql = "SELECT id FROM users WHERE username = '" + username + "' AND password = '" + password + "';";

//        Statement stmt = conn.createStatement();
//        System.out.println(sql);
//        ResultSet rs = stmt.executeQuery(sql);


        // Exemplu instructiuni parametrizate

        String sql2 = "SELECT id FROM users WHERE username = ? AND password = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql2);
        pstmt.setString( 1, username);
        pstmt.setString( 2, password);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            // Login successful
            return true;
        }

        return false;

    }
}

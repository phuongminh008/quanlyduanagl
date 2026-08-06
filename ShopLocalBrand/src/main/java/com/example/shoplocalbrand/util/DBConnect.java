package com.example.shoplocalbrand.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    // Sửa lại user và password cho đúng với SQL Server trên máy bạn
    private static final String USER = "sa";
    private static final String PASS = "223600";
    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=ShopLocalBrandmoi2;encrypt=true;trustServerCertificate=true;";

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Hàm main để test nghiệm thu kết nối
    public static void main(String[] args) {
        if (getConnection() != null) {
            System.out.println("Kết nối SQL Server thành công!");
        } else {
            System.out.println("Kết nối thất bại. Hãy kiểm tra lại User/Pass hoặc SQL Server.");
        }
    }
}
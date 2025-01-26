/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasigudang.model;

/**
 *
 * @author ACER A314
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class KoneksiDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/gudang";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
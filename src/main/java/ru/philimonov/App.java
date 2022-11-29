package ru.philimonov;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

public class App {
    public static void main(String[] args) throws SQLException {
        String email = "philimonov@gmail.com";
        String password = "xyz123";

        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "password"
        )) {
            PreparedStatement ps = connection.prepareStatement("select * from service_user where email = ? and password = ?");
            ps.setString(1, email);
            ps.setString(2, md5Hex(password));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Hello " + rs.getString("email"));
                } else {
                    System.out.println("Access denied!!!");
                }
            }
        }
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "password")) {
            PreparedStatement ps = connection.prepareStatement("select * from service_user where email = ? and password = ?");
            ps.setString(1, email);
            ps.setString(2, md5Hex(password));

            int x = ps.executeUpdate();
            if (x == 0) {
                System.out.println("Access denied!!!");
            } else {
                System.out.println("Success!!!");
            }
        }
    }
}

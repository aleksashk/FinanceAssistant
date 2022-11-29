package ru.philimonov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

public class App {
    public static void main(String[] args) throws SQLException {
        System.out.println("Aloha!!!");
        System.out.println("Press 1 for authorization, 2 for registration");
        Scanner scanner = new Scanner(System.in);
        int request = scanner.nextInt();

        switch (request) {
            case 1:
                String email = request("email: ");
                String password = request("password: ");
                try (Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "password");
                     PreparedStatement preparedStatement = connection.prepareStatement("select * from service_user where email=? and password=?")) {
                    preparedStatement.setString(1, email);
                    preparedStatement.setString(2, password);

                    try (ResultSet rs = preparedStatement.executeQuery()) {
                        if (rs.next()) {
                            System.out.println("Hello " + rs.getString("email"));
                        } else {
                            System.out.println("Access denied!!!");
                        }
                    }
                }
                break;
            case 2:
                email = request("email: ");
                password = request("password: ");
                try (Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "password"
                );
                     PreparedStatement prepareStatement = connection.prepareStatement("insert into service_users (email, password) values (?,?)")) {
                    prepareStatement.setString(1, email);
                    prepareStatement.setString(2, md5Hex(password));
                    int x = prepareStatement.executeUpdate();
                    if (x == 0) {
                        System.out.println("Fail!");
                    } else {
                        System.out.println("Success!");
                    }
                }
                break;
        }
    }

    private static String request(String str) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter " + str);
        String result = null;

        try {
            result = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

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
    private final static String URL = "jdbc:postgresql://localhost/postgres";
    private final static String USER = "postgres";
    private final static String PASSWORD = "password";

    public static void main(String[] args) throws SQLException {
        System.out.println("Aloha!!!");
        System.out.println("Press 1 for authorization, 2 for registration");
        Scanner scanner = new Scanner(System.in);
        int request = scanner.nextInt();

        switch (request) {
            case 1:
                int id = authentication();
                System.out.println("press 1 - get list of accounts, 2 - create an account, 3 - delete an account");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        getAccount(id);
                        break;
                    case 2:
                        createAccount(id);
                        break;
                    case 3:
                        deleteAccount(id);
                        break;
                }
            case 2:
                registration();
                break;
        }
    }

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static int authentication() throws SQLException {
        String email = request("email: ");
        String password = request("password: ");
        String sql = "select * from service_user where email=? and password=?";
        int id = 0;
        try (Connection connection = connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(1, md5Hex(password));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("id");
                    System.out.println("Hello " + rs.getString("email"));
                } else {
                    System.out.println("Access denied!");
                }
            }
        }
        return id;
    }

    public static void registration() throws SQLException {
        String email = request("email: ");
        String password = request("password: ");
        String sql = "insert into service_user(email, password) values(?,?)";
        try (Connection connection = connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, md5Hex(password));
            int number = ps.executeUpdate();
            if (number == 0) {
                System.out.println("Fail!!");
            } else {
                System.out.println("Success!");
            }
        }
    }

    public static void getAccount(int id) throws SQLException {
        String sql = "select * from account where user_id = ?";
        try (Connection connection = connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Balance: " + rs.getString("name") +
                        " is " + rs.getDouble("amount"));
            }
        }
    }

    public static void createAccount(int id) throws SQLException {
        String account_title = request("account_title: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inpunt amount: ");
        double amount = scanner.nextDouble();
        String sql = "insert into account(name, amount, user_id) values(?,?,?)";
        try (Connection connection = connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, account_title);
            ps.setDouble(2, amount);
            ps.setInt(3, id);
            int x = ps.executeUpdate();
            if (x == 0) {
                System.out.println("Fail!");
            } else {
                System.out.println("Success!");
            }
        }
    }

    public static void deleteAccount(int id) throws SQLException {
        String account_name = request("Enter account_title: ");
        String sql = "delete from account where user_id = ? and name = ?";
        try (Connection connection = connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, account_name);
            int x = ps.executeUpdate();
            if (x == 0) {
                System.out.println("Fail!");
            } else {
                System.out.println("Success!");
            }
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

package ru.philimonov.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.philimonov.exception.CustomException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionTypeDao {
    private final DataSource dataSource;

    public TransactionTypeDao() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost/postgres");
        config.setUsername("postgres");
        config.setPassword("password");
        dataSource = new HikariDataSource(config);
    }

    public TransactionTypeModel createType(String type) {
        TransactionTypeModel transactionTypeModel = null;
        String sql = "insert into category(name) values (?)";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                transactionTypeModel = new TransactionTypeModel();
                transactionTypeModel.setId(rs.getLong("id"));
                transactionTypeModel.setType(type);
            }
        } catch (SQLException e) {
            throw new CustomException(e);
        }
        return transactionTypeModel;
    }

    public void editType(String name, long id) {
        String sql = "update category set name = ? where id = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setLong(2, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new CustomException(e);
        }
    }

    public void deleteType(String name) {
        String sql = "delete from category where name = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException(e);
        }
    }
}

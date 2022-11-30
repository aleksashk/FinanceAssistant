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

public class TransactionCategoryDao {
    private final DataSource dataSource;

    public TransactionCategoryDao() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost/postgres");
        config.setUsername("postgres");
        config.setPassword("password");
        dataSource = new HikariDataSource(config);
    }

    public TransactionCategoryModel createCategory(String type) {
        TransactionCategoryModel transactionCategoryModel = null;
        String sql = "insert into category(name) values (?)";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                transactionCategoryModel = new TransactionCategoryModel();
                transactionCategoryModel.setId(rs.getLong("id"));
                transactionCategoryModel.setType(type);
            }
        } catch (SQLException e) {
            throw new CustomException(e);
        }
        return transactionCategoryModel;
    }

    public void editCategory(String name, long id) {
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

    public void deleteCategory(String name) {
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

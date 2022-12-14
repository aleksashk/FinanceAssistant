package ru.philimonov.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.philimonov.exception.CustomException;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private final DataSource dataSource;

    public AccountDao() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost/postgres");
        config.setUsername("postgres");
        config.setPassword("password");
        dataSource = new HikariDataSource(config);
    }

    public List<AccountModel> getAccount(long id) {
        AccountModel accountModel;
        ArrayList<AccountModel> accountModels = new ArrayList<>();
        String sql = "select * from account where user_id = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                accountModel = new AccountModel();
                accountModel.setId(rs.getLong("id"));
                accountModel.setName(rs.getString("name"));
                accountModel.setAmount(rs.getBigDecimal("amount"));
                accountModel.setUserId(id);
                accountModels.add(accountModel);
            }
        } catch (SQLException e) {
            throw new CustomException(e);
        }
        return accountModels;
    }

    public AccountModel createAccount(String accountName, BigDecimal amount, long id) {
        AccountModel accountModel = null;
        String sql = "insert into account(name,amount, user_id) values(?,?,?)";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accountName);
            ps.setBigDecimal(2, amount);
            ps.setLong(1, id);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                accountModel = new AccountModel();
                accountModel.setId(rs.getLong("id"));
                accountModel.setName(accountName);
                accountModel.setAmount(amount);
                accountModel.setId(id);
            }
        } catch (SQLException e) {
            throw new CustomException("Invalid data.");
        }
        return accountModel;
    }

    public void deleteAccount(long id) {
        String sql = "delete from account where id = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            int x = ps.executeUpdate();

        } catch (SQLException e) {
            throw new CustomException(e);
        }
    }
}

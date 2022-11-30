package ru.philimonov.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.philimonov.exception.CustomException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {
    private final DataSource dataSource;

    public TransactionDao() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost/postgres");
        config.setUsername("postgres");
        config.setPassword("password");
        dataSource = new HikariDataSource(config);
    }

    public List<TransactionModel> getTransaction(String type, LocalDateTime startDate, LocalDateTime endDate) {
        TransactionModel transactionModel;
        List<TransactionModel> transactionModelList = new ArrayList<>();
        String sql = "select t.* from transaction t " +
                "join category_transaction ct on t.id = ct.transaction_id " +
                "join category c on ct.category_id = c.id " +
                "where t.t_date between ? and ? and category.name=?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setTimestamp(1, Timestamp.valueOf(startDate));
            ps.setTimestamp(1, Timestamp.valueOf(endDate));
            ps.setString(3, type);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                transactionModel = new TransactionModel();
                transactionModel.setId(rs.getLong("id"));
                transactionModel.setAmount(rs.getBigDecimal("amount"));
                transactionModel.setDateTime(rs.getTimestamp("dateTime").toLocalDateTime());
                transactionModel.setFromAccount(rs.getLong("fromAccount"));
                transactionModel.setToAccount(rs.getLong("toAccount"));
                transactionModelList.add(transactionModel);
            }
        } catch (SQLException e) {
            throw new CustomException(e);
        }
        return transactionModelList;
    }
}

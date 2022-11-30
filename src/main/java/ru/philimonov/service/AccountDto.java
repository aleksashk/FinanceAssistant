package ru.philimonov.service;

import java.math.BigDecimal;

public class AccountDto {
    private long id;
    private String Name;
    private BigDecimal amount;
    private int userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", amount=" + amount +
                ", userId=" + userId +
                '}';
    }
}


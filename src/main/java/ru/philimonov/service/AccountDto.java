package ru.philimonov.service;

public class AccountDto {
    private long id;
    private String Name;
    private double amount;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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


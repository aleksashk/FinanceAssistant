package ru.philimonov.service;

import ru.philimonov.dao.TransactionTypeDao;

public class DeleteTypeTransactionService {
    private final TransactionTypeDao transactionTypeDao;

    public DeleteTypeTransactionService() {
        this.transactionTypeDao = new TransactionTypeDao();
    }

    public void deleteTransactionType(String name) {
        transactionTypeDao.deleteType(name);
    }
}

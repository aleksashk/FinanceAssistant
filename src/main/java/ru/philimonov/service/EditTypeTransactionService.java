package ru.philimonov.service;

import ru.philimonov.dao.TransactionTypeDao;

public class EditTypeTransactionService {
    private final TransactionTypeDao transactionTypeDao;

    public EditTypeTransactionService() {
        this.transactionTypeDao = new TransactionTypeDao();
    }

    public void editTransactionType(String name, long id){
        transactionTypeDao.editType(name, id);
    }
}

package ru.philimonov.service;

import ru.philimonov.dao.TransactionCategoryDao;

public class DeleteCategoryTransactionService {
    private final TransactionCategoryDao transactionCategoryDao;

    public DeleteCategoryTransactionService(TransactionCategoryDao transactionCategoryDao) {
        this.transactionCategoryDao = new TransactionCategoryDao();
    }

    public void deleteTransactionType(String name) {
        transactionCategoryDao.deleteCategory(name);
    }
}

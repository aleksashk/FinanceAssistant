package ru.philimonov.service;

import ru.philimonov.dao.TransactionCategoryDao;

public class EditCategoryTransactionService {
    private final TransactionCategoryDao transactionCategoryDao;

    public EditCategoryTransactionService(TransactionCategoryDao transactionCategoryDao) {
        this.transactionCategoryDao = new TransactionCategoryDao();
    }

    public void editTransactionType(String name, long id){
        transactionCategoryDao.editCategory(name, id);
    }
}

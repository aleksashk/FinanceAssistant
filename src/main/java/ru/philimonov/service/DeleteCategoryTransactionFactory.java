package ru.philimonov.service;

import ru.philimonov.dao.TransactionCategoryDao;

public class DeleteCategoryTransactionFactory {
    private static DeleteCategoryTransactionService deleteCategoryTransactionService;

    public static DeleteCategoryTransactionService getDeleteCategoryTransactionService() {
        if (deleteCategoryTransactionService == null) {
            deleteCategoryTransactionService = new DeleteCategoryTransactionService(new TransactionCategoryDao());
        }
        return deleteCategoryTransactionService;
    }
}

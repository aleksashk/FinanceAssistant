package ru.philimonov.service;

import ru.philimonov.dao.TransactionCategoryDao;

public class EditCategoryTransactionFactory {
    private static EditCategoryTransactionService editCategoryTransactionService;

    public static EditCategoryTransactionService getEditCategoryTransactionService(){
        if(editCategoryTransactionService == null){
            editCategoryTransactionService = new EditCategoryTransactionService(new TransactionCategoryDao());
        }
        return editCategoryTransactionService;
    }
}

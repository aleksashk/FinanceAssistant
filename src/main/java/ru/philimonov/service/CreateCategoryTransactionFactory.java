package ru.philimonov.service;

import ru.philimonov.converter.TransactionToDtoConverter;
import ru.philimonov.dao.TransactionCategoryDao;

public class CreateCategoryTransactionFactory {
    private static CreateCategoryTransactionService createCategoryTransactionService;
    public static CreateCategoryTransactionService getCreateCategoryTransactionService(){
        if(createCategoryTransactionService == null){
            createCategoryTransactionService = new CreateCategoryTransactionService(new TransactionCategoryDao(), new TransactionToDtoConverter());
        }
        return createCategoryTransactionService;
    }
}

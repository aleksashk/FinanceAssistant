package ru.philimonov.service;

import ru.philimonov.converter.TransactionToDtoConverter;
import ru.philimonov.dao.TransactionCategoryDao;
import ru.philimonov.dao.TransactionCategoryModel;

public class CreateCategoryTransactionService {
    private final TransactionCategoryDao transactionCategoryDao;
    private final TransactionToDtoConverter categoryToDtoConverter;

    public CreateCategoryTransactionService(TransactionCategoryDao transactionCategoryDao, TransactionToDtoConverter categoryToDtoConverter) {
        this.transactionCategoryDao = transactionCategoryDao;
        this.categoryToDtoConverter = categoryToDtoConverter;
    }

    public TransactionCategoryDto createType(String name) {
        TransactionCategoryModel transactionCategoryModel = transactionCategoryDao.createCategory(name);
        if(transactionCategoryModel == null){
            return null;
        }
        return categoryToDtoConverter.convert(transactionCategoryModel);
    }
}

package ru.philimonov.service;

import ru.philimonov.converter.TransactionToDtoConverter;
import ru.philimonov.dao.TransactionTypeDao;
import ru.philimonov.dao.TransactionTypeModel;

public class CreateTypeTransactionService {
    private final TransactionTypeDao transactionTypeDao;
    private final TransactionToDtoConverter typeToDtoConverter;

    public CreateTypeTransactionService() {
        this.transactionTypeDao = new TransactionTypeDao();
        this.typeToDtoConverter = new TransactionToDtoConverter();
    }

    public TransactionTypeDto createType(String name) {
        TransactionTypeModel transactionTypeModel = transactionTypeDao.createType(name);
        return typeToDtoConverter.convert(transactionTypeModel);
    }
}

package ru.philimonov.service;

import ru.philimonov.converter.TransactionDaoToTransDtoConverter;
import ru.philimonov.dao.TransactionDao;

public class TransactionShowFactory {
    private static TransactionShowService transactionShowService;

    public static TransactionShowService getTransactionShowService() {
        if (transactionShowService == null) {
            transactionShowService = new TransactionShowService(
                    new TransactionDao(),
                    new TransactionDaoToTransDtoConverter()
            );
        }
        return transactionShowService;
    }
}

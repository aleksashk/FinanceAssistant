package ru.philimonov.service;

import ru.philimonov.converter.TransactionDaoToTransDtoConverter;
import ru.philimonov.dao.TransactionDao;
import ru.philimonov.dao.TransactionModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionShowService {
    private final TransactionDao transactionDao;
    private final TransactionDaoToTransDtoConverter transactionDaoToTransDtoConverter;

    public TransactionShowService(TransactionDao transactionDao, TransactionDaoToTransDtoConverter transactionDaoToTransDtoConverter) {
        this.transactionDao = transactionDao;
        this.transactionDaoToTransDtoConverter = transactionDaoToTransDtoConverter;
    }

    public List<TransactionDto> transactionDtoList(String type, LocalDateTime startDate, LocalDateTime endDate) {
        List<TransactionModel> transactionModelList = transactionDao.getTransaction(type, startDate, endDate);
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        for (TransactionModel item : transactionModelList) {
            transactionDtoList.add(transactionDaoToTransDtoConverter.convert(item));
        }
        return transactionDtoList;
    }
}

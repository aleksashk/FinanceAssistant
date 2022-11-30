package ru.philimonov.converter;

import ru.philimonov.dao.TransactionModel;
import ru.philimonov.service.TransactionDto;

public class TransactionDaoToTransDtoConverter implements Converter<TransactionModel, TransactionDto>{
    @Override
    public TransactionDto convert(TransactionModel source) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(source.getId());
        transactionDto.setAmount(source.getAmount());
        transactionDto.setDateTime(source.getDateTime());
        transactionDto.setFromAccount(source.getFromAccount());
        transactionDto.setToAccount(source.getToAccount());
        return transactionDto;
    }
}

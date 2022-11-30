package ru.philimonov.converter;

import ru.philimonov.dao.TransactionTypeModel;
import ru.philimonov.service.TransactionTypeDto;

public class TransactionToDtoConverter implements Converter<TransactionTypeModel, TransactionTypeDto> {

    @Override
    public TransactionTypeDto convert(TransactionTypeModel source) {
        TransactionTypeDto transactionTypeDto = new TransactionTypeDto();
        transactionTypeDto.setId(source.getId());
        transactionTypeDto.setType(source.getType());
        return transactionTypeDto;
    }
}

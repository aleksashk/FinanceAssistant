package ru.philimonov.converter;

import ru.philimonov.dao.TransactionCategoryModel;
import ru.philimonov.service.TransactionCategoryDto;

public class TransactionToDtoConverter implements Converter<TransactionCategoryModel, TransactionCategoryDto> {

    @Override
    public TransactionCategoryDto convert(TransactionCategoryModel source) {
        TransactionCategoryDto transactionCategoryDto = new TransactionCategoryDto();
        transactionCategoryDto.setId(source.getId());
        transactionCategoryDto.setType(source.getType());
        return transactionCategoryDto;
    }
}

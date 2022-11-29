package ru.philimonov.converter;

import ru.philimonov.dao.AccountModel;
import ru.philimonov.service.AccountDto;

public class AccountToDtoConverter implements Converter<AccountModel, AccountDto> {
    @Override
    public AccountDto convert(AccountModel source) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(source.getId());
        accountDto.setName(source.getName());
        accountDto.setAmount(source.getAmount());
        accountDto.setUserId(source.getUserId());
        return accountDto;
    }
}

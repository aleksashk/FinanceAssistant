package ru.philimonov.service;

import ru.philimonov.converter.AccountToDtoConverter;
import ru.philimonov.dao.AccountDao;
import ru.philimonov.dao.AccountModel;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private final AccountDao accountDao;
    private final AccountToDtoConverter accountToDtoConverter;

    public AccountService(AccountDao accountDao, AccountToDtoConverter accountToDtoConverter) {
        this.accountDao = accountDao;
        this.accountToDtoConverter = accountToDtoConverter;
    }

    public List<AccountDto> accountDtoList(long id) {
        List<AccountModel> accountModelList = accountDao.getAccount(id);
        List<AccountDto> accountDtoList = new ArrayList<>();
        for (AccountModel item : accountModelList){
            accountDtoList.add(accountToDtoConverter.convert(item));
        }

        return accountDtoList;
    }
}

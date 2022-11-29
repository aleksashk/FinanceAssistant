package ru.philimonov.service;

import ru.philimonov.converter.AccountToDtoConverter;
import ru.philimonov.dao.AccountDao;

import java.util.List;

public class AccountService {
    private final AccountDao accountDao;
    private final AccountToDtoConverter accountToDtoConverter;

    public AccountService() {
        this.accountDao = new AccountDao();
        this.accountToDtoConverter = new AccountToDtoConverter();
    }

    public List<String> accountDtoList(long id) {
        List<String> list = accountDao.getAccount(id);
        return list;
    }
}

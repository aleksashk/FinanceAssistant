package ru.philimonov.service;

import ru.philimonov.converter.AccountToDtoConverter;
import ru.philimonov.dao.AccountDao;
import ru.philimonov.dao.AccountModel;

import java.math.BigDecimal;

public class CreateAccountService {
    private final AccountDao accountDao;
    private final AccountToDtoConverter accountToDtoConverter;

    public CreateAccountService() {
        this.accountDao = new AccountDao();
        this.accountToDtoConverter = new AccountToDtoConverter();
    }

    public AccountDto createAccount(String accountName, BigDecimal amount, long id) {
        AccountModel accountModel = accountDao.createAccount(accountName, amount, id);
        if (accountModel == null) {
            return null;
        }
        return accountToDtoConverter.convert(accountModel);
    }
}


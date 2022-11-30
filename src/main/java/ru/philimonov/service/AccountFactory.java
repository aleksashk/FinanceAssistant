package ru.philimonov.service;

import ru.philimonov.converter.AccountToDtoConverter;
import ru.philimonov.dao.AccountDao;

public class AccountFactory {
    private static AccountService accountService;
    public static AccountService getAccountService(){
        if(accountService == null){
            accountService = new AccountService(new AccountDao(), new AccountToDtoConverter());
        }
        return accountService;
    }
}

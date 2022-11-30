package ru.philimonov.service;

import ru.philimonov.converter.AccountToDtoConverter;
import ru.philimonov.dao.AccountDao;

public class CreateAccountFactory {
    private static CreateAccountService createAccountService;

    public static CreateAccountService getCreateAccountService() {
        if (createAccountService == null) {
            createAccountService = new CreateAccountService(new AccountDao(), new AccountToDtoConverter());
        }
        return createAccountService;
    }

}

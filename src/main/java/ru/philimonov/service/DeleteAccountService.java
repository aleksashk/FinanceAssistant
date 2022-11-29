package ru.philimonov.service;

import ru.philimonov.dao.AccountDao;

public class DeleteAccountService {
    private final AccountDao accountDao;

    public DeleteAccountService() {
        this.accountDao = new AccountDao();
    }

    public void deleteAccount(long id) {
        accountDao.deleteAccount(id);
    }
}


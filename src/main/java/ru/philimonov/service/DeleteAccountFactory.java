package ru.philimonov.service;

import ru.philimonov.dao.AccountDao;

public class DeleteAccountFactory {
    private static DeleteAccountService deleteAccountService;
    public static DeleteAccountService getDeleteAccountService(){
        if(deleteAccountService == null){
            deleteAccountService= new DeleteAccountService(new AccountDao());
        }
        return deleteAccountService;
    }
}

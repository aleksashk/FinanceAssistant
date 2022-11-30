package ru.philimonov.service;

import ru.philimonov.converter.UserModelToDtoConverter;
import ru.philimonov.dao.UserDao;

public class RegFactory {
    private static RegService regService;

    public static RegService getRegService() {
        if(regService == null){
            regService = new RegService(
                    new UserDao(),
                    new Md5DigestService(),
                    new UserModelToDtoConverter()
            );
        }
        return regService;
    }
}

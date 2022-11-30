package ru.philimonov.service;

import ru.philimonov.converter.UserModelToDtoConverter;
import ru.philimonov.dao.UserDao;

public class ServiceFactory {
    private static AuthService authService;

    public static AuthService getAuthService(){
        if(authService == null){
            authService = new AuthService(
                    new UserDao(),
                    new Md5DigestService(),
                    new UserModelToDtoConverter()
            );
        }
        return authService;
    }
}

package ru.philimonov.service;

import ru.philimonov.converter.UserModelToDtoConverter;
import ru.philimonov.dao.UserDao;
import ru.philimonov.dao.UserModel;

public class AuthService {
    private final UserDao userDao;
    private final DigestService digestService;
    private final UserModelToDtoConverter userDtoConverter;

    public AuthService(UserDao userDao, DigestService digestService, UserModelToDtoConverter userModelToDtoConverter) {
        this.userDao = userDao;
        this.digestService = digestService;
        this.userDtoConverter = userModelToDtoConverter;
    }

    public UserDto auth(String email, String password) {
        String hash = digestService.hex(password);
        UserModel userModel = userDao.findByEmailAndHash(email, hash);
        if (userModel == null) {
            return null;
        }
        return userDtoConverter.convert(userModel);
    }
}

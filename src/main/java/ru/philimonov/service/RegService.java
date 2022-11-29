package ru.philimonov.service;

import ru.philimonov.converter.UserModelToDtoConverter;
import ru.philimonov.dao.UserDao;
import ru.philimonov.dao.UserModel;

public class RegService {
    private final UserDao userDao;
    private final DigestService digestService;
    private final UserModelToDtoConverter userModelToDtoConverter;

    public RegService() {
        this.userDao = new UserDao();
        this.digestService = new Md5DigestService();
        this.userModelToDtoConverter = new UserModelToDtoConverter();
    }

    public UserDto registration(String email, String password) {
        String hash = digestService.hex(password);
        UserModel userModel = userDao.insert(email, hash);
        if (userModel == null) {
            return null;
        }
        return userModelToDtoConverter.convert(userModel);
    }
}

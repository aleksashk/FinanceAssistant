package ru.philimonov.converter;

import ru.philimonov.dao.UserModel;
import ru.philimonov.service.UserDto;

public class UserModelToDtoConverter implements Converter<UserModel, UserDto>{

    @Override
    public UserDto convert(UserModel source) {
        UserDto userDto = new UserDto();
        userDto.setId(source.getId());
        userDto.setEmail(source.getEmail());
        return userDto;
    }
}

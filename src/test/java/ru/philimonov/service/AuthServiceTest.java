package ru.philimonov.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.philimonov.converter.UserModelToDtoConverter;
import ru.philimonov.dao.UserDao;
import ru.philimonov.dao.UserModel;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {

    @InjectMocks AuthService subj;

    @Mock
    UserDao userDao;

    @Mock
    DigestService digestService;

    @Mock
    UserModelToDtoConverter userDtoConverter;

    @Test
    public void auth_userNotFound() {
        when(digestService.hex("password")).thenReturn("hex");
        when(userDao.findByEmailAndHash("harry@mail.com", "hex")).thenReturn(null);

        UserDto userDto = subj.auth("qwerty@mail.com", "password");

        assertNull(userDto);
        verify(digestService, times(1)).hex("password");
        verify(userDao, times(1)).findByEmailAndHash("qwerty@mail.com", "hex");
        verifyZeroInteractions(userDtoConverter);
    }

    @Test
    public void auth_userFound() {
        when(digestService.hex("password")).thenReturn("hex");

        UserModel userModel = new UserModel();
        userModel.setId(1);
        userModel.setEmail("qwerty@mail.com");
        userModel.setPassword("hex");

        when(userDao.findByEmailAndHash("qwerty@mail.com", "hex")).thenReturn(userModel);

        UserDto userDto1 = new UserDto();
        userDto1.setId(1);
        userDto1.setEmail("qwerty@mail.com");
        when(userDtoConverter.convert(userModel)).thenReturn(userDto1);

        UserDto userDto = subj.auth("qwerty@mail.com", "password");

        assertNotNull(userDto);
        assertEquals(userDto1, userDto);

        verify(digestService, times(1)).hex("password");
        verify(userDao, times(1)).findByEmailAndHash("qwerty@mail.com", "hex");
        verify(userDtoConverter, times(1)).convert(userModel);
    }
}

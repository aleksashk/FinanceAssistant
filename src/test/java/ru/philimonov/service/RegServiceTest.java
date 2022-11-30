package ru.philimonov.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.philimonov.converter.UserModelToDtoConverter;
import ru.philimonov.dao.UserDao;
import ru.philimonov.dao.UserModel;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegServiceTest {

    @InjectMocks
    RegService subj;

    @Mock
    UserDao userDao;
    @Mock
    DigestService digestService;
    @Mock
    UserModelToDtoConverter userDtoConverter;

    @Test
    public void registration_userNotRegistered() {
        when(digestService.hex("password")).thenReturn("hex");
        when(userDao.insert("john@mail.com", "hex")).thenReturn(null);

        UserDto userDto = subj.registration("john@mail.com", "password");

        assertNull(userDto);

        verify(digestService, times(1)).hex("password");
        verify(userDao, times(1)).insert("john@mail.com", "hex");
        verifyZeroInteractions(userDtoConverter);

    }

    @Test
    public void registration_userRegistered() {
        when(digestService.hex("password")).thenReturn("hex");

        UserModel userModel = new UserModel();
        userModel.setId(1);
        userModel.setEmail("john@mail.com");
        userModel.setPassword("hex");

        when(userDao.insert("john@mail.com", "hex")).thenReturn(userModel);

        UserDto userDto1 = new UserDto();
        userDto1.setId(1);
        userDto1.setEmail("harry@mail.com");
        when(userDtoConverter.convert(userModel)).thenReturn(userDto1);

        UserDto userDto = subj.registration("john@mail.com", "password");

        assertNotNull(userDto);
        assertEquals(userDto1, userDto);

        verify(digestService, times(1)).hex("password");
        verify(userDao, times(1)).insert("john@mail.com", "hex");
        verify(userDtoConverter, times(1)).convert(userModel);
    }
}

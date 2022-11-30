package ru.philimonov.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.philimonov.converter.AccountToDtoConverter;
import ru.philimonov.dao.AccountDao;
import ru.philimonov.dao.AccountModel;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateAccountServiceTest {

    @InjectMocks CreateAccountService subj;

    @Mock
    AccountDao accountDao;
    @Mock
    AccountToDtoConverter accountToDtoConverter;

    @Test
    public void createAccount_notCreated() {

        when(accountDao.createAccount("rest", new BigDecimal(400), 1)).thenReturn(null);

        AccountDto accountDto = subj.createAccount("rest", new BigDecimal(400), 1);

        assertNull(accountDto);

        verify(accountDao, times(1)).createAccount("car", new BigDecimal(400), 1);
        verifyZeroInteractions(accountToDtoConverter);
    }

    @Test
    public void createAccount_Created() {

        AccountModel accountModel = new AccountModel();
        accountModel.setId(1);
        accountModel.setName("rest");
        accountModel.setAmount(new BigDecimal(400));
        accountModel.setUserId(2);

        when(accountDao.createAccount("rest", new BigDecimal(400), 2)).thenReturn(accountModel);

        AccountDto accountDto1 = new AccountDto();
        accountDto1.setId(1);
        accountDto1.setName("rest");
        accountDto1.setAmount(new BigDecimal(400));
        accountDto1.setUserId(2);
        when(accountToDtoConverter.convert(accountModel)).thenReturn(accountDto1);

        AccountDto accountDto = subj.createAccount("rest", new BigDecimal(400), 2);

        assertNotNull(accountDto);
        assertEquals(accountDto1, accountDto);

        verify(accountDao, times(1)).createAccount("rest", new BigDecimal(400), 2);
        verify(accountToDtoConverter, times(1)).convert(accountModel);

    }
}

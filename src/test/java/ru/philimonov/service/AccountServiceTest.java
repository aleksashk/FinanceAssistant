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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks AccountService subj;

    @Mock
    AccountDao accountDao;
    @Mock
    AccountToDtoConverter accountToDtoConverter;

    @Test
    public void accountDtoList_accountsFound() {
        List<AccountModel> accountModelList = new ArrayList<>();
        AccountModel accountModel = new AccountModel();
        accountModel.setId(2);
        accountModel.setUserId(1);
        accountModel.setName("rent");
        accountModel.setAmount(BigDecimal.valueOf(1000));
        accountModelList.add(accountModel);

        when(accountDao.getAccount(1)).thenReturn(accountModelList);

        List<AccountDto> accountDtoList = subj.accountDtoList(1);

        assertNotNull(accountDtoList);
        verify(accountDao, times(1)).getAccount(1);
    }
}

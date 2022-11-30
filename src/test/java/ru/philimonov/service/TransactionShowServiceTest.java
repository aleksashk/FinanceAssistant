package ru.philimonov.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.philimonov.converter.TransactionDaoToTransDtoConverter;
import ru.philimonov.dao.TransactionDao;
import ru.philimonov.dao.TransactionModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class TransactionShowServiceTest {

    @InjectMocks
    TransactionShowService subj;

    @Mock
    TransactionDao transactionDao;
    @Mock
    TransactionDaoToTransDtoConverter transactionDaoToTransDtoConverter;

    @Test
    public void transactionDtoList() {
        List<TransactionModel> transactionModelList = new ArrayList<>();
        TransactionModel transactionModel = new TransactionModel();
        transactionModel.setId(1);
        transactionModel.setAmount(BigDecimal.valueOf(200));
        transactionModel.setDateTime(LocalDateTime.of(2022, 2, 4, 0, 0));
        transactionModel.setFromAccount(2);
        transactionModel.setToAccount(3);
        transactionModelList.add(transactionModel);

        when(transactionDao.getTransaction("food", LocalDateTime.of(2022, 3, 1, 0, 0), LocalDateTime.of(2022, 1, 1, 0, 0))).thenReturn(transactionModelList);

        List<TransactionDto> transactionDtoList = subj.transactionDtoList("food", LocalDateTime.of(2022, 3, 1, 0, 0), LocalDateTime.of(2022, 1, 1, 0, 0));

        assertNotNull(transactionDtoList);
        verify(transactionDao, times(1)).getTransaction("food", LocalDateTime.of(2022, 3, 1, 0, 0), LocalDateTime.of(2022, 1, 1, 0, 0));
    }
}

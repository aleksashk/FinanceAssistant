package ru.philimonov.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.philimonov.converter.TransactionToDtoConverter;
import ru.philimonov.dao.TransactionCategoryDao;
import ru.philimonov.dao.TransactionCategoryModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateCategoryTransactionServiceTest {

    @InjectMocks
    CreateCategoryTransactionService subj;

    @Mock
    TransactionCategoryDao transactionCategoryDao;
    @Mock
    TransactionToDtoConverter categoryToDtoConverter;

    @Test
    public void createCategory_CategoryNotCreated() {
        when(transactionCategoryDao.createCategory("school")).thenReturn(null);

        TransactionCategoryDto transactionCategoryDto = subj.createType("school");

        assertNull(transactionCategoryDto);
        verify(transactionCategoryDao, times(1)).createCategory("food");
        verifyZeroInteractions(categoryToDtoConverter);
    }

    @Test
    public void createType_typeCreated() {

        TransactionCategoryModel transactionCategoryModel = new TransactionCategoryModel();
        transactionCategoryModel.setType("school");

        when(transactionCategoryDao.createCategory("school")).thenReturn(transactionCategoryModel);

        TransactionCategoryDto transactionCategoryDto1 = new TransactionCategoryDto();
        transactionCategoryDto1.setType("school");
        when(categoryToDtoConverter.convert(transactionCategoryModel)).thenReturn(transactionCategoryDto1);

        TransactionCategoryDto transactionCategoryDto = subj.createType("school");

        assertNotNull(transactionCategoryDto);
        assertEquals(transactionCategoryDto1, transactionCategoryDto);

        verify(transactionCategoryDao, times(1)).createCategory("school");
        verify(categoryToDtoConverter, times(1)).convert(transactionCategoryModel);
    }
}

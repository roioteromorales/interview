package com.interview.logic.transactions.services;


import com.interview.logic.statistics.services.StatisticsService;
import com.interview.logic.transactions.model.Transaction;
import com.interview.presentation.transactions.time.TimestampValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;

import static com.interview.presentation.transactions.time.TimestampValidator.TIME_WINDOW;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    private static final Instant NOW = Instant.ofEpochMilli(TIME_WINDOW * 2);
    private static final Instant OUTDATED = NOW.minusMillis(TIME_WINDOW + 1);

    @Mock
    private TimestampValidator timestampValidator;

    @Mock
    private StatisticsService statisticsServiceMock;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void whenSavingAValidTransaction_shouldReturnTheTransaction() {
        Transaction transaction = new Transaction();
        transaction.setTimestamp(NOW);

        Transaction savedTransaction = transactionService.save(transaction);

        verify(statisticsServiceMock).add(transaction);
        assertThat(savedTransaction).isEqualTo(transaction);
    }

    @Test
    public void whenSavingATransaction_isValidatedThroughTimeStampValidator() {
        Transaction outdatedTransaction = new Transaction();
        outdatedTransaction.setTimestamp(OUTDATED);

        transactionService.save(outdatedTransaction);

        verify(timestampValidator).assertValid(OUTDATED);
    }



}
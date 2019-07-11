package com.interview.logic.transactions.services;

import com.interview.logic.statistics.services.StatisticsService;
import com.interview.logic.transactions.model.Transaction;
import com.interview.presentation.transactions.time.TimestampValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private StatisticsService statisticsService;
    private TimestampValidator timestampValidator;

    @Autowired
    public TransactionService(StatisticsService statisticsService, TimestampValidator timestampValidator) {
        this.statisticsService = statisticsService;
        this.timestampValidator = timestampValidator;
    }

    public Transaction save(Transaction transaction) {
        assertValid(transaction);

        statisticsService.add(transaction);

        return transaction;
    }

    public void deleteAll() {
        statisticsService.deleteAll();
    }

    private void assertValid(Transaction transaction) {
        timestampValidator.assertValid(transaction.getTimestamp());
    }

}

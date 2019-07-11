package com.interview.logic.statistics.repositories;

import com.interview.logic.statistics.model.Statistic;
import com.interview.logic.transactions.model.Transaction;

public interface StatisticsRepository {
    void add(Transaction transaction);

    Statistic getStatistics();

    void resetAll();
}

package com.interview.helpers;

import com.interview.logic.statistics.BigDecimalSummaryStatistics;
import com.interview.logic.statistics.model.Statistic;
import com.interview.logic.transactions.model.Transaction;

import java.math.BigDecimal;

public class StatisticsTestHelper {

    public static Statistic aValidStatistic(){
        Statistic statistic = new Statistic();
        statistic.setCount(3L);
        statistic.setAvg(new BigDecimal(12.345));
        statistic.setMax(new BigDecimal(20.000));
        statistic.setMin(new BigDecimal(1.102));
        statistic.setSum(new BigDecimal(39.565));
        return statistic;
    }

    public static Statistic statisticsFrom(Transaction... transactions) {
        BigDecimalSummaryStatistics result = new BigDecimalSummaryStatistics();
        for (Transaction t : transactions) {
            result.accept(t.getAmount());
        }
        return result.getStatistics();
    }
}

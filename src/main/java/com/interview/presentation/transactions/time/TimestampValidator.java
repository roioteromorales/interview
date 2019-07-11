package com.interview.presentation.transactions.time;

import com.interview.logic.transactions.exception.FutureTransactionException;
import com.interview.logic.transactions.exception.OutdatedTransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TimestampValidator {
    private static final int SIXTY_SECONDS = 60000;
    public static final int TIME_WINDOW = SIXTY_SECONDS;

    private TimeProvider timeProvider;

    @Autowired
    public TimestampValidator(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    public void assertValid(Instant transactionTimestamp) {
        Instant currentTimestamp = timeProvider.now();
        if (currentTimestamp.isBefore(transactionTimestamp)) {
            String message = "Timestamp " + transactionTimestamp + " is future than " + currentTimestamp;
            throw new FutureTransactionException(message);
        }
        Instant validTimestamp = currentTimestamp.minusMillis(TIME_WINDOW);
        if (transactionTimestamp.isBefore(validTimestamp)) {
            String message = "Timestamp " + transactionTimestamp + " is older than " + validTimestamp;
            throw new OutdatedTransactionException(message);
        }
    }


}

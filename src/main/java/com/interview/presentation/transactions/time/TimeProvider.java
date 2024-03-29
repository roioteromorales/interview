package com.interview.presentation.transactions.time;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TimeProvider {

    public Instant now() {
        return Instant.now().truncatedTo( ChronoUnit.MILLIS );
    }
}

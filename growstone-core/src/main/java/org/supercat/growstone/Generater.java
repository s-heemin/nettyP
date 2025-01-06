package org.supercat.growstone;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Generater {
    private static final AtomicInteger counter = new AtomicInteger();

    public static int generateSessionID() {
        return counter.incrementAndGet();
    }


}

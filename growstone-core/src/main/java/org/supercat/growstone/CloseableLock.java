package org.supercat.growstone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class CloseableLock extends ReentrantLock implements AutoCloseable {
    private static final Logger logger = LoggerFactory.getLogger(CloseableLock.class);
    public static final int TIMEOUT_SECOND = 15;

    public CloseableLock begin() throws InterruptedException {
        try {
            if (this.tryLock(TIMEOUT_SECOND, TimeUnit.SECONDS)) {
                return this;
            } else {
                throw new InterruptedException();
            }
        } catch (InterruptedException e) {
            SLog.logException(e);
            throw e;
        }
    }

    @Override
    public void close() {
        if (this.isHeldByCurrentThread()) {
            this.unlock();
        }

    }

}

package org.supercat.growstone.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.SLog;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

public class EventDispatcher {
    private static final Logger logger = LoggerFactory.getLogger(EventDispatcher.class);

    private final AtomicLong addedCnt = new AtomicLong();
    private final AtomicLong pollCnt = new AtomicLong();
    private final ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<>();

    public void update() {
        if (pollCnt.get() >= addedCnt.get()) {
            return;
        }

        Runnable evt;
        while ((evt = queue.poll()) != null) {
            try {
                evt.run();
            } catch (Exception e) {
                SLog.logException(e);
            } finally {
                pollCnt.incrementAndGet();
            }

        }
    }

    public void addEvent(Runnable c) {
        addedCnt.incrementAndGet();
        queue.add(c);
    }

    public boolean isEmpty() {
        return addedCnt.get() == pollCnt.get();
    }
}

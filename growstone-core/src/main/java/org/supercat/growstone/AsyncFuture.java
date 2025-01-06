package org.supercat.growstone;

import java.util.concurrent.ScheduledFuture;

public class AsyncFuture {
    private final ScheduledFuture future;
    private final Runnable cancellation;

    public AsyncFuture(ScheduledFuture future, Runnable cancellation) {
        this.future = future;
        this.cancellation = cancellation;
    }

    public boolean isDone() {
        return future.isDone();
    }

    public void cancel(boolean mayInterruptIfRunning) {
        future.cancel(mayInterruptIfRunning);
        cancellation.run();
    }
}

package org.supercat.growstone;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.function.Function;

public class Async {
    private static ScheduledExecutorService scheduledExecutors;

    private static ExecutorService logDBExecutor;
    private static ExecutorService loginExecutors;
    private static ExecutorService chatExecutors;

    private static final ArrayList<ExecutorService> sequenceReactionExecutors = new ArrayList<>();

    public static void init() {
        int coreCnt = Runtime.getRuntime().availableProcessors();
        int loginExecutorsCnt = Math.max(32, coreCnt * 2);
        int chatExecutorsCnt = Math.max(32, coreCnt * 2);

        int sequenceReactionThreadCnt = Math.max(32, coreCnt * 4);
        int dbExecutorsCnt = Math.max(1, coreCnt * 32);

        scheduledExecutors = Executors.newScheduledThreadPool(coreCnt);
        logDBExecutor = Executors.newFixedThreadPool(dbExecutorsCnt);
        chatExecutors = Executors.newFixedThreadPool(chatExecutorsCnt);
        Function<String, ThreadFactory> factory = s -> new ThreadFactoryBuilder().setNameFormat(s + "-%d").build();
        loginExecutors = Executors.newFixedThreadPool(loginExecutorsCnt, factory.apply("login"));
        for (int i = 0; i < sequenceReactionThreadCnt; ++i) {
            var seqThreadName = String.format("seq-%d", i);
            var threadFactory = factory.apply(seqThreadName);
            var executor = Executors.newSingleThreadExecutor(threadFactory);
            sequenceReactionExecutors.add(executor);
        }
    }
    private static CompletableFuture<Void> runAsync(Runnable task, Executor service) {
      CompletableFuture.runAsync(() -> {
          safeRun(task);
        }, service);

      return null;
    }

    public static void loginAsync(Runnable task) {
        runAsync(task, loginExecutors);
    }

    public static void chatAsync(Runnable task) {
        runAsync(task, chatExecutors);
    }
    public static void worldAsync(long playerID, Runnable task) {
        int idx = (int) (playerID % sequenceReactionExecutors.size());
        runAsync(task, sequenceReactionExecutors.get(idx));
    }
    private static void safeRun(Runnable task) {
        try {
            task.run();
        } catch (Exception e) {
            SLog.logException(e);
        }
    }
    public static void repeat(Runnable task, long initialDelay, long period, TimeUnit unit) {
        scheduledExecutors.scheduleAtFixedRate(() -> safeRun(task), initialDelay, period, unit);
    }


    public static CompletableFuture<Void> logDBAsync(Runnable task) {
        return runAsync(task, logDBExecutor);
    }

    public static void shutdown() {
        loginExecutors.shutdown();
        sequenceReactionExecutors.forEach(ExecutorService::shutdown);
    }

    public static void delayAsync(Runnable task, long millis) {
        var delayer = CompletableFuture.delayedExecutor(millis, TimeUnit.MILLISECONDS, scheduledExecutors);
        runAsync(task, delayer);
    }
}

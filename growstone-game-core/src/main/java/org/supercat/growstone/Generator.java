package org.supercat.growstone;


public class Generator {
    private final long epoch = 1609459200000L;
    private final long workerIdBits = 5L;
    private final long sequenceBits = 12L;
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private final long workerIdShift = sequenceBits;
    private final long timestampLeftShift = sequenceBits + workerIdBits ;
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long uniqueId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public Generator(long id) {
        if (uniqueId > maxWorkerId || uniqueId < 0) {
            throw new IllegalArgumentException("Worker ID can't be greater than %d or less than 0".formatted(maxWorkerId));
        }

        this.uniqueId = id;
    }

    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards.");
        }

        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - epoch) << timestampLeftShift) |
                (uniqueId << workerIdShift) |
                sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}

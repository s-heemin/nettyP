package org.supercat.growstone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class SavableObject {
    private static final Logger logger = LoggerFactory.getLogger(SavableObject.class);

    private final AtomicInteger changed;

    public SavableObject() {
        changed = new AtomicInteger();
    }

    public final int getChanged() {
        return changed.get();
    }

    public final boolean isChanged() {
        return 0 < getChanged();
    }

    public final void markAsChanged() {
        changed.incrementAndGet();
    }

    // 상속해서 사용할 것
    protected boolean saveInternal() {
        return false;
    }

    // 상속 불가
    public final boolean save() {
        int amt = changed.getAndSet(0);
        boolean saved = false;
        try {
            saved = saveInternal();
        } catch (Exception e) {
            SLog.logException(e);
        } finally {
            if (!saved) {
                changed.addAndGet(amt);
            }
        }

        return saved;
    }
    // 상속 불가
    public final boolean saveIfChanged() {
        if (isChanged()) {
            return save();
        }

        return false;
    }
}

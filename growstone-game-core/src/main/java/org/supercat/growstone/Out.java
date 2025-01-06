package org.supercat.growstone;

import java.util.Objects;
import java.util.Optional;

public final class Out<T> {
    private T value;

    private Out(T defaultValue) {
        this.value = defaultValue;
    }

    public static <T> Out<T> of() {
        return new Out<T>(null);
    }

    public static <T> Out<T> of(T a) {
        return new Out<T>(a);
    }


    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return this.value;
    }

    public boolean isEmpty() {
        return Objects.isNull(value);
    }

    public Optional<T> opt() {
        return Optional.ofNullable(value);
    }
}

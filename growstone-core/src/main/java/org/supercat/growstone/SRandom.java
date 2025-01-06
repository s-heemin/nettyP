package org.supercat.growstone;

import java.util.Random;

public class SRandom {
    public final Random r;

    public SRandom() {
        r = new Random();
    }

    public double random() {
        return r.nextDouble();
    }

    // a (inclusive), b (exclusive)
    public double nextDouble(double a, double b) {
        if (a >= b) {
            return b;
        }

        double c = (b - a) * random();
        return c + a;
    }

    // a (inclusive), b (exclusive)
    public float nextFloat(float a, float b) {
        if (a >= b) {
            return b;
        }

        float c = (float) ((b - a) * random());
        return c + a;
    }

    // a (inclusive), b (exclusive)
    public long nextLong(long a, long b) {
        if (a >= b) {
            return b;
        }

        long c = (long) ((b - a) * random());
        return c + a;
    }


    // a (inclusive), b (exclusive)
    public int nextInt(int a, int b) {
        if (a >= b) {
            return b;
        }

        int c = (int) ((b - a) * random());
        return c + a;
    }

    public boolean nextBoolean() {
        return 1 == nextIntEnd(0, 1);
    }

    // a (inclusive), b (inclusive)
    public int nextIntEnd(int a, int b) {
        if (a >= b) {
            return b;
        }

        int c = (int) ((b - a + 1) * random());
        return c + a;
    }

    // a (inclusive), b (inclusive)
    public long nextLongEnd(long a, long b) {
        if (a >= b) {
            return b;
        }

        long c = (long) ((b - a + 1) * random());
        return c + a;
    }
}

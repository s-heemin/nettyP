package org.supercat.growstone;

public class SRandomUtils {
    private static final SRandom r = new SRandom();

    public static double random() {
        return r.random();
    }

    public static double nextDouble(double a, double b) {
        return r.nextDouble(a, b);
    }

    public static float nextFloat(float a, float b) {
        return r.nextFloat(a, b);
    }

    public static long nextLong(long a, long b) {
        return r.nextLong(a, b);
    }

    public static int nextInt(int a) {
        return r.nextInt(0, a);
    }

    public static int nextInt(int a, int b) {
        return r.nextInt(a, b);
    }

    public static boolean nextBoolean() {
        return r.nextBoolean();
    }

    public static int nextIntEnd(int a) {
        return r.nextIntEnd(0, a);
    }

    public static int nextIntEnd(int a, int b) {
        return r.nextIntEnd(a, b);
    }

    public static long nextLongEnd(long a, long b) {
        return r.nextLongEnd(a, b);
    }
}

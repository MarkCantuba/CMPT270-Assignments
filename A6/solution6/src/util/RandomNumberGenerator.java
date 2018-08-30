package util;

import java.util.Random;

/**
 * A random number generator that is a Singleton.
 */
public class RandomNumberGenerator {
    /** The one instance for this Singleton class. */
    private static RandomNumberGenerator instance = null;

    /**
     * @return the sole random generator
     */
    public static RandomNumberGenerator getInstance() {
        if (instance == null) {
            instance = new RandomNumberGenerator();
        }
        return instance;
    }

    /** The actual random number generator used to generate the numbers. */
    private Random random;

    /**
     * Initialize the random number generator using the current time for the seed value.
     */
    private RandomNumberGenerator() {
        random = new Random(System.currentTimeMillis());
    }

    /**
     * @return The next random int uniformly distributed amongst all possible values.
     */
    public int getInt() {
        return random.nextInt();
    }

    /**
     * @return The next random int uniformly distributed amongst 0 to n-1 (inclusive).
     */
    public int getInt(int n) {
        return random.nextInt(n);
    }

    /**
     * @return The next random long uniformly distributed amongst all possible values.
     */
    public long getLong() {
        return random.nextLong();
    }

    /**
     * @return The next random float value uniformly distributed between 0.0 and 1.0.
     */
    public float getFloat() {
        return random.nextFloat();
    }

    /**
     * @return The next random double uniformly distributed between 0.0 and 1.0.
     */
    public double getDouble() {
        return random.nextDouble();
    }
}

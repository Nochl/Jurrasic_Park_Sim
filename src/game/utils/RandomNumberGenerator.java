package game.utils;

import java.util.Random;

/**
 * Implements a utility class to generate random numbers
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 */
public class RandomNumberGenerator {
    /**
     * Generates a random double between 0 and 1
     * @return a Double that's between 0 and 1
     */
    public static Double randomDouble() {
        return Math.random();
    }

    /**
     * Generates a random double between 0 and 1 that also fits in the range low-high
     * @param low a double containing the lowest bound
     * @param high a double containing the highest bound
     * @return a Double that's in the range low-high and 0-1
     */
    public static Double randomDoubleInRange(Double low, Double high) {
        Random random = new Random();
        return random.nextDouble() * (high - low) + low;
    }
}

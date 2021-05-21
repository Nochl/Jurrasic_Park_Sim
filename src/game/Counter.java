package game;

/**
 * Implements a counter class
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.1
 */
public class Counter {
    /**
     * an int containing the maximum value counter can be
     */
    private int max;
    /**
     * an int containing the current value of counter
     */
    private int value;

    /**
     * Constructor
     * @param max an int denoting the max value of counter
     */
    public Counter(int max) {
        this.max = max;
        value = max;
    }

    /**
     * Decrements the counter's current value by 1
     */
    public void dec() {
        value -= 1;
    }

    /**
     * Gets the value of counter
     * @return an int denoting the value of counter
     */
    public int getValue() {
        return value;
    }

    /**
     * Resets the counter to its max possible value
     */
    public void resetCounter() {
        value = max;
    }
}

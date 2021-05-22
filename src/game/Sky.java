package game;

import game.utils.RandomNumberGenerator;

/**
 * Implements sky static class for game world
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 * @see Counter
 */
public class Sky {
    /**
     * A counter denoting the amount of turns for a chance to rain
     */
    private static Counter rainTurns = new Counter(20);

    private static boolean isRaining = false;

    private static int addedSips = 0;

    /**
     * ticks the sky class every game round
     */
    public static void tick() {
        if (isRaining) {
            isRaining = false;
            addedSips = 0;
        }
        rainTurns.dec();
        if (rainTurns.getValue() <= 0) {
            Double chance = RandomNumberGenerator.randomDouble();
            if (chance <= 0.2) {
                isRaining = true;
                Double range = RandomNumberGenerator.randomDoubleInRange(0.1, 0.6);
                addedSips = (int)Math.round(20 * range);
            }
            rainTurns.resetCounter();
        }
    }

    /**
     * Gets the amount of sips added to lakes. If isRaining is false, returns 0
     * @return an int denoting the amount of sips added to lake
     */
    public static int getRainAmount() {
        return addedSips;
    }
}

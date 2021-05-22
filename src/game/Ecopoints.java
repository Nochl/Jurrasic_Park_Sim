package game;

/**
 * Implementing a currency system (eco points) in the game world
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 */
public class Ecopoints {
    /**
     * an int that denotes the amount of currency
     */
    private int points;

    /**
     * Constructor
     */
    public Ecopoints() {
        this.points = 0;
    }

    /**
     * Increments eco points by given int amount
     * @param points an int of the amount of points to be added
     */
    public void addPoints(int points) {
        this.points += points;
    }

    /**
     * Gets the points
     * @return an int containing the points
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Decrements eco points by given int amount
     * @param points an int of how much points to be removed
     */
    public void removePoints(int points) {
        this.points -= points;
    }
}

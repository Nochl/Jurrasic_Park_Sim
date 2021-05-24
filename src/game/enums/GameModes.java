package game.enums;

/**
 * Implements an enum class the denotes different types of game modes
 */
public enum GameModes {
    SANDBOX("Sandbox"), CHALLENGE("Challenge");

    /**
     * a string containing name
     */
    private final String name;

    /**
     * Private constructor of GameModes
     * @param name name of game mode
     */
    GameModes(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the game mode
     * @return a string containing name of GameMode
     */
    public String getName() {
        return this.name;
    }
}

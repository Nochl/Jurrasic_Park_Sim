package game;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.World;
import game.enums.GameModes;
import game.utils.GetUserInput;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Runs the game and allows the player to choose different game modes
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 * @see World
 * @see Player
 */
public class GameDriver {

    /**
     * Denotes the world the player is set in
     */
    private World world;

    /**
     * Denotes the player character
     */
    private Player player;

    /**
     * An
     */
    private ArrayList<GameModes> gameModes;

    /**
     * Constructor
     * @param world World the player is set in
     * @param player a Player object
     */

    public GameDriver(World world, Player player) {
        this.world = world;
        this.player = player;
        gameModes = new ArrayList<>();
        gameModes.add(null);
        Collections.addAll(gameModes, GameModes.values());
    }

    /**
     * Starts the game
     */
    public void startGame() {
        Display display = new Display();
        boolean continueGame = true;
        do {
            showGameMenu(display);
            setGameMode(display);
            display.println("Starting Game");
            display.endLine();
            world.run();
            display.println("1) Restart\n2) End Game");
            int input = GetUserInput.askForIntInRange(display, 1, 2);
            if (input == 2) {
                continueGame = false;
            }
        } while (continueGame);
    }

    /**
     * Sets the game mode of the game world
     * @param display a Display object
     */
    private void setGameMode(Display display) {
        int gameOption = GetUserInput.askForIntInRange(display, 1, gameModes.size() - 1);
        switch (gameModes.get(gameOption)) {
            case SANDBOX:
                display.println("Sandbox Mode Has Been Chosen");
                player.setTargetEcopoints(Integer.MAX_VALUE);
                player.setTargetMoves(Integer.MAX_VALUE);
                player.addCapability(GameModes.SANDBOX);
                break;
            case CHALLENGE:
                display.println("Challenge Mode Has Been Chosen");
                display.println("Number of Moves Limit");
                int targetMoves = GetUserInput.askForIntInRange(display, 1, 1000);
                display.println("Target Ecopoints");
                int targetEcopoints = GetUserInput.askForIntInRange(display, 1, 5000);
                player.setTargetMoves(targetMoves);
                player.setTargetEcopoints(targetEcopoints);
                player.addCapability(GameModes.CHALLENGE);
                break;
        }
    }

    /**
     * Displays the game start menu where player can see game mode options
     * @param display a Display object that handles I/O
     */
    private void showGameMenu(Display display) {
        if (gameModes.size() == 1) {
            display.println("No Game Modes");
            return;
        }
        display.println("----------------------------------------------");
        display.println("|        Please Choose A Game Mode            |");
        display.println("----------------------------------------------");
        for (int i = 1; i < gameModes.size(); i++) {
            String gameModeName = gameModes.get(i).getName();
            display.println(" " + i + ") " + gameModeName);
        }
    }
}

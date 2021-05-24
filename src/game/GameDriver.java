package game;

import edu.monash.fit2099.engine.*;
import game.enums.GameModes;
import game.ground.*;
import game.utils.GetUserInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Runs the game and allows the player to choose different game modes
 *
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 * @see World
 * @see Player
 */
public class GameDriver {

    /**
     * An array list of different game modes
     */
    private ArrayList<GameModes> gameModes;

    /**
     * Constructor
     */
    public GameDriver() {
        gameModes = new ArrayList<>();
        gameModes.add(null);
        Collections.addAll(gameModes, GameModes.values());
    }

    /**
     * Starts the game
     */
    public void startGame() {
        boolean continueGame = true;
        do {
            Display display = new Display();

            World world = new World(display);

            FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(), new Bush(), new VendingMachine());

            List<String> map = Arrays.asList(
                    ".....#######....................................................................",
                    ".....#.....#....................................................................",
                    ".....#....!#....................................................................",
                    ".....###.###....................................................................",
                    "................................................................................");
            GameMap gameMap = new GameMap(groundFactory, map);
            world.addGameMap(gameMap);

            Player player = new Player("Player", '@', 100);
            world.addPlayer(player, gameMap.at(9, 2));
            EcoHold.addPlayerEco(player);

            player.getEcopoints().addPoints(10000);

            showGameMenu(display);
            setGameMode(display, player);
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
     *
     * @param display a Display object
     */
    private void setGameMode(Display display, Player player) {
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
     *
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

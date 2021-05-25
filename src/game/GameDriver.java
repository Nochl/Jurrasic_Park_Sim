package game;

import edu.monash.fit2099.engine.*;
import game.dinosaur.Brachiosaur;
import game.dinosaur.Dinosaur;
import game.dinosaur.Pterodactyl;
import game.dinosaur.Stegosaur;
import game.enums.GameModes;
import game.ground.*;
import game.utils.GetUserInput;
import game.utils.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Runs the Jurassic World game and allows the player to choose different game modes
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
     * Initialises and starts the game
     */
    public void startGame() {
        boolean continueGame = true;
        do {
            Display display = new Display();
            World world = new World(display);

            FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(),
                    new Tree(), new Bush(), new VendingMachine(), new Lake());

            List<String> map = Arrays.asList(
                    "................................................................................",
                    "................................................................................",
                    ".....#######......~~~~~.........................................................",
                    ".....#_____#.....~~~~~~~........................................................",
                    ".....#____!#.....~~~~~~~........................................................",
                    ".....###.###......~~~~~.........................................................",
                    "................................................................................",
                    "......................................+++.......................................",
                    ".......................................++++.....................................",
                    "....................~~~~~..........+++++........................................",
                    ".....................................++++++.....................................",
                    "......................................+++.......................................",
                    ".....................................+++........................................",
                    "................................................................................",
                    "............+++..............~~~................................................",
                    ".............+++++..........~~~~~...............................................",
                    "...............++..........~~~~~~~.......................+++++..................",
                    ".............+++...........~~~~~~~..................++++++++....................",
                    "............+++.............~~~~~.....................+++.......................",
                    ".............................~~~................................................",
                    ".........................................................................++.....",
                    "........................................................................++.++...",
                    ".........................................................................++++...",
                    "..........................................................................++....",
                    "................................................................................");
            GameMap gameMap = new GameMap(groundFactory, map );
            world.addGameMap(gameMap);
            MapHolder.addMap(gameMap);

            List<String> map2 = Arrays.asList(
                    "................................................................................",
                    "................................................................................",
                    ".........++.....................................................................",
                    "..........+...........................................................++........",
                    "....................................................................++++........",
                    "................................................................................",
                    "................................~~~~~~~~~~~~....................................",
                    ".................+++........~~~~~~~~~~~~~~~~~~~.................................",
                    ".................+++.....~~~~~~~~~~~~~~~~~~~~~~~~~..............................",
                    ".......................~~~~~~~~~~~~~~~~~~~~~~~~~~~~.............................",
                    ".......................~~~~~~~~~~~~~~~~~~~~~~~~~~~~.............................",
                    ".........................~~~~~~~~~~~~~~~~~~~~~~~~~..............................",
                    "............................~~~~~~~~~~~~~~~~~~~.................................",
                    "........+.......................~~~~~~~~~~~~....................................",
                    "........++......................................................................",
                    "................................................................................",
                    "................................................................................",
                    "................................................................................",
                    "................+..................................................++...........",
                    "...............+.+................................................++++++........",
                    "................+...................................................++++........",
                    "................................................................................",
                    "................................................................................",
                    "................................................................................");

            GameMap gameMap2 = new GameMap(groundFactory, map2 );
            world.addGameMap(gameMap2);
            MapHolder.addMap(gameMap2);

            Player player = new Player("Player", '@', 100);
            world.addPlayer(player, gameMap.at(9, 4));
            EcoHold.addPlayerEco(player);

            MapSwitch.addSwitch(gameMap, "top");
            MapSwitch.addSwitch(gameMap2, "bottom");

            // Initialise Dinosaurs
            Dinosaur stegosaur1 = new Stegosaur("Stegosaur", false, 'm');
            Dinosaur stegosaur2 = new Stegosaur("Stegosaur",false, 'f');
            Dinosaur stegosaur3 = new Stegosaur("Stegosaur",false, 'f');
            Dinosaur stegosaur4 = new Stegosaur("Stegosaur",false, 'm');
            Dinosaur pterodactyl1 = new Pterodactyl("Pterodactyl",false, 'm');
            Dinosaur pterodactyl2 = new Pterodactyl("Pterodactyl",false, 'f');
            Dinosaur pterodactyl3 = new Pterodactyl("Pterodactyl",false, 'm');
            Dinosaur pterodactyl4 = new Pterodactyl("Pterodactyl",false, 'f');
            Dinosaur brachiosaur1 = new Brachiosaur("Brachiosaur", false, 'm');
            Dinosaur brachiosaur2 = new Brachiosaur("Brachiosaur", false, 'f');
            Dinosaur brachiosaur3 = new Brachiosaur("Brachiosaur", false, 'm');
            Dinosaur brachiosaur4 = new Brachiosaur("Brachiosaur", false, 'f');

            // Add Dinosaurs to game map
            initialiseDinosaur(gameMap, 30, 12, stegosaur1);
            initialiseDinosaur(gameMap, 32, 12, stegosaur2);
            initialiseDinosaur(gameMap, 10, 10, brachiosaur1);
            initialiseDinosaur(gameMap, 11, 11, brachiosaur2);
            initialiseDinosaur(gameMap, 20, 20, pterodactyl3);
            initialiseDinosaur(gameMap, 20, 21, pterodactyl4);

            // Add Dinosaur to game map
            initialiseDinosaur(gameMap2, 32, 20, pterodactyl1);
            initialiseDinosaur(gameMap2, 30, 20, pterodactyl2);
            initialiseDinosaur(gameMap2, 12, 10, stegosaur3);
            initialiseDinosaur(gameMap2, 12, 12, stegosaur4);
            initialiseDinosaur(gameMap2, 21, 15, brachiosaur3);
            initialiseDinosaur(gameMap2, 20, 15, brachiosaur4);



            showGameMenu(display);
            setGameMode(display, player);
            display.println("Starting Game");
            display.endLine();

            world.run();
            display.endLine();
            display.println("1) Restart\n2) End Game");
            int input = GetUserInput.askForIntInRange(display, 1, 2);
            if (input == 2) {
                continueGame = false;
            }
        } while (continueGame);

    }

    /**
     * Adds dinosaur into game map at location x and y and adds it into DinosaurHold
     * @param map a GameMap object
     * @param x an x coordinate in gamemap
     * @param y a y coordinate in game map
     * @param dinosaur a Dinosaur Object
     */
    private void initialiseDinosaur(GameMap map, int x, int y, Dinosaur dinosaur) {
        DinosaurHold.addDinosaur(dinosaur);
        if (!map.at(x, y).containsAnActor()) {
            map.at(x, y).addActor(dinosaur);
        }
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
                int targetMoves = GetUserInput.askForIntInRange(display, 1, 10000);
                display.println("Target Ecopoints");
                int targetEcopoints = GetUserInput.askForIntInRange(display, 1, 100000);
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

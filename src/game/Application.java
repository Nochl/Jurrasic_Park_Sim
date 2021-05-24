package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;
import game.dinosaur.*;
import game.dinosaur.Stegosaur;
import game.ground.*;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

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
				"...................................+++++........................................",
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
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
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

		EcoHold.addWorldEco(500);

		// Place a pair of stegosaurs in the middle of the map
		Dinosaur dinosaur1 = new Stegosaur("Stegosaur", false, 'm');
		Dinosaur dinosaur2 = new Stegosaur("Stegosaur",false, 'f');
		gameMap.at(30, 12).addActor(dinosaur1);
		gameMap.at(32, 12).addActor(dinosaur2);
		DinosaurHold.addDinosaur(dinosaur1);
		DinosaurHold.addDinosaur(dinosaur2);
		world.run();
	}

}

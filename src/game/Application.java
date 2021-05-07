package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.consumable.Fruit;
import game.dinosaur.Stegosaur;
import game.ground.*;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(), new Bush());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		".....#######....................................................................",
		".....#_____#....................................................................",
		".....#_____#....................................................................",
		".....###.###....................................................................",
		"................................................................................",
		"......................................+++.......................................",
		".......................................++++.....................................",
		"...................................+++++........................................",
		".....................................++++++.....................................",
		"......................................+++.......................................",
		".....................................+++........................................",
		"................................................................................",
		"............+++.................................................................",
		".............+++++..............................................................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		GameMap gameMap = new GameMap(groundFactory, map );
		world.addGameMap(gameMap);
		
		Player player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(9, 4));
		EcoHold.addPlayerEco(player.getEcopoints());
		player.addItemToInventory(new Fruit());

		// Place a pair of stegosaurs in the middle of the map
		gameMap.at(30, 12).addActor(new Stegosaur("Stegosaur"));
		gameMap.at(32, 12).addActor(new Stegosaur("Stegosaur"));
		gameMap.at(10, 4).addActor(new Stegosaur("Stegosaur"));
			
		world.run();
	}
}

package game;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.dinosaur.Allosaur;
import game.dinosaur.Dinosaur;
import game.dinosaur.Stegosaur;
import game.ground.*;

import java.util.Arrays;
import java.util.List;

public class Testing {
    public static void main(String[] args) {
        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(), new Bush(), new VendingMachine());

        List<String> map = Arrays.asList(
                ".....#######....................................................................",
                ".....#.....#....................................................................",
                ".....#.....#....................................................................",
                ".....###.###....................................................................",
                "................................................................................");
        GameMap gameMap = new GameMap(groundFactory, map );
        world.addGameMap(gameMap);

        Player player = new Player("Player", '@', 100);
        world.addPlayer(player, gameMap.at(15, 4));
        EcoHold.addPlayerEco(player);


        // Place a pair of stegosaurs in the middle of the map
        ////////////////////////////////////////////////////////
        Dinosaur dinosaur1 = new Allosaur("Allosaur", false, 'm');

        Dinosaur dinosaur2 = new Stegosaur("Stegosaur",false, 'f');
        gameMap.at(8, 1).addActor(dinosaur1);
        gameMap.at(9, 2).addActor(dinosaur2);
        DinosaurHold.addDinosaur(dinosaur1);
        DinosaurHold.addDinosaur(dinosaur2);
        world.run();

    }
}

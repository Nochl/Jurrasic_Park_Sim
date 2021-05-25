package game.ground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.EcoHold;
import game.actions.NextMapAction;
import game.actions.PickFruitAction;
import game.consumable.Fruit;
import game.enums.FruitCapabilities;
import game.enums.GroundTypeCapabilities;
import game.enums.MapCapabilities;

import java.util.ArrayList;

/**
 * Implements a tree ground object
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 2.0.0
 * @see Ground
 */
public class Tree extends Ground {
	/**
	 * an int denoting age of tree
	 */
	private int age = 0;

	/**
	 * An array list of Fruit denoting the fruits in tree
	 */
	private ArrayList<Fruit> inTree = new ArrayList<>();

	/**
	 * Constructor
	 */
	public Tree() {
		super('+');
		addCapability(GroundTypeCapabilities.TREE);
	}

	/**
	 * gets all the allowable actions that can be done to tree object
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return an Actions containing all the allowable actions that can be done to tree object
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		Actions actions = new Actions();
		if (!inTree.isEmpty()) {
			actions.add(new PickFruitAction(inTree));
		}

//		if (hasCapability(MapCapabilities.EDGEMAP)){
//			actions.add(new NextMapAction());
//		}
		return actions;
	}

	/**
	 * Ticks tree object
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {
		double prob = Math.random();
		super.tick(location);
		if (prob < 0.5) {
			EcoHold.addWorldEco(1);
			inTree.add(new Fruit(FruitCapabilities.IN_TREE));
		}
		if (inTree.size() > 0) {
			if (prob < 0.05) {
				Fruit fruit = inTree.remove(0);
				fruit.removeCapability(FruitCapabilities.IN_TREE);
				fruit.addCapability(FruitCapabilities.ON_FLOOR);
				location.addItem(fruit);
			}
		}


		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';
	}
}

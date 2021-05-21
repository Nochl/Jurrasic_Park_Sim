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
import game.enums.MapCapabilities;

import java.util.ArrayList;

public class Tree extends Ground {
	private int age = 0;
	private ArrayList<Fruit> inTree = new ArrayList<>();
	public Tree() {
		super('+');
	}

	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		Actions actions = new Actions();
		if (!inTree.isEmpty()) {
			actions.add(new PickFruitAction(inTree));
		}

		if (hasCapability(MapCapabilities.EDGEMAP)){
			actions.add(new NextMapAction());
		}
		return actions;
	}

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

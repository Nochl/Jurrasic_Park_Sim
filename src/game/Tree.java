package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.consumable.Fruit;

import java.util.ArrayList;

public class Tree extends Ground {
	private int age = 0;
	private ArrayList<Fruit> inTree = new ArrayList<>();
	public Tree() {
		super('+');
	}

	@Override
	public void tick(Location location) {
		double prob = Math.random();
		super.tick(location);
		if (prob < 0.5) {
			inTree.add(new Fruit());
		}
		if (inTree.size() > 0) {
			if (prob < 0.05) {
				location.addItem(inTree.remove(0));
			}
		}


		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';
	}
}

package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.consumable.Fruit;
import game.enums.FruitCapabilities;

import java.util.ArrayList;
import java.util.Random;

public class Tree extends Ground {
	private int age = 0;
	private ArrayList<Item> fruits;

	public Tree() {
		super('+');
	}

	@Override
	public void tick(Location location) {
		super.tick(location);
		maybeGrowFruit(location);
		maybeDropFruit();

		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';
	}

	private void maybeGrowFruit(Location location) {
		double chance = Math.random();
		if (chance <= 0.5) {
			Fruit fruit = new Fruit();
			fruits.add(fruit);
			location.addItem(fruit);
		}
	}

	private void maybeDropFruit() {
		double chance = Math.random();
		int fruitSize = fruits.size();
		if (chance < 0.05 && fruitSize != 0) {
			Random r = new Random();
			int index =  r.nextInt(fruitSize);
			Item fruit = fruits.get(index);
			fruit.removeCapability(FruitCapabilities.ON_FLOOR);
			fruits.remove(index);
		}
	}
}

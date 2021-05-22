package game.dinosaur;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Counter;
import game.actions.AttackAction;
import game.actions.FeedingAction;
import game.enums.DietCapabilities;
import game.enums.DinosaurCapabilities;
import game.enums.DinosaurState;

/**
 * Implements Allosaur that extends from Dinosaur Class, that is capable of attacking stegosaurs,
 * hunting meat items mating
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 4.1.0
 * @see Dinosaur
 * @see Counter
 */
public class Brachiosaur extends Dinosaur{
    /**
     * Constructor method for Brachiosaur
     *
     * @param name the name of the Actor
     * @param baby a boolean value that denotes if the brachiosaur being created starts as a baby
     */
    public Brachiosaur(String name, Boolean baby) {
        super(name, 'B', 100, baby);
        maxunconsciousTime = 20;
        maxThirst = 200;
        thirst = 60;
        addCapability(DietCapabilities.HERBIVORE);
        addCapability(DinosaurCapabilities.BRACHIOSAUR);
        if (baby) {setBabyAttributes();}
        else {growUp();}
    }

    /**
     * Constructor method for Brachiosaur
     * @param name a string name of the dinosaur
     * @param baby a boolean if the dinosaur is a baby
     * @param gender a char to denote that it is 'm' male or 'f' female
     */
    public Brachiosaur(String name, Boolean baby, char gender) {
        super(name, 'B', 100, baby, gender);
        maxunconsciousTime = 20;
        maxThirst = 200;
        thirst = 60;
        addCapability(DietCapabilities.HERBIVORE);
        addCapability(DinosaurCapabilities.BRACHIOSAUR);
        if (baby) {setBabyAttributes();}
        else {growUp();}
    }

    /**
     * Changes the attributes of the dinosaur into those of a baby brachiosaur
     */
    @Override
    void setBabyAttributes() {
        maxHitPoints = 80;
        hungryHealth = 40;
        breedingHealth = Integer.MAX_VALUE;
        mateTime = Integer.MAX_VALUE;
        matureCounter = new Counter(50);
        addCapability(DinosaurState.BABY);
    }

    @Override
    Counter getAttackTimeoutCounter() {
        return new Counter(50);
    }

    /**
     * Sets the attributes of the dinosaur object to those of a Adult brachiosaur
     */
    @Override
    void growUp() {
        maxHitPoints = 160;
        hungryHealth = 140;
        breedingHealth = 70;
        mateTime = 30;
    }
}

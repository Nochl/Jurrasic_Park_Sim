package game.dinosaur;

import edu.monash.fit2099.engine.*;
import game.Counter;
import game.behaviour.HuntingBehaviour;
import game.enums.DietCapabilities;
import game.enums.DinosaurCapabilities;
import game.enums.DinosaurState;

/**
 * Implements Allosaur that extends from Dinosaur Class, that is capable of attacking stegosaurs,
 * hunting meat items mating
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 4.0.0
 * @see Dinosaur
 * @see Counter
 */
public class Allosaur extends Dinosaur{

    /**
     * Constructor method for Allosaur
     *
     * @param name the name of the Actor
     * @param baby a boolean value that denotes if the allosaur being created starts as a baby
     */
    public Allosaur(String name, Boolean baby) {
        super(name, 'A', 100, baby);
        maxunconsciousTime = 20;
        addCapability(DietCapabilities.CARNIVORE);
        addCapability(DinosaurCapabilities.ALLOSAUR);
        if (baby) {setBabyAttributes();}
        else {growUp();}
    }

    /**
     * Constructor method for Allosaur
     * @param name a string name of the dinosaur
     * @param baby a boolean if the dinosaur is a baby
     * @param gender a char to denote that it is 'm' male or 'f' female
     */
    public Allosaur(String name, Boolean baby, char gender) {
        super(name, 'A', 100, baby, gender);
        behaviours.add(new HuntingBehaviour());
        maxunconsciousTime = 20;
        addCapability(DietCapabilities.CARNIVORE);
        addCapability(DinosaurCapabilities.ALLOSAUR);
        if (baby) {setBabyAttributes();}
        else {growUp();}

    }


    /**
     * returns an IntrinsicWeapon object specialised for allosaur
     * @return an IntrinsicWeapon object
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(20, "gouges");
    }

    /**
     * Creates a Counter to denote actors attack timeout
     * @return a Counter object of amount of turns in timeout
     */
    @Override
    Counter getAttackTimeoutCounter() {
        return new Counter(20);
    }

    /**
     * Changes the attributes of the dinosaur into those of a adult dinosaur
     */
    @Override
    void growUp() {
        maxHitPoints = 100;
        hungryHealth = 70;
        breedingHealth = 50;
        mateTime = 20;
    }

    /**
     * Sets the attributes of the dinosaur object to those of a child dinosaur
     */
    @Override
    void setBabyAttributes() {
        hitPoints = 25;
        maxHitPoints = 50;
        hungryHealth = 25;
        breedingHealth = Integer.MAX_VALUE;
        mateTime = Integer.MAX_VALUE;
        matureCounter = new Counter(40);
        addCapability(DinosaurState.BABY);
    }

}

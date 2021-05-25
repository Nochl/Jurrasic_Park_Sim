package game.dinosaur;

import com.sun.source.doctree.HiddenTree;
import edu.monash.fit2099.engine.*;

import game.Sky;
import game.actions.*;
import game.behaviour.*;
import game.Counter;
import game.consumable.Corpse;
import game.consumable.eggs.Egg;
import game.enums.*;

import java.util.ArrayList;
import java.util.HashMap;

import static edu.monash.fit2099.engine.Display.*;

/**
 * Implements a Dinosaur class that extends from actor.
 *
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 6.1.0
 * @see Actor
 * @see Counter
 * @see Behaviour
 */
public abstract class Dinosaur extends Actor {
    /**
     * An Array list containing Behaviour objects that denote the different behaviours
     * dinosaur do
     */
    protected ArrayList<Behaviour> behaviours = new ArrayList<>();
    /**
     * HP threshold when dinosaur is hungry
     */
    protected int hungryHealth;
    /**
     *  HP threshold when dinosaur can breed
     */
    protected int breedingHealth;
    /**
     * Hashmap of other dinosaurs who have attacked this dinosaur
     */
    protected HashMap<Actor, Counter> dinosaurAttackers;
    /**
     * Cooldown timer for when dinosaur can breed again
     */
    protected Counter canBreed;
    /**
     * Counter which records how long dinosaur has been unconcious
     */
    protected Counter unconsciousTime;
    /**
     * Dinosaur mating cooldown amount
     */
    protected int mateTime;
    /**
     * Max time dinosaur can be unconscious until it dies
     */
    protected int maxunconsciousTime;
    /**
     * Countdown until baby dinosaur matures
     */
    protected Counter matureCounter = null;
    /**
     * Dinosaur Max water capacity
     */
    protected int maxWater;
    /**
     * Dinosaur current water level
     */
    protected int water;
    /**
     * Dinosaur pregnancy timer before they can lay the egg
     */
    protected Counter pregTime = null;


    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */

    public Dinosaur(String name, char displayChar, int hitPoints, Boolean baby) {
        super(name, displayChar, hitPoints);
        addBehaviours();
        dinosaurAttackers = new HashMap<>();
        canBreed = new Counter(mateTime);
        double random = Math.random();

        // Sets dinosaur gender randomly
        if (random < 0.5) {
            addCapability(Gender.MALE);
        } else {
            addCapability(Gender.FEMALE);
        }

    }

    /**
     * Constructor for dinosaur
     * @param name name of actor
     * @param displayChar a char denoting display character
     * @param hitPoints an int denoting dinosaur's hit points
     * @param baby a boolean denoting if the dinosaur initialised should be a baby version
     * @param gender a char denoting the gender of the dinosaur
     */
    public Dinosaur(String name, char displayChar, int hitPoints, Boolean baby, char gender) {
        super(name, displayChar, hitPoints);
        addBehaviours();
        dinosaurAttackers = new HashMap<>();
        canBreed = new Counter(mateTime);

        // Sets dinosaur gender according to input
        if (gender == 'm') {
            addCapability(Gender.MALE);
        } else {
            addCapability(Gender.FEMALE);
        }
    }

    /**
     * Runs the Dinosaurs functionality and behaviours each turn
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an Action describing what the dinosaur does in the round
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

        // Display if Dinosaur is Hungry
        if (hitPoints < hungryHealth && !hasCapability(HungryCapabilities.HUNGRY)) {
            addCapability(HungryCapabilities.HUNGRY);
            display.println(this+" at "+map.locationOf(this).x()+","+map.locationOf(this).y()+" is hungry!");
        }

        // Removes hungry status (if hunger above an amount)
        if (hitPoints > hungryHealth && hasCapability(HungryCapabilities.HUNGRY)) {
            removeCapability(HungryCapabilities.HUNGRY);
        }

        // Check/Display if Dinosaur is Thirsty
        if (water < 40 && !hasCapability(ThirstCapabilities.THIRSTY)) {
            addCapability(ThirstCapabilities.THIRSTY);
            display.println(this+" at "+map.locationOf(this).x()+","+map.locationOf(this).y()+" is thirsty!");
        }

        // Removes thirsty status (if water above 40)
        if (water > 40 && hasCapability(ThirstCapabilities.THIRSTY)) {
            removeCapability(ThirstCapabilities.THIRSTY);
        }

        // Check to add mating status
        if (!hasCapability(Mateable.MATEABLE) && !hasCapability(DinosaurState.BABY) && canBreed == null) {
            addCapability(Mateable.MATEABLE);

        // Check to remove mating status
        } else if (hasCapability(Mateable.MATEABLE) && canBreed != null) {
            removeCapability(Mateable.MATEABLE);
        }

        // Check if its raining to revive dinosaur
        if (!isConscious() && Sky.getRainAmount() > 0) {
            water = 10;
            hitPoints = 50;
            display.println(this.toString()+" has been revived by the rain!");
        }

        // Running dinosaur functions (if its conscious)
        if (isConscious()) {
            unconsciousTime = null;
            // Decrementing Health and Thirst
            this.hurt(1);
            this.thirst(1);

            // If water level goes to 0, make dinosaur unconscious by reducing its health to 0
            if (water <= 0) {
                hitPoints = 0;
            }

            // Decrement breeding cooldown (if one exists)
            if (canBreed != null) {
                canBreed.dec();
                if (canBreed.getValue() < 1) {
                    canBreed = null;
                }
            }


            for (Actor dinosaur : dinosaurAttackers.keySet()) {
                Counter attackTimer = dinosaurAttackers.get(dinosaur);
                attackTimer.dec();
                if (attackTimer.getValue() == 0) {
                    dinosaurAttackers.remove(dinosaur);
                }
            }


            // check if dinosaur should grow
            if (hasCapability(DinosaurState.BABY)) {
                matureCounter.dec();
                if (matureCounter.getValue() == 0) {
                    matureCounter = null;
                    removeCapability(DinosaurState.BABY);
                    addCapability(DinosaurState.ADULT);
                    growUp();
                }
            }


            // Lays egg if there is one
            if (pregTime != null) {
                if (pregTime.getValue() < 0) {

                    // if dinosaur is Pterodactyl
                    if (this.hasCapability(DinosaurCapabilities.PTERODACTYL)) {
                        if (!map.locationOf(this).getGround().hasCapability(GroundTypeCapabilities.TREE)) {
                            return new GotoTreeBehaviour().getAction(this, map, actions);
                        }
                        else {
                            pregTime = null;
                            return new LayEggAction();
                        }
                    }
                    else {
                        pregTime = null;
                        return new LayEggAction();
                    }
                }
                else {
                    pregTime.dec();
                }
            }

            // Runs all behaviours, uses the one which is not null
            for (Behaviour thisbehaviour : behaviours) {
                Action action = thisbehaviour.getAction(this, map, actions);
                if (action != null) {
                    if (action instanceof MatingAction) {
                        resetMateTime();
                    }
                    return action;
                }
                // If theres no available behaviours, return a new DoNothingAction
            }  return new DoNothingAction();
        }

        // Dinosaur dies if unconscious for too long
        if (unconsciousCheck(map)) {
            return new DeadAction();
        }

        // if unconscious
        return new UnconsciousAction();
    }

    /**
     * Adds a dinosaur into the hash map of dinosaurs actors that have attacked this dinosaur
     * @param dinosaur a Dinosaur object that has attacked this dinosaur
     */
    public void addAttacker(Dinosaur dinosaur) {
        dinosaurAttackers.put(dinosaur, getAttackTimeoutCounter());
    }

    /**
     * returns true if the given dinosaur is able to attack this dinosaur. Else, false
     * @param dinosaur a Dinosaur object
     * @return a boolean denoting if the dinosaur has attacked this dinosaur
     */
    public boolean isCurrentlyTimedOut(Actor dinosaur) {
        Counter attackTimeout = dinosaurAttackers.get(dinosaur);
        return attackTimeout != null;
    }

    /**
     * create's and gets a counter for the specific dinosaur's attack timeout
     * @return a Counter of attack timeout
     */
    abstract Counter getAttackTimeoutCounter();

    /**
     * Resets the counter in which the dinosaur is able to breed
     */
    public void resetMateTime() {
        canBreed = new Counter(mateTime);
    }

    /**
     * checks if unconscious time has run out, and if true, kills the dinosaur and
     * creates a corpse and returns true. if unsconciousTime is still over 0, returns false
     * If it rains, Dinosaur gets revived
     * @param map map in which the dinosaur is in
     * @return a boolean true if dinosaur is still unconscious. false if dinosaur is dead
     */
    public boolean unconsciousCheck(GameMap map) {
        if (unconsciousTime == null) {
            unconsciousTime = new Counter(maxunconsciousTime);

        } else {
            unconsciousTime.dec();
        }

        if (unconsciousTime.getValue() <= 0) {
            Location location = map.locationOf(this);
            map.removeActor(this);
            location.addItem(new Corpse((this.name + " Corpse"), this));
            return true;
        }
        return false;
    }

    /**
     * Gets all the allowable actions that can be done to the dinosaur
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return an Actions object denoting the allowable actions
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions action = new Actions();
        action.add(new FeedingAction(this));
        if (isConscious()) {
            action.add(new AttackAction(this));
        }

        if (hasCapability(Mateable.MATEABLE)) {
            action.add(new MatingAction(this));
        }

        return action;
    }

    abstract void growUp();

    abstract void setBabyAttributes();

    /**
     * Adds all the behaviours of dinosaur
     */
    public void addBehaviours() {
        behaviours.add(new GotoTreeBehaviour());
        behaviours.add(new ThirstyBehaviour());
        behaviours.add(new ScavengingBehaviour());
        behaviours.add(new HuntingBehaviour());
        behaviours.add(new BreedingBehaviour());
        behaviours.add(new WanderBehaviour());
    }

    /////////////////////////////////////////////////////////////////////
    /**
     * Add water points to the current Actor's water total.
     *
     * This cannot take the water over the Actor's maximum. If there is an
     * overflow, water are silently capped at the maximum.
     *
     * Does not check for consciousness: unconscious Actors can still be healed
     * if the game client allows.
     *
     * @param points number of water to add.
     */
    public void drink(int points) {
        water += points;
        water = Math.min(water, maxWater);
    }

    /**
     * To reduce water level on dinosaur
     *
     * If the Actor's water level goes down to zero, it will be knocked out (by making its health 0).
     *
     * @param points number of water points to deduct.
     */
    public void thirst(int points) {
        water -= points;
    }

    /**
     * Gets the Egg from the dinosaur
     * @return Item egg
     */
    public Item getEgg() {
        for (Item item:this.getInventory()) {
            if (item.hasCapability(ItemTypeCapabilities.EGG)) {
                return item;
            }
        } return null;
    }

}


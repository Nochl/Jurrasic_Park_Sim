package game.dinosaur;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

import game.Behaviour;
import game.Counter;
import game.behaviours.WanderBehaviour;
import game.actions.FeedingAction;
import game.enums.DinosaurState;
import game.enums.Mateable;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Dinosaur extends Actor {
    protected ArrayList<Behaviour> behaviours = new ArrayList<>();
    protected int hungryHealth;
    protected int breedingHealth;
    protected HashMap<Actor, Counter> dinosaurAttackers;
    protected Counter canBreed;
    protected int mateTime;
    protected Counter matureCounter = null;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Dinosaur(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        behaviours.add(new WanderBehaviour());
        dinosaurAttackers = new HashMap<>();
        canBreed = new Counter(mateTime);
        addCapability(DinosaurState.BABY);
    }

    public Dinosaur(String name, char displayChar, int hitPoints, DinosaurState dinosaurState) {
        super(name, displayChar, hitPoints);
        behaviours.add(new WanderBehaviour());
        dinosaurAttackers = new HashMap<>();
        canBreed = new Counter(mateTime);
        addCapability(dinosaurState);
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions action = new Actions();
        action.add(new FeedingAction(this));
        return action;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        this.hurt(1);
        canBreed.dec();
        if (canBreed.getValue() < 1){
            canBreed = null;
            addCapability(Mateable.MATEABLE);
        }

        for (Actor dinosaur : dinosaurAttackers.keySet()) {
            Counter attackTimer = dinosaurAttackers.get(dinosaur);
            attackTimer.dec();
            if (attackTimer.getValue() == 0) {
                dinosaurAttackers.remove(dinosaur);
            }
        }

        if (hasCapability(DinosaurState.BABY)) {
            matureCounter.dec();
            if (matureCounter.getValue() == 0) {
                matureCounter = null;
                removeCapability(DinosaurState.BABY);
                addCapability(DinosaurState.ADULT);
                growUp();
            }
        }

        for (Behaviour thisbehaviour : behaviours) {
            Action action = thisbehaviour.getAction(this, actions, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    public void addAttacker(Dinosaur dinosaur) {dinosaurAttackers.put(dinosaur, getAttackTimeoutCounter());
    }

    public boolean isCurrentlyTimedOut(Actor dinosaur) {
        Counter attackTimeout = dinosaurAttackers.get(dinosaur);
        return attackTimeout != null;
    }

    abstract Counter getAttackTimeoutCounter();

    public void resetMateTime(){
        canBreed = new Counter(mateTime);
    }

    abstract void growUp();

    abstract void setBabyAttributes();

}


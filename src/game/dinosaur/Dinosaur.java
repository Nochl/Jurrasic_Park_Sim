package game.dinosaur;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

import game.behaviour.Behaviour;
import game.Counter;
import game.behaviour.WanderBehaviour;
import game.actions.FeedingAction;
import game.enums.Mateable;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Dinosaur extends Actor {
    protected ArrayList<Behaviour> behaviours = new ArrayList<Behaviour>();
    protected int hungryhealth;
    protected int breedinghealth;
    protected HashMap<Dinosaur, Counter> dinosaurAttackers;
    protected Counter canBreed;
    protected Counter unconsciousTime;
    protected int mateTime;
    protected int maxunconsciousTime = Integer.MAX_VALUE;


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

    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions action = new Actions();
        action.add(new FeedingAction(this));
        return action;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (isConscious()) {
            unconsciousTime = null;
            this.hurt(1);
            canBreed.dec();
            if (canBreed.getValue() < 1) {
                canBreed = null;
                addCapability(Mateable.MATEABLE);
            }

            for (Dinosaur dinosaur : dinosaurAttackers.keySet()) {
                Counter attackTimer = dinosaurAttackers.get(dinosaur);
                attackTimer.dec();
                if (attackTimer.getValue() == 0) {
                    dinosaurAttackers.remove(dinosaur);
                }
            }


            for (Behaviour thisbehaviour : behaviours) {
                Action action = thisbehaviour.getAction(this, map, actions);
                if (action != null)
                    return action;
            }
        }
        if (unconsciousTime == null){
            unconsciousTime = new Counter(maxunconsciousTime);
        }
        else{
            unconsciousTime.dec();
        }

        if (unconsciousTime.getValue() <0) {
            map.removeActor(this);
        }
        return new DoNothingAction();
    }

    public void addAttacker(Dinosaur dinosaur) {dinosaurAttackers.put(dinosaur, createTimeoutCounter());
    }

    public boolean isCurrentlyTimedOut(Actor dinosaur) {
        Counter attackTimeout = dinosaurAttackers.get(dinosaur);
        return attackTimeout != null;
    }

    abstract Counter createTimeoutCounter();

    public void resetMateTime(){
        canBreed = new Counter(mateTime);
    }
}


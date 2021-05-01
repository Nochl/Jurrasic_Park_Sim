package game.dinosaur;

import edu.monash.fit2099.engine.IntrinsicWeapon;

public class Allosaur extends Dinosaur{
    /**
     * Constructor.
     *
     * @param name the name of the Actor
     */
    public Allosaur(String name) {
        super(name, 'A', 100);
        maxHitPoints = 100;
        hungryhealth = 140;
        breedinghealth = 50;
    }
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(20, "gouges");
    }
}

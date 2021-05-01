package game.dinosaur;

public class Brachiosaur extends Dinosaur{
    /**
     * Constructor.
     *
     * @param name the name of the Actor
     */
    public Brachiosaur(String name) {
        super(name, 'B', 100);
        maxHitPoints = 160;
        hungryhealth = 140;
        breedinghealth = 70;
    }
}

package game.dinosaur;

public class Stegosaur extends Dinosaur{
    /**
     * Constructor.
     *
     * @param name the name of the Actor
     */
    public Stegosaur(String name) {
        super(name, 'S', 50);
        maxHitPoints = 100;
        hungryhealth = 90;
        breedinghealth = 50;
    }
}

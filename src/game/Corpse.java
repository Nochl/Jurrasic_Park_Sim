package game;

import game.PortableItems.PortableItem;

public class Corpse extends PortableItem {
    public Corpse(String name, char displayChar) {
        super("dead " + name, '%');
    }
}

package game.actors.classes;

import game.actors.Player;
import game.weapons.Club;

/**
 * A class that represents the Wrench class.
 * @author danny leung
 * @see Player
 */
public class Wrench extends Player{
    public static final int WRENCH_MAX_HIT_POINTS = 100000;
    /**
     * Constructor.
     * Creates a new Wrench player with 414 hit points and a Club.
     */
    public Wrench() {
        super(WRENCH_MAX_HIT_POINTS);
        addWeaponToInventory(new Club());
    }
}

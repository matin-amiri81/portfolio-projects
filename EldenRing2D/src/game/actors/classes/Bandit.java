package game.actors.classes;

import game.actors.Player;
import game.weapons.GreatKnife;

/**
 * A class that represents the Bandit class.
 * @author danny leung
 * @see Player
 */
public class Bandit extends Player{
    public static final int BANDIT_MAX_HIT_POINTS = 414;

    /**
     * Constructor.
     * Creates a new Bandit player with 414 hit points and a Great Knife.
     */
    public Bandit() {
        super(BANDIT_MAX_HIT_POINTS);
        this.addWeaponToInventory(new GreatKnife());
    }
}

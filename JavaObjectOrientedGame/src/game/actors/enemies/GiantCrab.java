package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.RandomNumberGenerator;
import game.weapons.GiantClaw;
import game.weapons.GiantPincer;

/**
 * A class represents a Giant Crab.
 * @author matin amiri
 * @see GiantEnemy
 * @see IntrinsicWeapon
 */
public class GiantCrab extends GiantEnemy{

    /**
     * Constructor
     */
    public GiantCrab() {
        super("Giant Crab", 'C', 407);
        this.addWeaponToInventory(new GiantClaw());

    }

    /**
     * randomly generated an amount of runes within a range and returns it
     * @return the amount of this Giant Crab's runes
     */
    @Override
    int getThisRunes() {
        return RandomNumberGenerator.getRandomInt(318,4961);
    }
}

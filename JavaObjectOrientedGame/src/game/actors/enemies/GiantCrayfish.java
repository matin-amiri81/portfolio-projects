package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.RandomNumberGenerator;
import game.weapons.GiantPincer;

/**
 * A class represents a Giant Crayfish.
 * @author matin amiri
 * @see GiantEnemy
 * @see IntrinsicWeapon
 */
public class GiantCrayfish extends GiantEnemy{

    /**
     * Constructor
     */
    public GiantCrayfish() {
        super("Giant Crayfish", 'R', 4803);
        this.addWeaponToInventory(new GiantPincer());

    }

    /**
     * randomly generated an amount of runes within a range and returns it
     * @return the amount of this Giant Crayfish's runes
     */
    @Override
    int getThisRunes() {
        return RandomNumberGenerator.getRandomInt(500,2374);
    }
}

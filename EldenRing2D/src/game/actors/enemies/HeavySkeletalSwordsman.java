package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.RandomNumberGenerator;
import game.weapons.Grossmesser;

/**
 * A class represents a Heavy Skeletal Swordsman.
 * @author matin amiri
 * @see SkeletalEnemy
 */
public class HeavySkeletalSwordsman extends SkeletalEnemy {

    /**
     * Constructor.
     * adds a Grossmesser to the inventory
     */
    public HeavySkeletalSwordsman() {
        super("HeavySkeletalSwordsman", 'q', 153);
        this.addWeaponToInventory(new Grossmesser());

    }

    /**
     * @return a new instance of heavy skeletal swordsman
     */
    @Override
    public SkeletalEnemy newInstance() {
        return new HeavySkeletalSwordsman();
    }

    /**
     * generates a random number of runes within the specified range and returns it
     * @return the runes the heavy skeletal swordsman holds
     */
    @Override
    int getThisRunes() {
        return RandomNumberGenerator.getRandomInt(35,892);
    }
    //does not have intrinsic weapon?

}

package game.actors.enemies;

import game.utils.RandomNumberGenerator;
import game.weapons.Scimitar;

/**
 * A class represents a Skeletal Bandit.
 * @author matin amiri
 * @see SkeletalEnemy
 */
public class SkeletalBandit extends SkeletalEnemy {

    /**
     * Constructor.
     * adds a Scimitar to the inventory
     */
    public SkeletalBandit() {
        super("SkeletalBandit", 'b', 184);
        this.addWeaponToInventory(new Scimitar());
    }

    /**
     * @return a new instance of skeletal bandit
     */
    @Override
    public SkeletalEnemy newInstance() {
        return new SkeletalBandit();
    }

    /**
     * generates a random number of runes within the specified range and returns it
     * @return the runes the skeletal bandit holds
     */
    @Override
    int getThisRunes() {
        //same as heavy skeletal swordsman?
        return RandomNumberGenerator.getRandomInt(35,892);
    }
    //does not have intrinsic weapon?
}

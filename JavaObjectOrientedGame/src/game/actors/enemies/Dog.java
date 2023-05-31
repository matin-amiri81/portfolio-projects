package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.RandomNumberGenerator;

public class Dog extends GodrickEnemy{

    public Dog() {
        super("Dog", 'a', 104);
    }

    @Override
    int getThisRunes() {
        return RandomNumberGenerator.getRandomInt(52 , 1390);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(101,"bites",93);
    }
}

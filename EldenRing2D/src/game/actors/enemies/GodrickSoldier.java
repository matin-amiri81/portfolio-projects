package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.RandomNumberGenerator;

public class GodrickSoldier extends GodrickEnemy{


    public GodrickSoldier() {
        super("Godrick Soldier", 'p', 198);
    }

    @Override
    int getThisRunes() {
        return RandomNumberGenerator.getRandomInt(38 , 70);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(52,"Slashes",97);
    }
}

package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Ability;

/**
 * a simple weapon that can be used to attack
 */
public class GraftedDragon extends WeaponItem {
    public static int GRAFTED_DRAGON_DAMAGE = 1000;
    public static int GRAFTED_DRAGON_HITCHANCE = 55;
    public GraftedDragon() {
        super("GraftedDragon", 'A', GRAFTED_DRAGON_DAMAGE, "Breathes",GRAFTED_DRAGON_HITCHANCE);
    }
}

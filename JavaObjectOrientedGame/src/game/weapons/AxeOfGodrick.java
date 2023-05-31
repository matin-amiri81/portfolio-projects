package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Ability;

/**
 * a simple weapon that can be used to attack
 */
public class AxeOfGodrick extends WeaponItem {
    public static int AXE_OF_GODRICK_DAMAGE = 1000;
    public static int AXE_OF_GODRICK_HITCHANCE = 55;
    public AxeOfGodrick() {
        super("Axe of Godrick", 'A', AXE_OF_GODRICK_DAMAGE, "chops",AXE_OF_GODRICK_HITCHANCE);
    }
}

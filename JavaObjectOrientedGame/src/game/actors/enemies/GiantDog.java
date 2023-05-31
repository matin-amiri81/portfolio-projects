package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AOEAttackAction;
import game.enums.Ability;
import game.utils.RandomNumberGenerator;
import game.weapons.GiantBite;

/**
 * A class represents a Giant Dog.
 * @author matin amiri
 * @see GiantEnemy
 * @see IntrinsicWeapon
 */
public class GiantDog extends CanineEnemy{

    /**
     * Constructor
     */
    public GiantDog() {
        super("Giant Dog", 'G', 693);
        this.addWeaponToInventory(new GiantBite());
    }

    /**
     * generates a random number of runes within the specified range and returns it
     * @return the runes the giant dog holds
     */
    @Override
    int getThisRunes() {
        return RandomNumberGenerator.getRandomInt(313,1808);
    }
}

package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.enums.Ability;
import game.enums.Status;

import java.util.Random;

/**
 * Special Action for the Uchigatana.
 * @author danny leung
 * @see Action
 * @see Actor
 */
public class UnsheatheAction extends Action {
    private Actor target;
    private String direction;
    private Random rand = new Random();
    private Weapon weapon;

    public static int UNSHEATHE_DAMAGE_MULTIPLYER = 2;
    public static int UNSHEATHE_HIT_CHANCE = 60;

    /**
     * constructor for UnsheatheAction
     * @param target
     * @param direction
     * @param weapon
     */
    public UnsheatheAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * Does double damage to the target
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (!(rand.nextInt(100) <= UNSHEATHE_HIT_CHANCE)) {
            return actor + " misses " + target + ".";
        }
        int damage = weapon.damage() * UNSHEATHE_DAMAGE_MULTIPLYER;
        String result = actor + " attacks " + target + " at " + direction + " with " + weapon + " for " + damage + " damage.";
        target.hurt(damage);
        if (!target.isConscious() && !target.hasCapability(Status.RESPAWNABLE)) {
            result += System.lineSeparator() + new DeathAction(actor).execute(target, map);
        }
        return result;
    }

    /**
     * Returns a description of this movement suitable to display in the menu.
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with Unsheathe Action";
    }
}

package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.List;
import java.util.Random;

/**
 * given an initial target, list of exits and optionally a weapon
 * the Area of Attack Action or AOE for short performs an attack action
 * with given weapon or the actors intrinsic weapon on all adjacent
 * locations to the actor on the execute method
 *
 * @author matin amiri
 * @see AttackAction
 * @see Action
 * @see Exit
 * @see Weapon
 * @see Actor
 */

public class AOEAttackAction extends Action {
    private Weapon weapon;
    private Actor target;

    /**
     * constructor
     * @param target the inicial target of the attack
     * @param weapon weapon used for attack
     */
    public AOEAttackAction(Actor target, Weapon weapon) {
        this.weapon = weapon;
        this.target = target;
    }
    /**
     * constructor
     * @param target the inicial target of the attack
     */
    public AOEAttackAction(Actor target) {
        this.target = target;
    }

    /**
     * performs attack action on all sounding locations of
     * the actor performing the attack
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the aoe attack in the format:
     * {actor} attacks sounding:
     * followed by the result of all attack actions on surrounding actors
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        List<Exit> exits = map.locationOf(actor).getExits();
        String result = actor + " attacks soroundings:";
        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }
        int damage = weapon.damage();
        for (Exit exit : exits) {
            if (exit.getDestination().containsAnActor()) {
                Actor target1 = exit.getDestination().getActor();
                result += System.lineSeparator() + new AttackAction(target1, exit.getName(), weapon).execute(actor, map);
            }
        }
        return result;
    }

    /**
     * returs descriptive string
     * @param actor The actor performing the action.
     * @return the aoe attack option in string format
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " Area Attack "+ this.target+" and sounding enemies with " + (weapon != null ? weapon : "Intrinsic Weapon");
    }
}

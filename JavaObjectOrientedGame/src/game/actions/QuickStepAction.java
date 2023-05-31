package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.utils.RandomNumberGenerator;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Special Action for the Great Knife.
 * @author danny leung
 * @see Action
 * @see Actor
 */
public class QuickStepAction extends Action {
    private Actor target;
    private String direction;
    private Weapon weapon;

    /**
     * constructor for QuickStepAction
     * @param target
     * @param direction
     * @param weapon
     */
    public QuickStepAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * Performs the quickstep action which is a combination of attack and move action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the attack and direction of quickstep
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        String result = new AttackAction(this.target,this.direction,this.weapon).execute(actor, map);


        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        if (here == null || there == null) {
            return result;
        }

        int currentDistance = distance(here, there);

        List<Exit> possibleExits = new ArrayList<>();
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                int newDistance = distance(destination, there);
                if (newDistance > currentDistance) { // moving away from the enemy
                    possibleExits.add(exit);
                }
            }
        }
        if (possibleExits.size() != 0){
            Exit exit = possibleExits.get(RandomNumberGenerator.getRandomInt(possibleExits.size()));
            Location destination = exit.getDestination();
            new MoveActorAction(destination, exit.getName()).execute(actor, map);
            result += System.lineSeparator() + actor + " quickstepped to (" + destination.x() +","+destination.y() +")";
        } else {
            result += System.lineSeparator() + actor + " failed to quickstep";
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
        return actor + " attacks " + target + " at " + direction + " with Quickstep Action";
    }

    /**
     * Calculates the distance between two locations.
     * @param a the first location
     * @param b the second location
     * @return the distance between the two locations
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}

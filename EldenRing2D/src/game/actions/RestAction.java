package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.ResetManager;
import game.utils.Resettable;

/**
 * Action to rest the actor and send them back to their last resting location
 * @author matin amiri
 * @see Action
 * @see Actor
 * @see Resettable
 * @see ResetManager
 * @see Location
 */
public class RestAction extends Action implements Resettable {
    private String direction;
    private String groundName;
    private Location location;
    private Actor actor;

    /**
     * constructor
     * @param direction the direction of the ground relatice to actor
     * @param groundName the name of the resting site
     * @param location the location of the resting site
     */
    public RestAction(String direction, String groundName, Location location){
        this.direction = direction;
        this.groundName = groundName;
        this.location = location;
        this.registerResettable();
    }

    /**
     * resets the map
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return {actor} is resting in the {groundName}
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.actor = actor;
        ResetManager.getInstance().run(map);
        return actor + " is resting in the " + groundName;

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " rest at " + direction + " in the " + groundName;
    }

    /**
     * moves the actor to this location if there is not already an actor present
     * when the map is reset
     * @param map the map that is getting reset
     */
    @Override
    public void reset(GameMap map) {
        if (!map.isAnActorAt(this.location)&& this.actor != null) {
            map.moveActor(this.actor, location);
        }
    }

    /**
     * prevents this resettable from being removed from the reset manager
     */
    @Override
    public void removeResettable() {
        //future update plan to have multiple resting sites
        //add an instance of the site of lost grace to the player
        //check if the instance is present in the actor here
        //remove if true
    }
}

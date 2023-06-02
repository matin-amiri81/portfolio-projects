package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gounds.Cliff;
import game.utils.RandomNumberGenerator;

/**
 * Action for climbing cliffs
 * @see Cliff
 * @see Action
 */
public class ClimbCliffAction extends Action {
    //NOTE: while yes this could be alot easier done in the tik method of the
    //location doing it like this allows for more flexibility in the future
    //i have no idea why i cared about this while writing, i guess too much time on my hands
    public static final int FALL_DAMAGE = 1000000;
    public static final int CLIMB_DEATH_CHANCE = 102;

    private String direction;
    private Location location;

    /**
     * constructor
     * @param location the location to move the actor to
     * @param direction the direction to move the actor to
     */
    public ClimbCliffAction(Location location, String direction){
        this.direction = direction;
        this.location = location;
    }

    /**
     * moves the actor to the location and then applies the damage
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing the result of the actor climbing the cliff
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.moveActor(actor,this.location);
        if(RandomNumberGenerator.getRandomInt(100)<CLIMB_DEATH_CHANCE){
            actor.hurt(FALL_DAMAGE);
            return actor + " fell off the cliff and died";
        }
        else{return actor + " successfully climbed the cliff";}
    }


    @Override
    public String menuDescription(Actor actor) {
        return actor +" climbs cliff " +direction;
    }
}

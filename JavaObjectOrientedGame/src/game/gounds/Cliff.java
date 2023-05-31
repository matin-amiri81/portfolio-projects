package game.gounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ClimbCliffAction;
import game.actions.RestAction;
import game.enums.Ability;

/**
 * a type of ground that doesnt allow the actor to enter traditionally and requires them to climb it
 * @see ClimbCliffAction
 */
public class Cliff extends Ground {

    /**
     * constructor
     */
    public Cliff() {
        super('▲');
    }

    /**
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return an actionList containing the action to climb the cliff
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        actionList.add(new ClimbCliffAction(location,direction));
        return actionList;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    //alternative method:
    //advantages: this way enemies/ai would also climb/fall off cliffs
    //disadvantages: you cannot add as much additional functionality in the future

    //@Override
    //public void tick(Location location) {
    //    location.getActor().hurt(FALL_DAMAGE);
    //}
}

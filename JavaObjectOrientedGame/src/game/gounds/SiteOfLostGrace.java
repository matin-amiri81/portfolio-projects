package game.gounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RestAction;
import game.enums.Ability;
import game.enums.Status;

/**
 * A class that represents the site of lost grace.
 * @author matin amiri
 * @see Ground
 * @see Location
 * @see Actor
 * @see RestAction
 */
public class SiteOfLostGrace extends Ground {

    /**
     * Constructor.
     */
    public SiteOfLostGrace() {
        super('U');
    }


    /**
     * Checks if the actor has the ability to rest and adds the rest action to the action list.
     * @param actor The actor on the site of lost grace
     * @param location The location of the site of lost grace
     * @param direction The direction of the site of lost grace
     * @return a list of actions performable by the actor at the site of lost grace
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if (actor.hasCapability(Ability.Rest)){
            actionList.add(new RestAction(direction,"Site of Lost Grace",location));
        }
        return actionList;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}

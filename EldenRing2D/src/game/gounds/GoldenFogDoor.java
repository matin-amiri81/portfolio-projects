package game.gounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ClimbCliffAction;
import game.actions.GoldenGateAction;
import game.enums.Ability;

/**
 * a type of ground linked to another of its own kind, allowing for movement between the two
 */
public class GoldenFogDoor extends Ground {

    private Location twinDoorLocation;
    public GoldenFogDoor() {
        super('∏');
    }

    /**
     * constructor
     * @param twinDoorLocation the location of the exit of this door
     */
    public void setExitLocation(Location twinDoorLocation) {
        this.twinDoorLocation = twinDoorLocation;
    }

    /**
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return an actionList containing the action to move to the exit of this door if the actor has the ability to enter
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if(twinDoorLocation == null){return actionList;}
        if(!actor.hasCapability(Ability.EnterGoldenDoor)){return actionList;}
        actionList.add(new GoldenGateAction(twinDoorLocation,direction));
        return actionList;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }
}

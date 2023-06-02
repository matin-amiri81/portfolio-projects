package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class GoldenGateAction extends Action {

    Location twinDoorLocation;
    String direction;
    public GoldenGateAction(Location twinDoorLocation, String direction){
        this.twinDoorLocation = twinDoorLocation;
        this.direction = direction;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        if (twinDoorLocation.containsAnActor()){return actor + " cannot enter the golden gate because there is an actor blocking the way";}
        map.moveActor(actor, twinDoorLocation);
        return actor + " has travelled through the golden door";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " enters the golden door" + direction;
    }
}

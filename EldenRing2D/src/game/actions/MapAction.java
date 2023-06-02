package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.items.GodlyBFSMap;
import game.utils.BFSMayhem;

public class MapAction extends Action {

    private Location destination;
    private String destinationName;

    public MapAction(Location destination, String destinationName) {
        this.destination = destination;
        this.destinationName = destinationName;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        if(this.destination.map() == map) {
            Location here = map.locationOf(actor);
            BFSMayhem.BFS(here, this.destination, actor);
            return "The path has been layed to the " + this.destinationName ;
        }
        return "The path has not been layed as the runes were lost in the " + this.destination.map().toString();

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor +" with the godly power of BFS, Lights the Way to the " + this.destinationName + "!";
    }
}

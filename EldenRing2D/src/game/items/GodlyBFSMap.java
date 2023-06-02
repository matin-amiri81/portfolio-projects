package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.MapAction;

import java.util.ArrayList;
import java.util.List;

/**
 * An Item that has the ability to display the shotrest path to a destination to the player
 * @see game.utils.BFSMayhem
 * @see MapAction
 */
public class GodlyBFSMap extends RuneItem {
    public static int GODLY_BFS_MAP_BUY_PRICE = 1000;
    public static int GODLY_BFS_MAP_SELL_PRICE = 300;
    Location destination;
    String destinationName;
    Location currentLocation;

    /**
     * Constructor
     */
    public GodlyBFSMap() {
        super("Godly Map", '‡', false,GODLY_BFS_MAP_BUY_PRICE,GODLY_BFS_MAP_SELL_PRICE);
    }

    /**
     * Constructor
     * @param destination The destination of the map
     * @param destinationName the name of the destination
     */
    public GodlyBFSMap(Location destination,String destinationName) {
        super("Godly Map", '‡', false,GODLY_BFS_MAP_BUY_PRICE,GODLY_BFS_MAP_SELL_PRICE);
        this.destination = destination;
        this.destinationName = destinationName;
    }

    /**
     * Sets the destination of the map
     * @param destination The destination of the map
     * @param destinationName the name of the destination
     */
    public void setDestination(Location destination,String destinationName) {
        this.destination = destination;
        this.destinationName = destinationName;
    }

    /**
     * keeps track of the current location of the actor carrying this Item
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.currentLocation = currentLocation;
    }

    /**
     * if the holder of this map is on the same map as the destination,
     * it will return an action to display the shortest path to the destination
     * @return an action to display the shortest path to the destination
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions= new ArrayList<>();

        if(this.destination == null) {return actions;}
        if(this.currentLocation == null) {return actions;}
        if (this.currentLocation.map() != this.destination.map()) {return actions;}
        actions.add(new MapAction(this.destination,this.destinationName));
        return actions;
    }

}

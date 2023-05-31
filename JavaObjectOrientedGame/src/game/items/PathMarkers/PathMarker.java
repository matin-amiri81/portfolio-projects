package game.items.PathMarkers;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An abstract class that represents a path marker
 * A path marker is an item that cannot be picked up or dropped but is displayed on the map as an arrow
 * it disappears after a certain number of turns
 */
public abstract class PathMarker extends Item {
    public static final int MAX_USES = 1;
    private int uses;


    public PathMarker(char displayChar) {
        super("marker", displayChar, false);
        uses = 0;
    }

    @Override
    public void tick(Location currentLocation) {
        if(uses >= MAX_USES){currentLocation.removeItem(this);}
        uses++;
    }
}

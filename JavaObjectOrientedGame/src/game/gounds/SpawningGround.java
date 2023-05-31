package game.gounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.XCompass;

/**
 * A class that represents a spawning ground.
 * @author matin amiri
 * @see Ground
 * @see game.enums.XCompass
 * @see Location
 */
public abstract class SpawningGround extends Ground {
    /**
     * Constructor.
     * @param displayChar character to display for this type of terrain
     */
    public SpawningGround(char displayChar) {
        super(displayChar);
    }

    /**
     * finds weather the location is on the east or west side of the map
     * @param location the location to check
     * @return the xCompass of the location
     */
    public XCompass getXCompass(Location location){
        XCompass xCompass;
        int widthMap = location.map().getXRange().max();
        int xCoordinate = location.x();
        if (xCoordinate < widthMap/2){
            xCompass = XCompass.WEST;
        }
        else{xCompass = XCompass.EAST;}
        return xCompass;
    }
}

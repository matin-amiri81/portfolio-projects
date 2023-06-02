package game.gounds;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.*;
import game.enums.XCompass;
import game.utils.RandomNumberGenerator;

/**
 * A class that represents a gust of wind.
 * @author matin amiri
 * @see SpawningGround
 * @see Location
 * @see XCompass
 */

public class GustOfWind extends SpawningGround{
    public static final int GUST_OF_WIND_SPAWN_CHANCE = 33;

    /**
     * Constructor.
     */
    public GustOfWind() {
        super('&');
    }

    /**
     * Spawns LoneWolf or GiantDog enemies in the gust of wind depending on the xCompass of the location.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        boolean spawn = RandomNumberGenerator.getRandomInt(0, 100) < GUST_OF_WIND_SPAWN_CHANCE;
        if ( spawn && !location.containsAnActor()) {
            XCompass xCompass = getXCompass(location);
            switch (xCompass) {
                case WEST:
                    location.addActor(new LoneWolf());
                    break;
                case EAST:
                    location.addActor(new GiantDog());
                    break;
                default:
                    throw new IllegalStateException("Unexpected value : " + xCompass);
            }
        }
    }
}

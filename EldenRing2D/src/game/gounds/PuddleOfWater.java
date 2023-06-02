package game.gounds;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.GiantCrab;
import game.actors.enemies.GiantCrayfish;
import game.enums.XCompass;
import game.utils.RandomNumberGenerator;
/**
 * A class that represents a puddle of water.
 * @author matin amiri
 * @see SpawningGround
 * @see Location
 * @see XCompass
 */
public class PuddleOfWater extends SpawningGround{
    public static final int PUDDLE_OF_WATER_SPAWN_CHANCE = 2;
    /**
     * Constructor.
     */
    public PuddleOfWater() {
        super('~');
    }

    /**
     * Spawns GiantCrab or GiantCrayfish enemies in the puddle of water depending on the xCompass of the location.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        boolean spawn = RandomNumberGenerator.getRandomInt(0, 100) < PUDDLE_OF_WATER_SPAWN_CHANCE;
        if ( spawn && !location.containsAnActor()) {
            XCompass xCompass = getXCompass(location);
            switch (xCompass) {
                case WEST:
                    location.addActor(new GiantCrab());
                    break;
                case EAST:
                    location.addActor(new GiantCrayfish());
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + xCompass);
            }
        }
    }

}

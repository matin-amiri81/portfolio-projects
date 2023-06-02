package game.gounds;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.HeavySkeletalSwordsman;
import game.actors.enemies.SkeletalBandit;
import game.enums.XCompass;
import game.utils.RandomNumberGenerator;

/**
 * A class that represents a graveyard.
 * @author matin amiri
 * @see SpawningGround
 * @see Location
 * @see XCompass
 */
public class Graveyard extends SpawningGround {

    public static final int GRAVEYARD_SPAWN_CHANCE = 27;

    /**
     * Constructor.
     */
    public Graveyard() {
        super('n');
    }

    /**
     * Spawns HeavySkeletalSwordsman or SkeletalBandit enemies in the graveyard depending on the xCompass of the location.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        boolean spawn = RandomNumberGenerator.getRandomInt(0, 100) < GRAVEYARD_SPAWN_CHANCE;
        if ( spawn && !location.containsAnActor()) {
            XCompass xCompass = getXCompass(location);
            switch (xCompass) {
                case WEST:
                    location.addActor(new HeavySkeletalSwordsman());
                    break;
                case EAST:
                    location.addActor(new SkeletalBandit());
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + xCompass);
            }
        }
    }
}

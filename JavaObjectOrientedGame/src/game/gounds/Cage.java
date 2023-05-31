package game.gounds;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Dog;
import game.utils.RandomNumberGenerator;

/**
 * a spawning ground that has a chance to spawn a Dog
 */
public class Cage extends SpawningGround{

    public static final int CAGE_SPAWN_CHANCE = 37;

    /**
     * Constructor.
     */

    public Cage() {
        super('<');
    }

    /**
     * Spawns a Dog given the location is empty the spawn chance
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        boolean spawn = RandomNumberGenerator.getRandomInt(0, 100) < CAGE_SPAWN_CHANCE;
        if ( spawn && !location.containsAnActor()) {
            location.addActor(new Dog());
        }
    }
}

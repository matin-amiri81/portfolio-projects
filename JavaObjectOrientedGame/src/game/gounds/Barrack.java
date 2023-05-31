package game.gounds;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Dog;
import game.actors.enemies.GodrickSoldier;
import game.utils.RandomNumberGenerator;

/**
 * a spawning ground that has a chance to spawn a GodrickSoldier
 */
public class Barrack extends SpawningGround{
    public static final int BARRACK_SPAWN_CHANCE = 45;

    /**
     * Constructor.
     */
    public Barrack() {
        super('B');
    }

    /**
     * Spawns a GodrickSoldier given the location is empty the spawn chance
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        boolean spawn = RandomNumberGenerator.getRandomInt(0, 100) < BARRACK_SPAWN_CHANCE;
        if ( spawn && !location.containsAnActor()) {
            location.addActor(new GodrickSoldier());
        }
    }
}

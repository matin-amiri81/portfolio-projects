package game.gounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * a child of rotting ground
 * Damage: 20
 * Display char: ▒
 */
public class SeaOfRot extends RottingGround {

    public static final int SEA_OF_ROT_DAMAGE = 20;

    public SeaOfRot() {
        super('▒',SEA_OF_ROT_DAMAGE);
    }

}

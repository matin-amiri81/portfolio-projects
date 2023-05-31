package game.gounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * a child of rotting ground
 * Damage: 50
 * Display char: ▓
 */
public class OceanOfRot extends RottingGround {

    public static final int OCEAN_OF_ROT_DAMAGE = 50;
    public OceanOfRot() {
        super('▓',OCEAN_OF_ROT_DAMAGE);
    }

}

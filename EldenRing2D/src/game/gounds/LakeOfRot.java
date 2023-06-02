package game.gounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.utils.RandomNumberGenerator;

/**
 * a child of rotting ground
 * Damage: 10
 * Display char: ░
 */
public class LakeOfRot extends RottingGround {
    public static final int LAKE_OF_ROT_DAMAGE = 10;

    public LakeOfRot() {
        super('░',LAKE_OF_ROT_DAMAGE);
    }

}

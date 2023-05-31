package game.gounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Status;

/**
 * A class that represents bare dirt.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Dirt extends Ground {

	public Dirt() {
		super('.');
		this.addCapability(Status.REPLACEABLE);
	}
}

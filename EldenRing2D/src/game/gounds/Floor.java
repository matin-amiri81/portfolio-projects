package game.gounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Ability;
import game.enums.Status;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Floor extends Ground {
	public Floor() {
		super('_');
		this.addCapability(Status.REPLACEABLE);
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasCapability(Ability.EnterFloor);
	}
}

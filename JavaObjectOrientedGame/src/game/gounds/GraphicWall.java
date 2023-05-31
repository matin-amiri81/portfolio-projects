package game.gounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
/**
 * Identical to Wall except for the abstract factor,
 * abstract to allow for the creation of several wall variations
 * with different display characters but identical functionality
 */
public abstract class GraphicWall extends Ground {

    public GraphicWall(char displayChar) {
        super(displayChar);
    }
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    @Override
    public boolean blocksThrownObjects() {
        return true;
    }
}

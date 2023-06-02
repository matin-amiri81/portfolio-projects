package game.utils;

import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A resettable interface
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public interface Resettable {

    void reset(GameMap map);

    default void removeResettable(){
        ResetManager.getInstance().removeResettable(this);
    }
    default void registerResettable(){
        ResetManager.getInstance().registerResettable(this);
    }
}

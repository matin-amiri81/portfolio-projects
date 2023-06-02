package game.utils;

import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: matin amiri
 *
 */
public class ResetManager {
    private List<Resettable> resettables;
    private static ResetManager instance;

    /**
     * HINT 1: where have we seen a private constructor before?
     * HINT 2: see the instance attribute above.
     */
    private ResetManager() {
        this.resettables = new ArrayList<>();
    }

    public static ResetManager getInstance() {
        if (instance == null) {
            instance = new ResetManager();
        }
        return instance;
    }


    /**
     * runs the reset method of all resettable s in the list
     * @param map the map to reset the resettable on
     */
    public void run(GameMap map) {
        List<Resettable> removables = new ArrayList<>();
        for (Resettable resettable : resettables) {
            removables.add(resettable);
            resettable.reset(map);
        }
        for (Resettable resettable : removables) {
            resettable.removeResettable();
        }
    }

    /**
     * adds a resettable to the list
     * @param resettable the resettable to add
     */
    public void registerResettable(Resettable resettable) {resettables.add(resettable);}

    /**
     * removes a resettable from the list
     * @param resettable the resettable to remove
     */
    public void removeResettable(Resettable resettable) {
        resettables.remove(resettable);
    }

    //test purposes
    //public List<Resettable> getResettable() {return resettables;}
}

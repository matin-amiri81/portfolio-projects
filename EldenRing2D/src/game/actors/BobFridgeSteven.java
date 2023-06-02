package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.GodlyBFSMap;

/**
 * A specific type of trader that sells BFS maps
 * @see Trader
 */
public class BobFridgeSteven extends Trader{

    /**
     * constructor for BobFridgeSteven a trader specialised in BFS Maps
     */
    public BobFridgeSteven() {
        super("Bob Fridge Steven", 'Ü', TRADER_MAX_HIT_POINTS);
    }

    public void addMap(GodlyBFSMap BFSmap){
        this.itemStore.add(BFSmap);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}

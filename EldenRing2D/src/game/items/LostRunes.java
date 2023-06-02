package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PickUpLostRunesAction;
import game.enums.Ability;
import game.utils.Resettable;

/**
 * A class that represents the lost runes of the player as an item.
 * @author matin amiri
 * @see Item
 * @see Resettable
 * @see PickUpLostRunesAction
 */
public class LostRunes extends Item {
    private int runes;
    private Location location;

    /**
     * Constructor.
     * @param runes The number of runes the player has lost
     * @param location The location of the lost runes
     */
    public LostRunes(int runes, Location location){
        super("Lost Runes", '$', false);
        this.runes = runes;
        this.location = location;
    }

    /**
     * @param actor to pick up runes
     * @return Checks if the actor has the ability to pick up runes
     * if so adds the pick up action to the returning action list.
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        if(actor.hasCapability(Ability.PickUpRunes)) {
            return new PickUpLostRunesAction(this);
        }
        return null;
    }

    /**
     * removes the lost runes from the location
     */
    public void reset() {
        location.removeItem(this);
    }

    /**
     * @return the number of runes
     */
    public int getRunes(){
        return runes;
    }
}

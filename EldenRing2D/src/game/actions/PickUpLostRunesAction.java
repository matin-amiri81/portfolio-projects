package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.LostRunes;
import game.runes.RuneManager;

/**
 * Action to pick up lost runes and deposit them into the actor
 * @author matin amiri
 * @see PickUpAction
 * @see Actor
 * @see LostRunes
 * @see RuneManager
 */
public class PickUpLostRunesAction extends PickUpAction {
    private LostRunes lostRunes;

    /**
     * constructor
     * @param lostRunes the lost runes to be picked up
     */
    public PickUpLostRunesAction(LostRunes lostRunes) {
        super(lostRunes);
        this.lostRunes = lostRunes;
    }

    /**
     * deposit the runes into the actor. the actor should be checked
     * to have the capability to pick up lost runes before allowing them to perform this action
     * this will not break application just a precaution
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string representing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RuneManager.getInstance().depositRunes(actor, this.lostRunes.getRunes());
        return super.execute(actor, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " retrieves Runes  (value: " + lostRunes.getRunes() +")";
    }
}

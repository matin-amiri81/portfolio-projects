package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Ability;
import game.items.GoldenRunes;
import game.runes.RuneManager;
import game.utils.RandomNumberGenerator;

/**
 * An action to consume golden runes and gain a random amount of runes
 * @see GoldenRunes
 * @see Action
 */
public class ConsumeRunesAction extends Action {
    private GoldenRunes goldenRunes;
    public static final int GOLDEN_RUNES_MIN = 200;
    public static final int GOLDEN_RUNES_MAX = 10000;


    /**
     * constructor
     * @param goldenRunes the golden runes to be consumed
     */
    public ConsumeRunesAction(GoldenRunes goldenRunes){
        this.goldenRunes = goldenRunes;
    }

    /**
     * consumes and removes the golden runes from the actor's inventory
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(!actor.hasCapability(Ability.ConsumeGoldenRunes)){return actor + " cannot consume golden runes";}
        int randomRunes = RandomNumberGenerator.getRandomInt(GOLDEN_RUNES_MIN,GOLDEN_RUNES_MAX);
        RuneManager.getInstance().depositRunes(actor,randomRunes);
        actor.removeItemFromInventory(goldenRunes);
        return actor + " consumed golden runes and gained " + randomRunes + " runes";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes the golden runes";
    }
}

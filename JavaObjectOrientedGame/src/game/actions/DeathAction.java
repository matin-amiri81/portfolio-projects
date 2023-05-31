package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Ability;
import game.runes.RuneManager;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: matin
 *
 */
public class DeathAction extends Action {
    private Actor attacker = null;

    public DeathAction() {
    }
    public DeathAction(Actor attacker) {
        this.attacker = attacker;
    }

    /**
     * When the target is killed, the items and weapons carried by target
     * will be dropped to the location in the game map where the target was
     * if the attack has the capability to obtain the runes from the target
     * the runes will be transferred through the rune manager
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     * @see Ability
     * @see RuneManager
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";
        if(attacker != null)
            result += attacker + " killed " + target + ".";
        else
            result += target + " is killed.";

        ActionList dropActions = new ActionList();
        // drop all items
        for (Item item : target.getItemInventory())
            dropActions.add(item.getDropAction(target));
        for (WeaponItem weapon : target.getWeaponInventory())
            dropActions.add(weapon.getDropAction(target));
        for (Action drop : dropActions)
            drop.execute(target, map);

        if(this.attacker != null) {
            if (this.attacker.hasCapability(Ability.GetRuneFromKill)) {
                RuneManager.getInstance().transactAllRunes(target, attacker);
            }
        }

        // remove actor
        map.removeActor(target);
        result += System.lineSeparator() + menuDescription(target);
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.";
    }
}

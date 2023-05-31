package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.Trader;

/**
 * An action to trade an item or weapon for another weapon
 * @see Action
 * @see Item
 * @see Trader
 * @see WeaponItem
 */
public class TradeWeaponAction extends Action {
    private Trader trader;
    private WeaponItem weaponGiven;
    private WeaponItem weaponTaken;
    private Item itemTaken;


    /**
     * constructor for trading weapon for weapon
     * @param trader the actor offering to trade
     * @param weaponTaken the weapon taken from the actor
     * @param weaponGiven the weapon given to the actor
     */
    public TradeWeaponAction(Trader trader, WeaponItem weaponTaken, WeaponItem weaponGiven){
        this.trader = trader;
        this.weaponGiven = weaponGiven;
        this.weaponTaken = weaponTaken;
    }

    /**
     * constructor for trading item for weapon
     * @param trader the actor offering to trade
     * @param itemTaken the item taken from the actor
     * @param weaponGiven the weapon given to the actor
     */
    public TradeWeaponAction(Trader trader, Item itemTaken, WeaponItem weaponGiven){
        this.trader = trader;
        this.weaponGiven = weaponGiven;
        this.itemTaken = itemTaken;
    }

    /**
     * adds the weapon to actor and removes the weapon from the actors
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string representing the result of the transaction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(weaponTaken == null) {
            actor.removeItemFromInventory(itemTaken);
            actor.addWeaponToInventory(weaponGiven);
            return actor + " traded " + itemTaken + " for " + weaponGiven + " with " + trader;
        }
        actor.removeWeaponFromInventory(weaponTaken);
        actor.addWeaponToInventory(weaponGiven);
        return actor + " traded " + weaponTaken + " for " + weaponGiven + " with " + trader;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Trade " + (weaponTaken != null ? weaponTaken : itemTaken) + " for " + weaponGiven + " with " + trader;
    }
}

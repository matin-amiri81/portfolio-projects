package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.Player;
import game.actors.Trader;
import game.enums.Ability;
import game.items.RuneItem;
import game.runes.RuneManager;
import game.weapons.RuneWeapon;

/**
 * An action to sell an item or weapon
 * @author matin amiri
 * @see Action
 * @see Actor
 * @see Item
 * @see Trader
 * @see java.security.DrbgParameters.Capability
 */
public class SellAction extends Action {

    private Actor trader;
    private Item item = null;
    private WeaponItem weapon = null;
    private final int sellPrice;

    /**
     * constructor
     * @param trader the actor offering to sell
     * @param item the item to sell
     */
    public SellAction(Actor trader,Item item, int sellPrice){
        this.trader = trader;
        this.item = item;
        this.sellPrice = sellPrice;
    }
    /**
     * constructor
     * @param trader the actor offering to sell
     * @param weapon the weapon to sell
     */
    public SellAction(Actor trader, WeaponItem weapon,int sellPrice){
        this.trader = trader;
        this.weapon = weapon;
        this.sellPrice = sellPrice;
    }

    /**
     * adds the runes to actor and removes the item or weapon from the actors
     * item or weapon inventory respectively
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string representing the result of the transaction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        if(actor.hasCapability(Ability.Sell)){
            if(this.item == null){result += sellWeapon(actor);}
            else{result += sellItem(actor);}
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + (weapon != null ? weapon : item) +" to " + trader + " for " + this.sellPrice +"" ;
    }

    private String sellWeapon(Actor actor){
        RuneManager.getInstance().depositRunes(actor, this.sellPrice);
        actor.removeWeaponFromInventory(this.weapon);
        return actor + " sold " + this.weapon + " to " + trader + " for " + this.sellPrice+ " runes.";
        //potential check could be added to see if money went through before removing weapon
    }
    private String sellItem(Actor actor){
        RuneManager.getInstance().depositRunes(actor, this.sellPrice);
        actor.removeItemFromInventory(this.item);
        return actor + " sold " + this.item + " to " + trader + " for " + this.sellPrice+ " runes.";
        //potential check could be added to see if money went through before removing item
    }
}



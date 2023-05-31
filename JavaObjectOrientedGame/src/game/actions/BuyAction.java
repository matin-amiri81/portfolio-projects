package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.actors.Trader;
import game.enums.Ability;
import game.items.RuneItem;
import game.runes.IRuneItem;
import game.runes.RuneManager;
import game.weapons.Club;
import game.weapons.RuneWeapon;

/**
 * An action to buy an item or weapon
 * @author matin amiri
 * @see Action
 * @see Actor
 * @see Item
 * @see Trader
 * @see java.security.DrbgParameters.Capability
 */
public class BuyAction extends Action {

    private Trader trader;
    private RuneItem item = null;

    private RuneWeapon weapon = null;

    /**
     * constructor
     * @param trader the actor offering to sell
     * @param item the item up for sale
     */
    public BuyAction(Trader trader,RuneItem item){
        this.trader = trader;
        this.item = item;
    }
    /**
     * constructor
     * @param trader the actor offering to sell
     * @param weapon the weapon up for sale
     */
    public BuyAction(Trader trader,RuneWeapon weapon){
        this.trader = trader;
        this.weapon = weapon;
    }

    /**
     * checks if the player has the required runes to purchase the item or weapon
     * if so deduct the runes from player and add item or weapon to the actors
     * item or weapon inventory respectively
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string representing the result of the transaction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        if(actor.hasCapability(Ability.Buy)){
            if(this.item == null){result += buyWeapon(actor);}
            else{result += buyItem(actor);}
        }
        return result;
    }

    /**
     * returns descriptive string
     * @param actor The actor performing the action.
     * @return the buy option in text form
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + (weapon != null ? weapon : item) +" from " + trader+ " for "
                + (weapon != null ? weapon.getBuyPrice()+"" : item.getBuyPrice() +"");
    }

    private String buyWeapon(Actor actor){
        if (RuneManager.getInstance().removeRunes(actor, this.weapon.getBuyPrice())){
            actor.addWeaponToInventory(this.weapon);
            return actor + " bought " + this.weapon + " from " + trader + " for " + this.weapon.getBuyPrice()+ " runes.";
        }
        return  actor + " does not have enough runes to buy " + this.weapon + " from " + trader + ".";
    }
    private String buyItem(Actor actor){
        if (RuneManager.getInstance().removeRunes(actor, this.item.getBuyPrice())){
            actor.addItemToInventory(this.item);
            return actor + " bought " + this.item + " from " + trader + " for " + this.item.getBuyPrice()+ " runes.";
        }
        return  actor + " does not have enough runes to buy " + this.item + " from " + trader + ".";
    }
}







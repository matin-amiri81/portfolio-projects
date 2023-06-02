package game.actors;


import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.BuyAction;
import game.actions.SellAction;
import game.enums.Ability;
import game.items.RuneItem;
import game.runes.RuneManager;
import game.weapons.RuneWeapon;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class for actors that hold and trade rune items and weapons
 * @author matin amiri
 * @see Actor
 * @see RuneItem
 * @see RuneWeapon
 * @see BuyAction
 * @see SellAction
 * @see RuneManager
 */
public abstract class Trader extends Actor {
    public static final int TRADER_MAX_HIT_POINTS = 1;

    /**
     * the list of weapons the trader has to sell
     */
    protected List<RuneWeapon> weaponStore;
    /**
     * the list of items the trader has to sell
     */
    protected List<RuneItem> itemStore;

    /**
     * Constructor.
     * initialise the weaponStore and itemStore
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Trader(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        weaponStore = new ArrayList<>();
        itemStore = new ArrayList<>();
        this.addCapability(Ability.Sell);
    }

    /**
     * given an actor that has ability to buy or sell, return a list of buy and sell actions
     * @return ActionList of buy and sell actions that the actor can perform
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actionList = new ActionList();
        if (otherActor.hasCapability(Ability.Buy)){
            for (RuneWeapon weapon: weaponStore){
                actionList.add(new BuyAction(this,weapon));
            }
            for (RuneItem item: itemStore){
                actionList.add(new BuyAction(this,item));
            }
        }
        return actionList;
    }
}

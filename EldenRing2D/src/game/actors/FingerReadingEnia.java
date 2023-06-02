package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.TradeWeaponAction;
import game.enums.Ability;
import game.weapons.AxeOfGodrick;
import game.weapons.GraftedDragon;

import java.util.ArrayList;
import java.util.List;

/**
 * A trader with the ability to trade/exchange
 * @see Trader
 * @see WeaponItem
 * @see TradeWeaponAction
 */
public class FingerReadingEnia extends Trader{
    private List<WeaponItem> weaponsToTrade;

    /**
     * Constructor for FingerReadingEnia a trader with the extra ability of trading/exchanging
     */
    public FingerReadingEnia() {
        super("Finger Reader Enia", 'E', TRADER_MAX_HIT_POINTS);
        weaponsToTrade = new ArrayList<>();
        this.weaponsToTrade.add(new AxeOfGodrick());
        this.weaponsToTrade.add(new GraftedDragon());
    }

    /**
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return all possible trade actions with this trader excluding sell (trader gives money)
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        actions.add(super.allowableActions(otherActor, direction, map));
        for(WeaponItem weaponItem : otherActor.getWeaponInventory()){
            if(weaponItem.hasCapability(Ability.GodrickTrades)){
                for (WeaponItem weaponsToTrade : this.weaponsToTrade){
                    actions.add(new TradeWeaponAction(this, weaponItem, weaponsToTrade));
                }
            }
        }
        for(Item item: otherActor.getItemInventory()){
            if (item.hasCapability(Ability.GodrickTrades)){
                for (WeaponItem weaponsToTrade : this.weaponsToTrade){
                    actions.add(new TradeWeaponAction(this, item, weaponsToTrade));
                }
            }
        }
        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}

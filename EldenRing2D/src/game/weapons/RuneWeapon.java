package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.SellAction;
import game.enums.Ability;
import game.runes.IRuneItem;
import game.runes.RuneManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that represents a sellable/buy-able weapon in the game.
 * @author matin amiri
 * @see Item
 * @see IRuneItem
 * @see RuneManager
 */
public abstract class RuneWeapon extends WeaponItem implements IRuneItem {

    private int buyPrice;
    private int sellPrice;
    private Location location;

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     * @param buyPrice      the int value to buy weapon
     * @param sellPrice     the int value to sell weapon
     */
    public RuneWeapon(String name, char displayChar, int damage, String verb, int hitRate, int buyPrice,int sellPrice) {
        super(name, displayChar, damage, verb, hitRate);
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    /**
     * @return the buy price of this weapon
     */
    @Override
    public int getBuyPrice() {
        return this.buyPrice;
    }

    /**
     * @return the sell price of this weapon
     */
    @Override
    public int getSellPrice() {
        return this.sellPrice;
    }
    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.location = currentLocation;
    }

    /**
     * looks for traders around the holder of the weapon and returns a sell action if it is valid
     * @return a list containing its sell actions
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        if(location == null){return actions;}
        if(this.sellPrice == -1){return actions;}
        for(Exit exit:location.getExits()){
            if(!exit.getDestination().containsAnActor()){continue; }
            Actor actor = exit.getDestination().getActor();
            if(!actor.hasCapability(Ability.Sell)){continue;}
            actions.add(new SellAction(actor,this,getSellPrice()));
        }
        return actions;
    }
}

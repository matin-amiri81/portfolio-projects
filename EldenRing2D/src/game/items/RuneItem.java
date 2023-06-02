package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.BuyAction;
import game.actions.SellAction;
import game.enums.Ability;
import game.runes.IRuneItem;
import game.runes.RuneManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that represents a sellable/buy-able items in the game.
 * @author matin amiri
 * @see Item
 * @see IRuneItem
 * @see RuneManager
 */
public abstract class RuneItem extends Item implements IRuneItem {

    private int buyPrice;
    private int sellPrice;
    private Location location;
    /***
     * Constructor. registers item in reset manager
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public RuneItem(String name, char displayChar, boolean portable, int buyPrice, int sellPrice) {
        super(name, displayChar, portable);
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    /**
     * @return the buy price of this item
     */
    @Override
    public int getBuyPrice() {
        return this.buyPrice;
    }

    /**
     * @return the sell price of this item
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
     * looks for traders around the holder of the item and returns a sell action if it is valid
     * @return a list containing its sell actions
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        if(location == null){return actions;}
        for(Exit exit:location.getExits()){
            if(!exit.getDestination().containsAnActor()){continue; }
            Actor actor = exit.getDestination().getActor();
            if(!actor.hasCapability(Ability.Sell)){continue;}
            actions.add(new SellAction(actor,this,getSellPrice()));
        }
        return actions;
    }
}

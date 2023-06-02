package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.weapons.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A specific type of trader that sells Uchigatana, GreatKnife, Club and Scimitar
 * @author matin amiri
 * @see Trader
 */
public class MerchentKale extends Trader{



    /**
     * constructor
     * adds Uchigatana, GreatKnife, Club and Scimitar to the weapon store
     */
    public MerchentKale() {
        super("Merchant Kale", 'K', TRADER_MAX_HIT_POINTS);
        this.weaponStore.add(new Uchigatana());
        this.weaponStore.add(new GreatKnife());
        this.weaponStore.add(new Club());
    }

    /**
     * the trader does nothing on its turn
     * @param map the map the actor is on
     * @param display the display
     * @return Do nothing action
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}




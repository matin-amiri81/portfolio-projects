package game.runes;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.ResetManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that manages the runes of all the actors as well
 * as holding the selling price of all sellable items
 * @author matin amiri
 * @see Runes
 * @see Actor
 * @see Item
 */
public class RuneManager {

    private  Map<Actor, Runes> runePossessors;
    private static RuneManager instance;

    private RuneManager() {

        this.runePossessors = new HashMap<>();
    }

    /**
     * factory method
     * @return the single instance of this class
     */
    public static RuneManager getInstance() {
        if (instance == null) {
            instance = new RuneManager();
        }
        return instance;
    }

    /**
     * adds the actor and runes to the runePossessors map using the actor as the key
     * @param actor the actor to register
     * @param runes the number of runes the actor has
     */
    public void registerRunePossessor(Actor actor, int runes) {
        runePossessors.put(actor, new Runes(runes));
    }


    /**
     * adds runes to the account of the actor
     * @param depositer the actor to deposit runes
     * @param runes the number of runes to deposit
     */
    public void depositRunes(Actor depositer, int runes){
        if (runePossessors.containsKey(depositer)){
            runePossessors.get(depositer).addRunes(runes);
        }
    }

    /**
     * removes runes from the account of the actor
     * @param actor the actor to remove runes
     * @param amount the number of runes to withdraw
     * @return false if actor is not present in the bank of rune manager, true otherwise
     */
    public boolean removeRunes(Actor actor, int amount){
        if (runePossessors.containsKey(actor)){
            return runePossessors.get(actor).removeRunes(amount);
        }
        return false;
    }

    /**
     * @param actor the actor to check
     * @return the rune manager bank balance of the actor, -1 if not in the bank
     */
    public int getRunes(Actor actor){
        if (runePossessors.containsKey(actor)){
            return runePossessors.get(actor).getRunes();
        }
        return -1;
    }

    /**
     * transfers all the runes from the depositer actor to the withdrawing actor
     * @param depositer the actor to deposit runes
     * @param withdrawer the actor to withdraw runes
     */
    public void transactAllRunes(Actor depositer, Actor withdrawer){
        if (runePossessors.containsKey(depositer) && runePossessors.containsKey(withdrawer)){
            int runes = this.getRunes(depositer);
            this.depositRunes(withdrawer, runes);
        }
        //should add some sort of check here
    }

}

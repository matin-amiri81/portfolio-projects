package game.actors.classes;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.weapons.Uchigatana;

/**
 * A class that represents the Samurai class.
 * @author danny leung
 * @see Player
 */
public class Samurai extends Player {

    public static final int SAMURAI_MAX_HIT_POINTS = 455;

    /**
    * Constructor.
     * Creates a new Samurai player with 455 hit points and a Uchigatana.
    */
    public Samurai() {
        super(SAMURAI_MAX_HIT_POINTS);
        this.addWeaponToInventory(new Uchigatana());
    }
}

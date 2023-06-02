package game.weapons;

import edu.monash.fit2099.engine.actions.Action;

import java.util.List;

/**
 * Class representing a Scimitar weapon.
 * This weapon has a special area attack skill
 */
public class Scimitar extends AOEWeapons {
    public static int SCIMITAR_DAMAGE = 118;
    public static int SCIMITAR_HITCHANCE = 85;
    public static int SCIMITAR_BUY_PRICE = 600;
    public static int SCIMITAR_SELL_PRICE = 100;

    /**
     * Constructor
     */
    public Scimitar() {
        //cant find verb other than attacks?
        super("Scimitar", 's', SCIMITAR_DAMAGE, "performs spinning attack on", SCIMITAR_HITCHANCE,SCIMITAR_BUY_PRICE,SCIMITAR_SELL_PRICE);
    }

}

package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Club extends RuneWeapon {

    public static int CLUB_DAMAGE = 103;
    public static int CLUB_HITCHANCE = 80;
    public static int CLUB_BUY_PRICE = 600;
    public static int CLUB_SELL_PRICE = 100;

    /**
     * Constructor
     */
    public Club() {
        super("Club", '!', CLUB_DAMAGE, "bonks", CLUB_HITCHANCE,CLUB_BUY_PRICE,CLUB_SELL_PRICE);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {}
}

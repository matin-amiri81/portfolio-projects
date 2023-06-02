package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AOEAttackAction;

import java.util.List;

public class GiantPincer extends AOEWeapons {
    public static int GIANT_PINCER_DAMAGE = 527;
    public static int GIANT_PINCER_HITCHANCE = 100;
    public static int GIANT_PINCER_BUY_PRICE = -1;
    public static int GIANT_PINCER_SELL_PRICE = -1;

    public GiantPincer() {
        super("Giant Pincer", '^', GIANT_PINCER_DAMAGE, "Pinches", GIANT_PINCER_HITCHANCE,GIANT_PINCER_BUY_PRICE,GIANT_PINCER_SELL_PRICE);
    }

}

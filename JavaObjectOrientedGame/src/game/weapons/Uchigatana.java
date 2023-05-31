package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.UnsheatheAction;
import game.enums.Ability;

/**
 * Class representing a Uchigatana.
 * This weapon has a special skill
 * @author danny leung
 * @see RuneWeapon
 */
public class Uchigatana extends RuneWeapon {
        public static int UCHIGATANA_DAMAGE = 80;
        public static int UCHIGATANA_HITCHANCE = 80;
        public static int UCHIGATANA_BUY_PRICE = 5000;
        public static int UCHIGATANA_SELL_PRICE = 500;
        public Uchigatana() {
            super("Uchigatana", ')', UCHIGATANA_DAMAGE, "attacks", UCHIGATANA_HITCHANCE, UCHIGATANA_BUY_PRICE,UCHIGATANA_SELL_PRICE);
            this.addCapability(Ability.Skill);
        }

    @Override
    public Action getSkill(Actor target, String direction) {
        return new UnsheatheAction(target, direction,this);
    }
}

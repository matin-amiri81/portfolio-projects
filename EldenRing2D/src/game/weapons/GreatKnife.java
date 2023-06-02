package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.QuickStepAction;
import game.enums.Ability;

/**
 * Class representing a Great Knife.
 * This weapon has a special skill
 * @author danny leung
 * @see RuneWeapon
 */
public class GreatKnife extends RuneWeapon {
    public static int GREAT_KNIFE_DAMAGE = 75;
    public static int GREAT_KNIFE_HITCHANCE = 70;
    public static int GREAT_KNIFE_BUY_PRICE = 3500;
    public static int GREAT_KNIFE_SELL_PRICE = 600;

    public GreatKnife() {
        super("Great Knife", '/', GREAT_KNIFE_DAMAGE, "attacks", GREAT_KNIFE_HITCHANCE,GREAT_KNIFE_BUY_PRICE,GREAT_KNIFE_SELL_PRICE);
        this.addCapability(Ability.Skill);
    }

    @Override
    public Action getSkill(Actor target, String direction) {
        return new QuickStepAction(target, direction,this);
    }
}


package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AOEAttackAction;
import game.actions.AttackAction;
import game.enums.Ability;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class that represents a weapon that can attack multiple targets at once
 */
public abstract class AOEWeapons extends RuneWeapon {

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public AOEWeapons(String name, char displayChar, int damage, String verb, int hitRate,int buyPrice,int sellPrice){
        super(name, displayChar, damage, verb, hitRate,buyPrice,sellPrice);
        this.addCapability(Ability.Skill);
    }


    /**
     * @param target target actor
     * @param direction direction of the target
     * @return the special area attack action this weapon is capable of
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new AOEAttackAction(target, this);
    }
}

package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AOEAttackAction;
import game.enums.Ability;
import game.enums.EnemyType;

/**
 * an abstract class representing a Giant Enemy
 * @author matin amiri
 * @see Enemies
 */
public abstract class GiantEnemy extends Enemies{
    /**
     * Constructor
     * sets default giant capabilities
     * @param name the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints the Actor's starting hit points
     */
    public GiantEnemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(EnemyType.GIANT);
        this.addCapability(Ability.AOEAttack);
        this.addCapability(Ability.IntrinsicSkill);
    }

}

package game.actors.enemies;

import game.enums.EnemyType;

/**
 * An abstract class that represents a Canine Enemy
 * @author matin amiri
 * @see Enemies
 * @see EnemyType
 */
public abstract class CanineEnemy extends Enemies{

    /**
     * Constructor
     * sets the EnemyType to CANINE
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public CanineEnemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(EnemyType.CANINE);
    }
}

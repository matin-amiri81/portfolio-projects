package game.actors.enemies;

import game.enums.EnemyType;

public abstract class GodrickEnemy extends Enemies{
    public GodrickEnemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(EnemyType.GODRICK);
    }

}

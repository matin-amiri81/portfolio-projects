package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DeathAction;
import game.behaviours.SkellitonDeathBehaviour;
import game.enums.EnemyType;
import game.enums.Status;

/**
 * An abstract class represents a Skeletal Enemy.
 * @author matin amiri
 * @see Enemies
 * @see SkellitonDeathBehaviour
 * @see DoNothingAction
 */
public abstract class SkeletalEnemy extends Enemies{
    /**
     * Constructor.
     * sets default capabilities of a Skeletal Enemy
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public SkeletalEnemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(EnemyType.SKELETAL);
        this.addCapability(Status.RESPAWNABLE);
    }

    /**
     * overrides die method from Enemies and replaces itself with a pile of bones on the map
     * @param map the map the actor is on
     * @param display the display
     * @return a DoNothingAction
     */
    @Override
    public Action die(GameMap map, Display display) {
        Location location = map.locationOf(this);
        map.removeActor(this);
        location.addActor(new PileOfBones(this.newInstance(),this.getThisRunes()));
        return new DoNothingAction();
    }

    /**
     * abstract so that all skeletal enemies must implement this method
     * @return a new instance of the specific skeletal enemy
     */
    public abstract SkeletalEnemy newInstance();
}

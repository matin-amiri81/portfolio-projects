package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.SkeletalEnemy;

/**
 * revives a pile of bones back to their skeletal class
 * @author matin amiri
 * @see SkeletalEnemy
 * @see Action
 */
public class BoneReviveAction extends Action {
    private SkeletalEnemy skeletalEnemy;

    /**
     * constructor
     * @param skeletalEnemy a new instance of the skeleton to replace the pile of bones on revival
     */
    public BoneReviveAction(SkeletalEnemy skeletalEnemy) {
        this.skeletalEnemy = skeletalEnemy;
    }

    /**
     * revive the pile of bones back to a skeleton
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string "pile of bones transforms back to a skelliton"
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        map.removeActor(actor);
        location.addActor(skeletalEnemy);
        return ("pile of bones transforms back to a skelliton");
    }

    /**
     *  no menu description for this action as a player should not be able to perform this
     * @param actor The actor performing the action.
     * @return null
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }

}

package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BoneReviveAction;
import game.actions.DeathAction;
import game.actors.enemies.SkeletalEnemy;
import game.enums.Status;

/**
 * A class that represents a clock behaviour for skeletal enemies once they die (pile of bones).
 * @author matin amiri
 * @see Behaviour
 * @see DeathAction
 * @see SkeletalEnemy
 * @see game.actors.enemies.PileOfBones
 *
 */
public class SkellitonDeathBehaviour implements Behaviour {
    public static final int PILE_OF_BONES_COUNTER = 3;
    private int deathCounter;
    private SkeletalEnemy skeletalEnemy;

    /**
     * Constructor. sets the death counter to 0
     * @param skeletalEnemy the skeletal enemy to be revived
     */
    public SkellitonDeathBehaviour(SkeletalEnemy skeletalEnemy) {
        this.deathCounter = 0;
        this.skeletalEnemy = skeletalEnemy;

    }

    /**
     * revives the skeletal enemy after three turns if the pile of bones is not attacked
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return a DoNothingAction if the death counter is less than 3, otherwise returns a BoneReviveAction
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (deathCounter <= PILE_OF_BONES_COUNTER){
            deathCounter++;
            return new DoNothingAction();
        }
        else {
            actor.removeCapability(Status.RESPAWNABLE);
            return new BoneReviveAction(skeletalEnemy);
        }
    }
}

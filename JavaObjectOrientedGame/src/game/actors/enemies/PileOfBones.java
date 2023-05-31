package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.DeathAction;
import game.behaviours.Behaviour;
import game.behaviours.SkellitonDeathBehaviour;
import game.enums.EnemyType;
import game.enums.Status;

/**
 * A class representing a Pile Of Bones.
 * @author matin amiri
 * @see Enemies
 * @see SkellitonDeathBehaviour
 * @see DeathAction
 */
public class PileOfBones extends Enemies {

    private SkeletalEnemy skeletalEnemy;
    private Behaviour behaviour;

    /**
     * Constructor.
     * transfers all weapons from the now diseased skeletal enemy onto itself
     * adds default capabilities of a Pile Of Bones as well as the single behaviour it has
     * @param skeletalEnemy the skeletal enemy that died and will be reborn
     * @param runes the amount of runes the skeletal enemy had when it died
     */
    public PileOfBones(SkeletalEnemy skeletalEnemy, int runes) {
        super("Pile Of Bones", 'X', 1,runes);
        for (WeaponItem weaponItem : skeletalEnemy.getWeaponInventory()) {
            this.addItemToInventory(weaponItem);
        }
        this.skeletalEnemy = skeletalEnemy;
        behaviour = new SkellitonDeathBehaviour(skeletalEnemy);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(EnemyType.SKELETAL);
    }

    /**
     * overrides play-turn
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the action given by SkellitonDeathBehaviour
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!this.isConscious()){
            return new DeathAction();
        }
        return behaviour.getAction(this, map);
    }

    /**
     * @return the amount of runes the skeletal enemy would have given if directly killed
     */
    @Override
    int getThisRunes() {
        return skeletalEnemy.getThisRunes();}


}

package game.actors.enemies;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.*;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Ability;
import game.enums.EnemyType;
import game.enums.Status;
import game.runes.RuneManager;
import game.utils.RandomNumberGenerator;
import game.utils.Resettable;

import java.util.HashMap;
import java.util.Map;

/**
 * An abstract class that represents an enemy/active npc in the game.
 * @author matin amiri
 * @see Actor
 * @see Resettable
 * @see EnemyType
 * @see Status
 * @see Behaviour
 * @see AttackBehaviour
 * @see WanderBehaviour
 * @see FollowBehaviour
 */
public abstract class Enemies extends Actor implements Resettable {

    private int deSpawnChance = 10;
    private Map<Integer, Behaviour> behaviours = new HashMap<>();


    /**
     * Constructor
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemies(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.registerResettable();
        RuneManager.getInstance().registerRunePossessor(this,this.getThisRunes());
        behaviours.put(999,new WanderBehaviour());
    }

    /**
     * constructor for actors that need to pass in runes before register rune possessors is called
     * @param name the name of the actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints the Actor's starting hit points
     * @param runes the number of runes the actor will drop upon death
     */
    public Enemies(String name, char displayChar, int hitPoints, int runes) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.registerResettable();
        RuneManager.getInstance().registerRunePossessor(this,runes);
        behaviours.put(999,new WanderBehaviour());
    }

    /**
     * returns an action in the priority of: attacking, following, wandering and if none of the above, do nothing
     * if the actors death does not occur in an attack because they are respawnable, method die is called
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the action the actor will take this turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        boolean deSpawn = RandomNumberGenerator.getRandomInt(100) < this.deSpawnChance;
        if (deSpawn && !this.hasCapability(Status.FOLLOWING)){ // && !hasCapability respawnable // check spec to see if pile of bones can despawn
            return new DespawnAction();
        }
        if (!this.isConscious()) {
            return (this.die(map,display));
        }
        //checks to see if there is a followable (player) in the immediate vicinity
        for(Exit exit : map.locationOf(this).getExits()){
            if(exit.getDestination().containsAnActor())
            {
                if (exit.getDestination().getActor().hasCapability(Status.FOLLOWABLE)){
                    behaviours.put(10, new FollowBehaviour(exit.getDestination().getActor()));
                    this.addCapability(Status.FOLLOWING);
                }
            }
        }
        behaviours.put(1, new AttackBehaviour());
        //loops behaviours here
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        //loops behaviours here
        return new DoNothingAction();
    }

    /**
     * returns all possible actions another actor in the immediate vicinity can take on this actor
     * including attacks with/without weapon items and
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of actions that can be performed on this actor
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // find out if other actor is hostile to enemy
        // change the has capability to something more resuable
        EnemyType otherEnemyType;
        EnemyType thisEnemyType;
        try{ otherEnemyType = otherActor.findCapabilitiesByType(EnemyType.class).get(0);}
        catch (Exception e){otherEnemyType = null;}
        thisEnemyType = this.findCapabilitiesByType(EnemyType.class).get(0);
        boolean hostile = ((otherActor.hasCapability(Status.HOSTILE_TO_ENEMY))&& (thisEnemyType != otherEnemyType));
        if(hostile){
            actions.add(new AttackAction(this, direction));
            for (WeaponItem weaponItem : otherActor.getWeaponInventory()){
                actions.add(new AttackAction(this, direction,weaponItem));
                if (weaponItem.hasCapability(Ability.Skill)) {
                    actions.add(weaponItem.getSkill(this, direction));
                }
            }
        }
        return actions;
    }

    /**
     * when map is reset this method removes the actor from the map
     * @param map the map the actor is on
     */
    @Override
    public void reset(GameMap map) {
        map.removeActor(this);
    }

    /**
     * default method to die if actor is respawn-able, can be overwritten for special death conditions
     * @param map the map the actor is on
     * @param display the display
     * @return a death action
     */
    public Action die(GameMap map, Display display){
        return new DeathAction();
    }

    /**
     * returns the number of runes the actor will drop upon death
     * @return the number of runes the actor will drop upon death
     */
    abstract int getThisRunes();

}


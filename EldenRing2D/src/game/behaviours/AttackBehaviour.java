package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AOEAttackAction;
import game.actions.AttackAction;
import game.actors.enemies.Enemies;
import game.enums.Ability;
import game.enums.EnemyType;
import game.enums.Status;
import game.utils.RandomNumberGenerator;

import java.util.List;

/**
 * A class that figures out a if there is an Attack for the Actor to do.
 * @author matin amiri
 * @see Behaviour
 * @see AttackAction
 * @see Enemies
 * @see WeaponItem
 */
public class AttackBehaviour implements Behaviour {


    public AttackBehaviour (){
    }

    /**
     * Returns a random attacking action out of all possible attack actions
     * this actor can perform this turn or null if there arnt any
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an attacking action that attacks a target if possible, else null.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        EnemyType selfType;
        EnemyType targetType;
        ActionList actions = new ActionList();
        try{
            selfType = actor.findCapabilitiesByType(EnemyType.class).get(0);
        } catch (IndexOutOfBoundsException e){selfType = null;}

        //for all adjacent targets that are not of the dame type and this actors weapons including intrinsic weapon
        // add attack actions to action list reflecting the possible attacks (special skills included)
        Location here = map.locationOf(actor);
        List<Exit> exits = here.getExits();
        for (Exit exit: exits){
            if (exit.getDestination().containsAnActor()) {
                Actor target = exit.getDestination().getActor();
                //check if target is hostile to actor
                try{
                    targetType = target.findCapabilitiesByType(EnemyType.class).get(0);
                } catch (IndexOutOfBoundsException e){targetType = null;}
                boolean isHostileToTarget = (selfType != targetType);

                if (target.hasCapability(Status.HOSTILE_TO_ENEMY) && isHostileToTarget){
                    actions.add(targetActions(actor,target,exit.getName(),here));
                }
            }
        }
        //pick randomly from the list of possible actions
        int actionSize = actions.size();
        if (actionSize > 0){
            int randomActionInt = RandomNumberGenerator.getRandomInt(0, actionSize-1);
            return actions.get(randomActionInt);
        }
        return null;
    }
    private ActionList targetActions(Actor actor,Actor target,String direction, Location location) {
        ActionList actions = new ActionList();
        actions.add(new AttackAction(target, direction, actor.getIntrinsicWeapon()));

        WeaponItem weapon = null;
        int weaponInventorySize = actor.getWeaponInventory().size();
        if (weaponInventorySize > 0){
            int randomWeapon =RandomNumberGenerator.getRandomInt(0, weaponInventorySize-1);
            weapon = actor.getWeaponInventory().get(randomWeapon);
        }
        //has weapon
        if (weapon != null) {
            actions.add(new AttackAction(target, direction, weapon));
            if (weapon.hasCapability(Ability.Skill)) {
                actions.add(weapon.getSkill(target, direction));
            }
        }
        return actions;
    }
}

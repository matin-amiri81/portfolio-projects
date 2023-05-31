package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.EnemyType;
import game.enums.Status;
import game.behaviours.WanderBehaviour;
import game.actions.AttackAction;
import game.behaviours.Behaviour;
import game.utils.RandomNumberGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * A class represents a Lone Wolf.
 * @author Adrian Kristanto
 * Modified by: matin amiri
 * @see CanineEnemy
 */
public class LoneWolf extends CanineEnemy {

    /**
     * Constructor
     */
    public LoneWolf() {
        super("Lone Wolf", 'h', 102);

    }

    /**
     * @return a IntrinsicWeapon of the Lone Wolf
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bites", 95);
    }

    /**
     * randomly generated an amount of runes within a range and returns it
     * @return the amount of this Lone Wolf's runes
     */
    @Override
    int getThisRunes() {
        return RandomNumberGenerator.getRandomInt(55,1470);
    }
}

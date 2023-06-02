package game.gounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.utils.RandomNumberGenerator;

/**
 * A type of ground that damages actors that stand on it
 * but also has a chance to give and remove an immunity to damage from this ground
 */
public abstract class RottingGround extends Ground {

    public static int IMMUNITY_TO_ROT_CHANCE = 50;

    private int damage;

    /**
     * constructor
     * @param displayChar the character to display
     * @param damage the amount of damage to deal
     */
    public RottingGround(char displayChar, int damage) {
        super(displayChar);
        this.damage = damage;
    }

    /**
     * deals damage to the actor on the ground
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        if(!location.containsAnActor()){return;}
        if(location.getActor().hasCapability(Status.IMMUNETOROT)){
            if(RandomNumberGenerator.getRandomInt(100)>IMMUNITY_TO_ROT_CHANCE){return;}
            location.getActor().removeCapability(Status.IMMUNETOROT);
        }
        if(location.getActor().hasCapability(Status.IMMUNETOROT)){return;}
        location.getActor().hurt(this.damage);
        //if this was an Action we could use display
    }
}

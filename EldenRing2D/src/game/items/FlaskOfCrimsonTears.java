package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DrinkFlaskAction;
import game.utils.Resettable;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Flask of Crimson Tears. It can be used 3 time to heal the actor.
 * @author matin amiri
 * @see Item
 * @see DrinkFlaskAction
 * @see Resettable
 */
public class FlaskOfCrimsonTears extends Item implements Resettable {

    public static int FLASK_OF_CRIMSON_TEAR_USES = 3;

    //this couldve been a weapon item that used get skill to heal the actor
    private int uses;
    private int maxHp;


    /**
     * Constructor. sets the uses and registers the item as resettable
     * @param maxHp the maximum hp of the actor
     */
    public FlaskOfCrimsonTears(int maxHp) {
        super("Flask of Crimson Tears", 'F', false);
        this.uses = 3;
        this.registerResettable();
        this.maxHp = maxHp;
    }

    /**
     * checks if there are still uses left for the flask if so it returns a DrinkFlaskAction
     * @return a list containing just a DrinkFlaskAction if there are still uses left, otherwise returns an empty list
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions= new ArrayList<Action>();
        if (this.uses >= 0){
            actions.add(new DrinkFlaskAction(this));
        }
        return actions;
    }

    /**
     * @return a string indicating that the actor drank from the flask if there
     * are still uses left,otherwise returns a string indicating that the flask is empty
     */
    public String use(){
        if (this.uses > 0){
            this.uses --;
            return "Drank from Flask of Crimson Tears";
        }
        else {
            return "Flask of Crimson Tears is empty";
        }
    }

    /**
     * @return the number of uses left for the flask
     */
    public int getUses(){
        return this.uses;
    }

    /**
     * resets the uses of the flask to 3
     * @param map the GameMap containing the Actor
     */
    @Override
    public void reset(GameMap map) {
        this.uses = FLASK_OF_CRIMSON_TEAR_USES;
    }

    /**
     * override to not remove this ressetable from the map
     */
    @Override
    public void removeResettable() { }

    /**
     * @return the maximum hp of the actor
     */
    public int getMaxHp() {
    	return this.maxHp;
    }
}

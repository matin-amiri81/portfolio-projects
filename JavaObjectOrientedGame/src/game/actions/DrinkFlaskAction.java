package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.FlaskOfCrimsonTears;

/**
 * Action to drink from the flask of crimson tears and reset actors health
 * @author matin amiri
 * @see Action
 * @see FlaskOfCrimsonTears
 * @see Actor
 */
public class DrinkFlaskAction extends Action {

    private FlaskOfCrimsonTears flask;

    /**
     * constructor
     * @param flask the flask to be drunk from
     */
    public DrinkFlaskAction(FlaskOfCrimsonTears flask) {
        this.flask = flask;
    }

    /**
     * reset the actors health to max if the flask is not empty
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of drinking from the flask, either successful or the flask is empty
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.heal(flask.getMaxHp());
        return actor + flask.use();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " drink from the flask of crimson tears (" + flask.getUses() +"/3)" ;
    }
}

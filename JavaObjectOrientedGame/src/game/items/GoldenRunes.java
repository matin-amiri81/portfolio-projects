package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeRunesAction;
import game.actions.DrinkFlaskAction;
import game.enums.Ability;

import java.util.ArrayList;
import java.util.List;

public class GoldenRunes extends Item {

    public GoldenRunes() {
        super("Golden Runes", '⊛', true);
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions= new ArrayList<>();
        actions.add(new ConsumeRunesAction(this));
        return actions;
    }
}

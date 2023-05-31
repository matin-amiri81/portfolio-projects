package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.enums.Ability;
import game.runes.RuneManager;

/**
 * an item that can be sold or traded
 */
public class RemembranceOfTheGrafted extends RuneItem {
    public static final int REMEMBRANCE_OF_THE_GRAFTED_BUY_PRICE = 20000;
    public static final int REMEMBRANCE_OF_THE_GRAFTED_SELL_PRICE = -1;
    public RemembranceOfTheGrafted() {
        super("Remembrance Of The Grafted", 'O', true,REMEMBRANCE_OF_THE_GRAFTED_SELL_PRICE,REMEMBRANCE_OF_THE_GRAFTED_BUY_PRICE);
        this.addCapability(Ability.GodrickTrades);
    }
}

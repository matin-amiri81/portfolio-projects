package game.runes;

/**
 * Interface for buyable/sellable things in the game
 * @author matin amiri
 */
public interface IRuneItem {
    /**
     * @return the price to buy this
     */
    public int getBuyPrice();

    /**
     * @return the money recieved for selling this
     */
    public int getSellPrice();

}

package game.weapons;

/**
 * Class representing a Grossmesser weapon
 * This weapon has an AOE attack
 */
public class Grossmesser extends AOEWeapons {
    public static int GROSSMESSER_DAMAGE = 115;
    public static int GROSSMESSER_HITCHANCE = 85;
    public static int GROSSMESSER_BUY_PRICE = 600;
    public static int GROSSMESSER_SELL_PRICE = 100;

    /**
     * Constructor
     */
    public Grossmesser() {
        //cant find verb other than attacks?
        super("Grossmesser", '?', GROSSMESSER_DAMAGE, "performs spinning attack on", GROSSMESSER_HITCHANCE,GROSSMESSER_BUY_PRICE,GROSSMESSER_SELL_PRICE);
    }
}

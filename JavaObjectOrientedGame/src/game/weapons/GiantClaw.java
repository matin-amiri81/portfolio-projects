package game.weapons;

public class GiantClaw extends AOEWeapons{
    public static int GIANT_CLAW_DAMAGE = 204;
    public static int GIANT_CLAW_HITCHANCE = 90;
    public static int GIANT_CLAW_BUY_PRICE = -1;
    public static int GIANT_CLAW_SELL_PRICE = -1;

    public GiantClaw() {
        super("Giant Claw",  '^', GIANT_CLAW_DAMAGE, "Pinches", GIANT_CLAW_HITCHANCE,GIANT_CLAW_BUY_PRICE,GIANT_CLAW_SELL_PRICE);
    }
}

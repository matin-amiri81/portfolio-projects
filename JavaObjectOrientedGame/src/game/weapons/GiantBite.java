package game.weapons;

public class GiantBite extends AOEWeapons{
    public static int GIANT_BITE_DAMAGE = 214;
    public static int GIANT_BITE_HITCHANCE = 90;
    public static int GIANT_BITE_BUY_PRICE = -1;
    public static int GIANT_BITE_SELL_PRICE = -1;



    public GiantBite() {

        super("Giant Biite", 'w', GIANT_BITE_DAMAGE, "Takes Giant Bite", GIANT_BITE_HITCHANCE, GIANT_BITE_BUY_PRICE,GIANT_BITE_SELL_PRICE);
    }
}

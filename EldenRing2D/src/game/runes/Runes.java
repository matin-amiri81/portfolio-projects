package game.runes;

/**
 * A class that represents the runes an actor holds, or rather the bank of
 * rune manager holds for them
 * @author matin amiri
 * @see RuneManager
 */
public class Runes{

    private int runes;

    public Runes(int runes){
        this.runes = runes;
    }

    int getRunes(){
        return runes;
    }
    public void addRunes(int amount){
        runes += amount;
    }

    public boolean removeRunes(int amount){
        if (runes >= amount){
            runes -= amount;
            return true;
        }
        return false;
    }



}

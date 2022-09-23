package game.weapons;


import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Element;

public class Bubble extends WeaponItem{

    public Bubble() {
        super("Bubble", 'B', 25, SpecialAttack.BUBBLE.toString(), 80);
        this.addCapability(Element.WATER);
    }

    public String toString() {
        return "Bubble";
    }
}

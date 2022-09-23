package game.weapons;


import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Element;

public class VineWhip extends WeaponItem{

    public VineWhip() {
        super("VineWhip", 'V', 30, SpecialAttack.VINEWHIP.toString(), 70);
        this.addCapability(Element.GRASS);
    }

    public String toString() {
        return "Vine Whip";
    }
}

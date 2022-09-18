package weapons;


import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Element;

public class Bubble extends WeaponItem{
    final Element weaponType;

    public Bubble() {
        super("Bubble", 'B', 25, SpecialAttack.BUBBLE.toString(), 80);
        this.weaponType = Element.WATER;
        this.addCapability(Element.WATER);
    }
}

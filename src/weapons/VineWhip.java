package weapons;


import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Element;

public class VineWhip extends WeaponItem{
    final Element weaponType;

    public VineWhip() {
        super("VineWhip", 'V', 30, SpecialAttack.VINEWHIP.toString(), 70);
        this.weaponType = Element.GRASS;
        this.addCapability(Element.GRASS);
    }
}

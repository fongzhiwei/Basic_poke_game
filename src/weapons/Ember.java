package weapons;


import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Element;

public class Ember extends WeaponItem{
    final Element weaponType;

    public Ember() {
        super("Ember", 'E', 20, SpecialAttack.EMBER.toString(), 90);
        this.weaponType = Element.FIRE;
        this.addCapability(Element.FIRE);
    }
}

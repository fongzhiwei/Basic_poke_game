package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Element;
import game.Status;

public class SpecialWeapon extends WeaponItem {

    public SpecialWeapon(String name, char displayChar, int damage, String verb, int hitRate, Element weaponType) {
        super(name, displayChar, damage, verb, hitRate);
        this.addCapability(weaponType);
        this.addCapability(Status.WEAPON);
    }

    public String toString() {
        return this.verb();
    }
}

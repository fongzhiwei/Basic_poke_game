package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Element;
import game.Status;

/**
 * Class representing the pokemon's special weapon
 *
 * Created by:
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 *
 * Modified by:
 *
 */
public class SpecialWeapon extends WeaponItem {

    /**
     * Constructor.
     *
     * @param name          name of the weapon
     * @param displayChar   character to use for display when weapon is on the ground
     * @param damage        amount of damage this weapon does
     * @param verb          verb to use for this weapon, e.g. "sparks", "burbles"
     * @param hitRate       the probability/chance to hit the target
     * @param weaponType    the weapon's element type
     */
    public SpecialWeapon(String name, char displayChar, int damage, String verb, int hitRate, Element weaponType) {
        super(name, displayChar, damage, verb, hitRate);
        this.addCapability(weaponType);
        this.addCapability(Status.WEAPON);
    }
}

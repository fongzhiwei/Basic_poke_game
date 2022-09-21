package game.weapons;


import edu.monash.fit2099.engine.weapons.WeaponItem;

public class Ember extends WeaponItem{

    public Ember() {
        super("Ember", 'E', 20, SpecialAttack.EMBER.toString(), 90);
    }

    public String toString() {
        return "Ember";
    }
}

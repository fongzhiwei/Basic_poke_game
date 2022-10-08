package game.pokemon;


import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Element;
import game.Utils;
import game.behaviours.EvolveBehaviour;
import game.weapons.SpecialWeapon;

/**
 * Class representing the Charmeleon
 *
 * Created by:
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 *
 * Modified by:
 *
 */
public class Charmeleon extends Pokemon {
    /**
     * Constructor.
     */
    public Charmeleon() {
        super("Charmeleon", 'C', 150);
        this.addCapability(Element.FIRE);
        this.getBehaviours().put(0, new EvolveBehaviour());
    }

    /**
     * Creates and returns an intrinsic weapon. By default, the Charmeleon 'scratch' for 10 damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "scratch");
    }

    /**
     * Switch the pokemon's weapon in the game
     *
     * @param isEquipping boolean value representing if the pokemon is equipping any weapon at the moment
     */
    public void toggleWeapon(boolean isEquipping) {
        if (this.pokemonLocation.getGround().hasCapability(Element.FIRE)) {
            if (!isEquipping) {
                SpecialWeapon weapon;
                int randomNum = Utils.nextNum(0, 2);

                if (randomNum == 0) {
                    weapon = new SpecialWeapon("Ember", 'E', 20, "sparks", 90, Element.FIRE);
                }
                else {
                    weapon = new SpecialWeapon("Blaze", 'B', 60, "burns", 90, Element.FIRE);
                }

                this.addItemToInventory(weapon);
            }
        }
        else {
            if (isEquipping) {
                this.removeItemFromInventory((Item) this.getWeapon());
            }
        }
    }
}

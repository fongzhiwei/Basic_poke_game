package game.pokemon;


import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Element;
import game.Utils;
import game.items.Fire;
import game.weapons.SpecialWeapon;

/**
 * Class representing the Charizard
 *
 * Created by:
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 *
 * Modified by:
 *
 */
public class Charizard extends Pokemon{

    /**
     * Constructor.
     */
    public Charizard() {
        super("Charizard", 'Z', 250);
        this.addCapability(Element.FIRE);
        this.addCapability(Element.DRAGON);
    }

    /**
     * Creates and returns an intrinsic weapon. By default, the Charizard 'scratch' for 10 damage.
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
                int randomNum = Utils.nextNum(0, 3);

                if (randomNum == 0) {
                    weapon = new SpecialWeapon("Ember", 'E', 20, "sparks", 90, Element.FIRE);
                }
                else if (randomNum == 1) {
                    weapon = new SpecialWeapon("Blaze", 'B', 60, "burns", 90, Element.FIRE);
                }
                else {
                    weapon = new SpecialWeapon("Fire Spin", 'F', 70, "blasts", 90, Element.FIRE);

                    for (Exit elem: this.pokemonLocation.getExits()) {
                        elem.getDestination().addItem(new Fire());

                        if (elem.getDestination().containsAnActor() && !elem.getDestination().getActor().hasCapability(Element.FIRE)) {
                            Pokemon target = (Pokemon) elem.getDestination().getActor();

                            target.setEffectTurnCount(0);
                            target.hurt(10);
                        }
                    }
                }

                this.addItemToInventory(weapon);
            }
        }
        else {
            if (isEquipping) {
                this.removeItemFromInventory((Item) this.getWeapon());

                for (Exit elem: this.pokemonLocation.getExits()) {
                    Fire fire = new Fire();

                    if (elem.getDestination().getItems().contains(fire)) {
                        elem.getDestination().removeItem(fire);
                    }
                }
            }
        }
    }
}

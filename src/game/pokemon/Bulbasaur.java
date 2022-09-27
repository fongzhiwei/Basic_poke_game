package game.pokemon;


import edu.monash.fit2099.engine.items.Item;
import game.Element;
import game.Status;
import game.time.TimePerception;
import game.weapons.SpecialWeapon;

/**
 * Created by:
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 *
 * Modified by:
 *
 */
public class Bulbasaur extends Pokemon implements TimePerception {
    /**
     * Constructor.
     */
    public Bulbasaur() {
        super("Bulbasaur", 'b', 100);
        // HINT: add more relevant behaviours here
        this.addCapability(Element.GRASS);
        this.registerInstance();
    }

    /**
     * Switch the pokemon's weapon in the game
     *
     * @param isEquipping boolean value representing if the pokemon is equipping any weapon at the moment
     */
    @Override
    public void toggleWeapon(boolean isEquipping) {
        if (this.pokemonLocation.getGround().hasCapability(Element.GRASS)) {
            if (!isEquipping) {
                SpecialWeapon bubble = new SpecialWeapon("VineWhip", 'V', 30, "whips", 70, Element.GRASS);
                this.addItemToInventory(bubble);
            }
        }
        else {
            if (isEquipping) {
                this.removeItemFromInventory((Item) this.getWeapon());
            }
        }
    }

    /**
     * Bulbasaur will be hurt during the period of day
     */
    @Override
    public void dayEffect() {
        // Bulbasaur will be hurt by 5 points
        super.hurt(5);
    }

    /**
     * Bulbasaur will be healed during the period of night
     */
    @Override
    public void nightEffect() {
        // Bulbasaur will be healed by 5 points
        super.heal(5);
    }
}

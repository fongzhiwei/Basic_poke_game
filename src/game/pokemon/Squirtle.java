package game.pokemon;


import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import game.Element;
import game.time.TimePerception;
import game.weapons.SpecialWeapon;

import java.util.List;

/**
 * Created by:
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 *
 * Modified by:
 *
 */
public class Squirtle extends Pokemon implements TimePerception {
    /**
     * Constructor.
     */
    public Squirtle() {
        super("Squirtle", 's', 100);
        // HINT: add more relevant behaviours here
        this.addCapability(Element.WATER);
        this.registerInstance();
    }

    /**
     * Switch the pokemon's weapon in the game
     * @param isEquipping boolean value representing if the pokemon is equipping any weapon at the moment
     */
    @Override
    public void toggleWeapon(boolean isEquipping) {
        List<Exit> exits = this.pokemonLocation.getExits();

        for(Exit elem: exits) {
            if (!elem.getDestination().containsAnActor()) {
                exits.remove(elem);
            }
            else {
                if (!elem.getDestination().getActor().hasCapability(Element.FIRE)) {
                    exits.remove(elem);
                }
            }
        }

        if (this.pokemonLocation.getGround().hasCapability(Element.WATER) || exits.size() > 0) {
            if (!isEquipping) {
                SpecialWeapon bubble = new SpecialWeapon("Bubble", 'B', 25, "burbles", 80, Element.WATER);
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
     * Squirtle will be hurt during the period of day
     */
    @Override
    public void dayEffect() {
        // Squirtle will be hurt by 10 points
        super.hurt(10);
    }

    /**
     * Squirtle will be healed during the period of night
     */
    @Override
    public void nightEffect() {
        // Squirtle will be healed by 10 points
        super.heal(10);
    }
}

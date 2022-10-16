package game.pokemon;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Element;
import game.Status;
import game.behaviours.EvolveBehaviour;
import game.items.PokemonEgg;
import game.time.TimePerception;
import game.trade.Tradable;
import game.weapons.SpecialWeapon;

import java.util.ArrayList;

/**
 * Class representing the Charmander
 *
 * Created by:
 * @author Riordan D. Alfredo
 *
 * Modified by:
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 */
public class Charmander extends Pokemon implements TimePerception, Tradable {
    /**
     * Constructor.
     */
    public Charmander() {
        super("Charmander", 'c', 100);
        // HINT: add more relevant behaviours here
        this.getBehaviours().put(0, new EvolveBehaviour());
        this.addCapability(Element.FIRE);
        this.registerInstance();
    }

    /**
     * Creates and returns an intrinsic weapon. By default, the Charmander 'scratch' for 10 damage.
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
                SpecialWeapon ember = new SpecialWeapon("Ember", 'E', 20, "sparks", 90, Element.FIRE);
                this.addItemToInventory(ember);
            }
        }
        else {
            if (isEquipping) {
                this.removeItemFromInventory((Item) this.getWeapon());
            }
        }
    }

    /**
     * Charmander will be healed during the period of day
     */
    @Override
    public void dayEffect() {
        // Charmander will be healed by 10 points
        super.heal(10);
    }

    /**
     * Charmander will be hurt during the period of night
     */
    @Override
    public void nightEffect() {
        // Charmander will be hurt by 10 points
        super.hurt(10);
    }

    /**
     * This is a method to trade with player using candy to get Charmander pokemon egg
     * And, the pokemon egg will be store in the inventory list.
     *
     * @param player the person that want to trade
     */
    public void tradedWith(Actor player) {
        PokemonEgg pokemonEgg = new PokemonEgg(BasePokemon.CHARMANDER);
        player.addItemToInventory(pokemonEgg);
    }

}

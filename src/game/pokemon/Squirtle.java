package game.pokemon;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import game.Element;
import game.items.PokemonEgg;
import game.time.TimePerception;
import game.trade.Tradable;
import game.weapons.SpecialWeapon;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the Squirtle
 *
 * Created by:
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 * @version 1.0
 *
 *
 */
public class Squirtle extends Pokemon implements TimePerception, Tradable {
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
        ArrayList<Integer> posIndex = new ArrayList<>();

        for(int i = 0; i < exits.size(); i++) {
            if (exits.get(i).getDestination().containsAnActor() && exits.get(i).getDestination().getActor().hasCapability(Element.FIRE)) {
                posIndex.add(i);
            }
        }

        if (this.pokemonLocation.getGround().hasCapability(Element.WATER) || posIndex.size() > 0) {
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

    /**
     * This is a method to trade with player using candy to get Squirtle pokemon egg.
     * And, the pokemon egg will be store in the inventory list.
     *
     * @param player the person that want to trade
     */
    public void tradedWith(Actor player) {
        PokemonEgg pokemonEgg = new PokemonEgg(BasePokemon.SQUIRTLE);
        player.addItemToInventory(pokemonEgg);
    }
}

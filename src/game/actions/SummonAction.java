package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.*;
import game.items.Pokeball;
import game.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * An Action to feed a Pokemon.
 * Created by:
 * @author Leong Xin Yun
 *
 * Modified by:
 *
 */
public class SummonAction extends Action {

    /**
     * The Pokemon that is to be summoned (release)
     */
    protected Pokemon target;

    /**
     * The direction of incoming summon (release) action.
     */
    protected String direction;

    /**
     * The pokeball item holding the pokemon to be released
     */
    protected Item pokeball;

    /**
     * Constructor.
     *
     * @param target the Pokemon to summon (release)
     * @param direction the direction of the incoming summon (release) action
     * @param pokeball the pokeball item retaining the pokemon
     *
     */
    public SummonAction(Pokemon target, String direction, Item pokeball) {
        this.target = target;
        this.direction = direction;
        this.pokeball = pokeball;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Element pokefruitType;

        if (ElementsHelper.hasAnySimilarElements(this.target, this.pokefruit.findCapabilitiesByType(Element.class))) {
            pokefruitType = this.pokefruit.findCapabilitiesByType(Element.class).get(0);
            actor.removeItemFromInventory(this.pokefruit);
            return String.format("%s gives a %s Pokefruit to %s.", actor, pokefruitType, AffectionManager.getInstance().increaseAffection(this.target, 20));
        } else {
            actor.removeItemFromInventory(pokefruit);
            return String.format("%s dislikes it! %s.", actor, AffectionManager.getInstance().decreaseAffection(this.target, 10));
        }

        if (this.pokeball.getPokemon().toString().equals(this.target.toString())) {
            // retrieve pokemon next to actor
            Pokemon capturedPokemon = this.pokeball.getPokemon();
            List<Exit> exits = map.locationOf(actor).getExits();
            ArrayList<Integer> validPosIndex = new ArrayList<>();

            for (int i = 0; i < exits.size(); i++) {
                if (exits.get(i).getDestination().getGround().canActorEnter(capturedPokemon)) {
                    validPosIndex.add(i);
                }
            }

            if (validPosIndex.size() > 0) {
                int randomPosIndex = Utils.nextNum(0, validPosIndex.size());
                map.addActor(capturedPokemon, exits.get(randomPosIndex).getDestination());
                capturedPokemon.addCapability(Status.CATCHABLE);
                actor.removeItemFromInventory(pokeball);
                return String.format("I choose you... %s.", this.target);
            }
        }
        return String.format("%s has not captured any %s.", actor, this.target);
    }

    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s summons %s." , actor, this.target);
    }
}

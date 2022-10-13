package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.*;
import game.items.Pokefruit;
import game.pokemon.Pokemon;

/**
 * An Action to feed a Pokemon.
 *
 * Created by:
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 * @version 1.0
 *
 * @see edu.monash.fit2099.engine.actions.Action
 *
 */
public class FeedAction extends Action {
    /**
     * The Pokemon that is to be fed
     */
    protected Pokemon target;

    /**
     * The direction of incoming feed action.
     */
    protected String direction;

    /**
     * The pokefruit item to be fed by the actor to the pokemon.
     */
    protected Item pokefruit;

    /**
     * Constructor.
     *
     * @param target the Pokemon to feed
     * @param direction the direction of the incoming feed action
     * @param pokefruit the pokefruit item to be fed to the pokemon
     *
     */
    public FeedAction(Pokemon target, String direction, Item pokefruit) {
        this.target = target;
        this.direction = direction;
        this.pokefruit = pokefruit;
    }

    /**
     * Execute feed action for player to feed a pokemon on the map
     * @param actor the actor to feed the pokemon (player)
     * @param map the game map
     * @return a string display after action is completed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Element pokefruitType;

        if (ElementsHelper.hasAnySimilarElements(this.target, this.pokefruit.findCapabilitiesByType(Element.class))) {
            pokefruitType = this.pokefruit.findCapabilitiesByType(Element.class).get(0);
            actor.removeItemFromInventory(this.pokefruit);
            return String.format("%s gives a %s Pokefruit to %s.", actor, pokefruitType, AffectionManager.getInstance().increaseAffection(actor,target, 20));
        } else {
            actor.removeItemFromInventory(pokefruit);
            return String.format("%s dislikes it! %s.", this.target, AffectionManager.getInstance().decreaseAffection(actor, target, 10));
        }
    }
    
    /**
     * Method to return appropriate menu description for the action
     * @param actor the actor who performed the action
     * @return a string for menu description
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s gives a %s to %s.(%s AP) at %s",actor,pokefruit, target, AffectionManager.getInstance().getAffectionPoint(actor,target), direction);
    }

}

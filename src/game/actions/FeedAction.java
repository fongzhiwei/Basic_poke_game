package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.*;
import game.items.Pokefruit;
import game.pokemon.Pokemon;

public class FeedAction extends Action {
    protected Pokemon target;
    protected String direction;
    protected Item pokefruit;

    public FeedAction(Pokemon target, String direction, Item pokefruit) {
        this.target = target;
        this.direction = direction;
        this.pokefruit = pokefruit;
    }

    public String execute(Actor actor, GameMap map) {
        Element pokefruitType;

        if (ElementsHelper.hasAnySimilarElements(this.target, this.pokefruit.findCapabilitiesByType(Element.class))) {
            pokefruitType = this.pokefruit.findCapabilitiesByType(Element.class).get(0);
            actor.removeItemFromInventory(this.pokefruit);
            return String.format("%s gives a %s Pokefruit to %s.", actor, pokefruitType, AffectionManager.getInstance().increaseAffection(this.target, 75));
        } else {
            actor.removeItemFromInventory(pokefruit);
            return String.format("%s dislikes it! %s.", actor, AffectionManager.getInstance().decreaseAffection(this.target, 10));
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s feeds %s to %s.",actor,pokefruit, target);
    }

}

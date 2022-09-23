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

    public FeedAction(Pokemon target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    public String execute(Actor actor, GameMap map) { // Do checking here or main file???
        if (!this.target.isConscious()) {
            return String.format("%s is unconscious.", this.target);
        }
        else if (actor.getInventory().size() == 0) {
            return String.format("%s's inventory is empty.", actor);
        }
        // if affection points <= -50, relationship cannot be fixed
        else if (this.target.hasCapability(AffectionLevel.DISLIKE)) {
            return String.format("%s dislikes you.", this.target);
        }
        else {
            int index = 0;

            while (index < actor.getInventory().size()) {
                if (actor.getInventory().get(index).hasCapability(Status.FOOD)) {
                    Item pokefruit = actor.getInventory().get(index);
                    Element pokefruitType;
                    String str;

                    if (ElementsHelper.hasAnySimilarElements(pokefruit, actor.findCapabilitiesByType(Element.class))) {
                        pokefruitType = pokefruit.findCapabilitiesByType(Element.class).get(0);
                        str = String.format("%s gives a %s Pokefruit to %s.", actor, pokefruitType, AffectionManager.getInstance().increaseAffection(this.target, 20));
                    }
                    else {
                        str = String.format("%s dislikes it! %s.", actor, AffectionManager.getInstance().decreaseAffection(this.target, 10));
                    }
                    actor.removeItemFromInventory(pokefruit);
                    return str;
                }
                index += 1;
            }
            return String.format("%s does not have Pokefruit inside inventory.", actor);
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s feeds $s.", target);
    }

}

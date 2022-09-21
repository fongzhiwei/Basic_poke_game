package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.AffectionLevel;
import game.AffectionManager;
import game.Element;
import game.items.Pokefruit;
import game.pokemon.Pokemon;

public class FeedAction extends Action {
    protected Pokemon target;
//    protected String direction;

    public FeedAction(Pokemon target, String direction) {
        this.target = target;
//        this.direction = direction;
    }

    public String execute(Actor actor, GameMap map) {
        if (!this.target.isConscious()) {
            return this.target + "is unconscious.";
        }
        // if affection points <= -50, relationship cannot be fixed
        // --> cannot feed?? can feed but no increase in affection points??
        else if (this.target.getAffectionLevel() == AffectionLevel.DISLIKE) {
            return this.target + "dislikes you.";
        }
        else if (actor.getInventory().size() == 0) {
            return actor + "'s inventory is empty";
        }
        else {
            int index = 0;

            while (index < actor.getInventory().size()) {
                if (actor.getInventory().get(index) instanceof Pokefruit) {
                    Item pokefruit = actor.getInventory().get(index);
                    Element pokefruitType = null;

                    for (Enum<?> elem: pokefruit.capabilitiesList()) {
                        if (elem instanceof Element) {
                            pokefruitType = (Element) elem;
                            break;
                        }
                    }

                    actor.removeItemFromInventory(pokefruit);

                    if ((pokefruit.hasCapability((Enum<?>) this.target.capabilitiesList()))) { // not sure
                        return String.format("%s gives a %s Pokefruit to $s", actor, pokefruitType, AffectionManager.getInstance().increaseAffection(this.target, 20));
                    }
                    else {
                        return String.format("%s dislikes it! %s", actor, AffectionManager.getInstance().decreaseAffection(this.target, 10));
                    }
                }
                index += 1;
            }
            return actor + " does not have pokefruit";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " feeds " + target;
    }

}

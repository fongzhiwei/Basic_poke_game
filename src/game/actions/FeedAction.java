package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.AffectionManager;
import game.items.Pokefruit;
import pokemon.Pokemon;

public class FeedAction extends Action {
    protected Pokemon target;
    protected String direction;

    public FeedAction(Pokemon target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    public String execute(Actor actor, GameMap map) {
        if (!this.target.isConscious()) {
            return this.target + "is unconscious.";
        }
        else if (actor.getInventory().size() == 0) {
            return actor + "'s inventory is empty";
        }
        else {
            int index = 0;

            while (index < actor.getInventory().size()) {
                if (actor.getInventory().get(index) instanceof Pokefruit) {
                    Item pokefruit = actor.getInventory().get(index);
                    actor.removeItemFromInventory(pokefruit);

                    if ((pokefruit.hasCapability((Enum<?>) this.target.capabilitiesList()))) { // not sure
                        return AffectionManager.getInstance().increaseAffection(this.target, 20);
                    }
                    else {
                        return AffectionManager.getInstance().decreaseAffection(this.target, 10);
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

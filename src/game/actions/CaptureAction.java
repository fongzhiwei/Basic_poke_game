package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.AffectionManager;
import game.items.Pokeball;
import game.items.Pokefruit;
import pokemon.Pokemon;

public class CaptureAction extends Action {
    protected Pokemon target;
    protected String direction;

    public CaptureAction(Pokemon target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    public String execute(Actor actor, GameMap map) {
        if (!this.target.isConscious()) {
            return this.target + " is unconscious.";
        }
        else if (this.target.isCaptured()) { // captured pokemon will show on map??
            return this.target + " is already captured.";
        }
        else if (!this.target.isCatchable()) {
            return this.target + " cannot be captured." + AffectionManager.getInstance().decreaseAffection(this.target, 10);
        }
        else if (actor.getInventory().size() == 0) {
            return actor + "'s inventory is empty";
        }
        else {
            int index = 0;

            while (index < actor.getInventory().size()) {
                if (actor.getInventory().get(index) instanceof Pokeball) {
                    Item pokeball = actor.getInventory().get(index);
                    actor.removeItemFromInventory(pokeball);

                    //////////////////////////////////////////////////////
                }
                index += 1;
            }
            return actor + " does not have pokeball";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " captures " + target + " at " + direction;
    }

}

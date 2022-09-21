package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.AffectionManager;
import game.items.Pokeball;
import game.pokemon.Pokemon;

public class CaptureAction extends Action {
    protected Pokemon target;
    protected String direction;

    public CaptureAction(Pokemon target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    public String execute(Actor actor, GameMap map) {
        if (!this.target.isConscious()) {
            return String.format("%s is unconscious.", this.target);
        }
        else if (this.target.isCaptured()) { // captured game.pokemon will show on map?? summoned pokemon needs to be recaptured??
            return String.format("%s is already captured.", this.target);
        }
        else if (!this.target.isCatchable()) {
            return String.format("%s cannot be captured. $s", this.target, AffectionManager.getInstance().decreaseAffection(this.target, 10));
        }
//        else if (actor.getInventory().size() == 0) {
//            return actor + "'s inventory is empty";
//        }
        else {
            Pokeball pokeball = new Pokeball(this.target);
            actor.addItemToInventory(pokeball);
            return String.format("%s captured %s", actor, this.target);
            }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " captures " + target + " at " + direction;
    }

}

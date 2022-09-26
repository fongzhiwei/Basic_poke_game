package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.AffectionManager;
import game.Status;
import game.items.Pokeball;
import game.pokemon.Pokemon;

/**
 * An Action to capture a Pokemon.
 * Created by:
 * @author Leong Xin Yun
 *
 * Modified by:
 *
 */
public class CaptureAction extends Action {
    /**
     * The Pokemon that is to be captured
     */
    protected Pokemon target;

    /**
     * The direction of incoming capture action.
     */
    protected String direction;

    /**
     * Constructor.
     *
     * @param target the Pokemon to capture
     * @param direction the direction of the incoming capture action
     *
     */
    public CaptureAction(Pokemon target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (!this.target.hasCapability(Status.CATCHABLE)) {
            return String.format("%s cannot be captured. %s", this.target, AffectionManager.getInstance().decreaseAffection(this.target, 10));
        }
        else {
            this.target.removeCapability(Status.CATCHABLE);
            Pokeball pokeball = new Pokeball(this.target);
            actor.addItemToInventory(pokeball);
            map.removeActor(this.target); // remove target from gameMap?
            return String.format("%s captured %s", actor, this.target);
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s captures %s at %s.", actor, this.target, this.direction);
    }

}

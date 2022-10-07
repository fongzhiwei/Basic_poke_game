package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.AffectionManager;
import game.Status;
import game.items.Candy;
import game.items.Pokeball;
import game.pokemon.Pokemon;

/**
 * An Action to capture a Pokemon.
 *
 * Created by:
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 * @version 1.0
 *
 * @see edu.monash.fit2099.engine.actions.Action
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
     * Location of the current pokemon to spawn a candy after successful CaptureAction
     */
    protected Location location;

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

    /**
     * Execute capture action for player to catch a pokemon on the map
     * @param actor the actor to catch the pokemon (player)
     * @param map the game map
     * @return a string display after action is completed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (!this.target.hasCapability(Status.CATCHABLE)) {
            return String.format("%s cannot be captured. %s", this.target, AffectionManager.getInstance().decreaseAffection(this.target, 10));
        }
        else {
            Pokeball pokeball = new Pokeball(this.target);
            actor.addItemToInventory(pokeball);
            location = map.locationOf(this.target);
            map.removeActor(this.target); // remove target from gameMap
            location.addItem(new Candy());


            return String.format("%s captured %s", actor, this.target);
        }
    }

    /**
     * Method to return appropriate menu description for the action
     * @param actor the actor who performed the action
     * @return a string for menu description
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s captures %s at %s.", actor, this.target, this.direction);
    }

}

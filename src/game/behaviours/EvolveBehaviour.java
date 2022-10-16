package game.behaviours;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.EvolveAction;
import game.pokemon.CanEvolve;

/**
 * A class that figures out an EvolveAction that will evolve the target Actor
 *
 * Created by:
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 *
 * Modified by:
 *
 * @see Behaviour
 * @see EvolveAction
 * @see CanEvolve
 */
public class EvolveBehaviour implements Behaviour{
    /**
     * Returns an EvolveAction to evolve a target actor, if possible.
     * If no target is found or no evolve action is possible, returns null.
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an Action that actor can perform, or null if actor can't do this.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor.isConscious()) {
            for (Exit elem: map.locationOf(actor).getExits()) {
                if (elem.getDestination().containsAnActor()) {
                    return null;
                }
            }
            return new EvolveAction((CanEvolve) actor);
        }
        return null; // go to next behaviour
    }
}

package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.*;

import java.util.List;

/**
 * A class that figures out an AttackAction that will attack the target Actor
 *
 * Created by:
 * @author Riordan D. Alfredo
 *
 * Modified by:
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 *
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Returns an AttackAction to attack a target actor, if possible.
     * If no target is found or no attack action is possible, returns null.
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an Action that actor can perform, or null if actor can't do this.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // FIXME: fakeOtherActor is a completely new instance that doesn't exist anywhere in the map! Check the requirement.
        if (actor.isConscious()) {
            List<Exit> exits = map.locationOf(actor).getExits();
            Actor otherActor;

            for(Exit elem: exits) {
                if (elem.getDestination().containsAnActor()) {
                    otherActor = elem.getDestination().getActor();

                     if (!ElementsHelper.hasAnySimilarElements(actor, otherActor.findCapabilitiesByType(Element.class)) && (!otherActor.hasCapability(Status.IMMUNE))) {
                        return new AttackAction(otherActor, "here"); // behaviour will stop here.
                    }
                }
            }
        }
        return null; // go to next behaviour
    }
}

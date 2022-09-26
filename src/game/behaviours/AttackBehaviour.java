package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.*;
import game.pokemon.Charmander;

import java.util.List;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Leong Xin Yun
 */
public class AttackBehaviour implements Behaviour {

    /**
     *  HINT: develop a logic to check surrounding, check elements, and return an action to attack that opponent.
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

                    if (ElementsHelper.hasAnySimilarElements(actor, otherActor.findCapabilitiesByType(Element.class))) {
                        return new AttackAction(otherActor, "here"); // behaviour will stop here.
                    }
                }
            }
        }
        return null; // go to next behaviour
    }
}

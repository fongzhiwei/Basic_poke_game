package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.*;
import pokemon.Charmander;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class AttackBehaviour implements Behaviour {

    /**
     *  HINT: develop a logic to check surrounding, check elements, and return an action to attack that opponent.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Actor fakeOtherActor = new Charmander();
        // FIXME: fakeOtherActor is a completely new instance that doesn't exist anywhere in the map! Check the requirement.
        if(ElementsHelper.hasAnySimilarElements(actor, fakeOtherActor.findCapabilitiesByType(Element.class))){
            return new AttackAction(fakeOtherActor, "here"); // behaviour will stop here.
        }
        return null; // go to next behaviour
    }
}

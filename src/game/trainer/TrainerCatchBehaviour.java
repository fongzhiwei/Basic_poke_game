package game.trainer;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.AffectionLevel;
import game.AffectionManager;
import game.Character;
import game.actions.CaptureAction;
import game.behaviours.Behaviour;
import game.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class that represents a behaviour of catch pokemon for trainer.
 * @author Fong Zhiwei <zfon0005@student.monash.edu>
 * @version 1.0
 *
 * @see Behaviour
 * @see AffectionLevel
 * @see AffectionManager
 * @see Character
 * @see CaptureAction
 * @see Behaviour
 * @see Pokemon
 */

public class TrainerCatchBehaviour implements Behaviour {

    private final Random random = new Random();

    /**
     * This method will get the action of catch a pokemon.
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return either the behaviour of catch action or null(go to the check the next behaviour)
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();
        List<Exit> exits = map.locationOf(actor).getExits();
        Pokemon otherActor;

        for (Exit exit : exits) {
            // Character.NPC to check if the actor is a pokemon
            if (exit.getDestination().containsAnActor() && exit.getDestination().getActor().hasCapability(Character.NPC)) {
                // Get the actor on the trainer's exits as a pokemon
                otherActor = (Pokemon) exit.getDestination().getActor();
                // Check if the pokemon's affection > 50
                if (AffectionManager.getInstance().getAffectionPoint(actor, otherActor) > AffectionLevel.LIKE.getPoints()) {
                    actions.add(new CaptureAction(otherActor, exit.getName()));
                }
            }
        }
        if (!actions.isEmpty()) {
            // return a random action from the list of valid actions
            return actions.get(random.nextInt(actions.size()));
        } else {
            return null; // go to next behaviour
        }
    }
}

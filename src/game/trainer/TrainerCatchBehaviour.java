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

public class TrainerCatchBehaviour implements Behaviour {

    private final Random random = new Random();

    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();
        List<Exit> exits = map.locationOf(actor).getExits();
        Pokemon otherActor;

        for (Exit exit : exits) {
            if (exit.getDestination().containsAnActor() && exit.getDestination().getActor().hasCapability(Character.NPC)) {
                otherActor = (Pokemon) exit.getDestination().getActor();
                if (AffectionManager.getInstance().getAffectionPoint(actor, otherActor) > AffectionLevel.LIKE.getPoints()) {
                    actions.add(new CaptureAction(otherActor, exit.getName()));
                }
            }
        }
        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        } else {
            return null; // go to next behaviour
        }
    }
}

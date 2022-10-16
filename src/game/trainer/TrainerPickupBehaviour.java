package game.trainer;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.Behaviour;

import java.util.ArrayList;

/**
 * A class that represents a behaviour of pickup item for trainer.
 * @author Fong Zhiwei <zfon0005@student.monash.edu>
 * @version 1.0
 *
 * @see Behaviour
 */

public class TrainerPickupBehaviour implements Behaviour {

    /**
     * This method will get the action of pickup an item (e.g. PokeFruit)
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return either the behaviour of pickup action or null(go to the check the next behaviour)
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();

        if(map.locationOf(actor).getItems().size() > 0){
            for (Item item : map.locationOf(actor).getItems()){
                if (item.getPickUpAction(actor)!= null)
                actions.add(item.getPickUpAction(actor));
            }
            if (!actions.isEmpty()){
                return actions.get(0);
            }
        }
        return null;
    }
}

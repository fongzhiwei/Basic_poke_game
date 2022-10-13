package game.trainer;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.Behaviour;

import java.util.ArrayList;

public class TrainerPickupBehaviour implements Behaviour {

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

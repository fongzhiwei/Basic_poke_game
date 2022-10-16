package game.trainer;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.*;
import game.Character;
import game.actions.FeedAction;
import game.behaviours.Behaviour;
import game.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a behaviour of feed pokemon for trainer.
 * @author Fong Zhiwei <zfon0005@student.monash.edu>
 * @version 1.0
 *
 */

public class TrainerFeedBehaviour implements Behaviour {

    /**
     * This method will get the action of feed a pokemon with pokefruit
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return either the behaviour of feed action or null(go to the check the next behaviour)
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Item> pokefruits = new ArrayList<>();
        for (Item item: actor.getInventory()){
            if (item.hasCapability(Status.FRUIT)){
                pokefruits.add(item);
            }
        }
        if (!pokefruits.isEmpty()){
            List<Exit> exits = map.locationOf(actor).getExits();
            Pokemon otherActor;

            for (Exit exit: exits){
                if (exit.getDestination().containsAnActor() && exit.getDestination().getActor().hasCapability(Character.NPC)){
                    otherActor = (Pokemon) exit.getDestination().getActor();

                    for (Item pokefruit: pokefruits){
                        if(ElementsHelper.hasAnySimilarElements(pokefruit,otherActor.findCapabilitiesByType(Element.class))){
                            return new FeedAction(otherActor,exit.getName(),pokefruit);
                        }
                    }
                    return new FeedAction(otherActor,exit.getName(), pokefruits.get(Utils.nextNum(0,pokefruits.size())));
                }
            }
        }
        return null;
    }
}

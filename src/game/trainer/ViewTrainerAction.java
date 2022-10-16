package game.trainer;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.AffectionManager;
import game.pokemon.Pokemon;

import java.util.Map;

/**
 * A class that view trainer's action.
 * @author Fong Zhiwei <zfon0005@student.monash.edu>
 * @version 1.0
 *
 * @see Action
 * @see AffectionManager
 * @see Pokemon
 */

public class ViewTrainerAction extends Action {

    protected Trainer trainer;

    /**
     * Constructor.
     * @param trainer a trainer actor
     */
    public ViewTrainerAction(Trainer trainer){
        this.trainer = trainer;
    }

    /**
     * This method view trainer's action and return an appropriate format to print at console.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string format
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String stats =String.format("%s | %s,%s | %s",trainer,map.locationOf(trainer).x(),map.locationOf(trainer).y(),trainer.getInventory());
        Map<Pokemon, Integer> selectedTrainerMap = AffectionManager.getInstance().getTrainerMap().get(trainer);
        for(Map.Entry<Pokemon, Integer> pokemonAffection : selectedTrainerMap.entrySet()){
            if (map.locationOf(pokemonAffection.getKey())!= null && pokemonAffection.getValue() !=0){
                stats += String.format("\n- %s with %s AP at %s,%s",pokemonAffection.getKey(),pokemonAffection.getValue(), map.locationOf(pokemonAffection.getKey()).x(),map.locationOf(pokemonAffection.getKey()).y());
            }
        }

        return stats;
    }

    /**
     * This method return an appropriate string format to print to console.
     *
     * @param actor The actor performing the action.
     * @return a string format
     */
    public String menuDescription(Actor actor) {
        return String.format("Display %s's stats.", trainer);
    }


}

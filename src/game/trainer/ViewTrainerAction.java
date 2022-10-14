package game.trainer;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.AffectionManager;
import game.pokemon.Pokemon;

import java.util.Map;

public class ViewTrainerAction extends Action {

    protected Trainer trainer;

    public ViewTrainerAction(Trainer trainer){
        this.trainer = trainer;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String stats =String.format("%s | %s,%s | %s",trainer,map.locationOf(trainer).x(),map.locationOf(trainer).y(),trainer.getInventory());
        Map<Pokemon, Integer> selectedTrainerMap = AffectionManager.getInstance().getTrainerMap().get(trainer);
        for(Map.Entry<Pokemon, Integer> pokemonAffection : selectedTrainerMap.entrySet()){
            if (map.locationOf(pokemonAffection.getKey())!= null && pokemonAffection.getValue() !=0){
                stats += String.format("\n- %s with %s at %s,%s",pokemonAffection.getKey(),pokemonAffection.getValue(), map.locationOf(pokemonAffection.getKey()).x(),map.locationOf(pokemonAffection.getKey()).y());
            }
        }

        return stats;
    }

    public String menuDescription(Actor actor) {
        return String.format("Display %s's stats.", trainer);
    }
    public String hotkey(String string) {
        return string;
    }
}

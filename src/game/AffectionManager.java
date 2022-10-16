package game;

import edu.monash.fit2099.engine.actors.Actor;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.pokemon.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Affection Manager
 * <p>
 * Created by:
 * @author Riordan D. Alfredo
 *
 * Modified by:
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 * @author Fong Zhiwei <zfon0005@student.monash.edu>
 * @author Soh Meng Jienq <msoh0007@student.monash.edu>
 */
public class AffectionManager {

    /**
     * Singleton instance (the one and only for a whole game).
     */
    private static AffectionManager instance;

    /**
     * A collection of pokemons and their affection points
     */
    private final Map<Pokemon, Integer> affectionPoints;

    private final Map<Actor, Map<Pokemon, Integer>> trainerMap;

    /**
     * The player/trainer in the game
     */
    private Actor trainer;

    /**
     * private singleton constructor
     */
    private AffectionManager() {
        this.affectionPoints = new HashMap<>();
        this.trainerMap = new HashMap<>();
    }

    /**
     * Access single instance publicly
     *
     * @return this instance
     */
    public static AffectionManager getInstance() {
        if (instance == null) {
            instance = new AffectionManager();
        }
        return instance;
    }

    /**
     * Add a trainer to this class's attribute. Assume there's only one trainer at a time.
     *
     * @param player the actor instance
     */
    public void registerPlayer(Actor player) {
        this.trainer = player;
    }

    public void registerTrainer(Actor trainer){
        this.trainerMap.put(trainer, new HashMap<>());
    }

    /**
     * Add Pokemon to the collection. By default, it has 0 affection point. Ideally, you'll register all instantiated Pokemon
     *
     * @param pokemon the Pokemon instance
     */
    public void registerPokemon(Pokemon pokemon) {
        for (Map<Pokemon, Integer> pokemonAffection: trainerMap.values()){
            pokemonAffection.put(pokemon, 0);
        }
    }

    /**
     * Get the affection point by using the game.pokemon instance as the key.
     *
     * @param pokemon Pokemon instance
     * @return integer of affection point.
     */
//    public int getAffectionPoint(Pokemon pokemon) { // changed Charmander game.pokemon --> Pokemon game.pokemon
//        return affectionPoints.get(pokemon);
//    }

    public int getAffectionPoint(Actor trainer, Pokemon pokemon){
        Map<Pokemon, Integer> selectedTrainerMap = trainerMap.get(trainer);
        return selectedTrainerMap.get(pokemon);
    }

    /**
     * Useful method to search a game.pokemon by using Actor instance.
     *
     * @param trainer Actor instance, but we expect a trainer here, e.g. Trainer Goh
     * @param pokemon Actor instance, but we expect a pokemon here, e.g. Charmander
     * @return the Pokemon instance.
     */

    private Pokemon findPokemon(Actor trainer, Actor pokemon) {
        Map<Pokemon, Integer> selectedTrainerMap = trainerMap.get(trainer);
        for (Pokemon poke : selectedTrainerMap.keySet()) {
            if (poke.equals(pokemon)) {
                return poke;
            }
        }
        return null;
    }

    /**
     * Get trainer (e.g. Trainer Goh)
     * @return trainer
     */
    public Actor getTrainer() {
        return trainer;
    }

    /**
     * Increase the Pokemon's affection points.
     *
     * @param trainer Actor instance, but we expect a trainer here, e.g. Trainer Goh
     * @param pokemon the target pokemon that might increase its affection points
     * @param point positive affection modifier (to be subtracted later)
     * @return custom message to be printed by Display instance later.
     */

    public String increaseAffection(Actor trainer, Pokemon pokemon, int point) {
        Map<Pokemon, Integer> selectedTrainerMap = trainerMap.get(trainer);
        if (findPokemon(trainer, pokemon) != null) {
            int oldAP = getAffectionPoint(trainer, pokemon);

            if (oldAP + point >= AffectionLevel.MAX.getPoints()) {
                selectedTrainerMap.replace(pokemon, AffectionLevel.MAX.getPoints());
            }
            else {
                selectedTrainerMap.replace(pokemon, oldAP + point);
            }

            if (getAffectionPoint(trainer, pokemon)>= AffectionLevel.FOLLOW.getPoints()){
                pokemon.getBehaviours().put(1,new FollowBehaviour(trainer));
            }
            return String.format("%s(%d AP)", pokemon, this.getAffectionPoint(trainer, pokemon));
        }
        return String.format("%s does not exist in the collection", pokemon);
    }

    /**
     * Decrease the Pokemon's affection points. Work on both cases when there's a Pokemon,
     * or when it doesn't exist in the collection.
     * @param trainer Actor instance, but we expect a trainer here, e.g. Trainer Goh
     * @param pokemon the target pokemon that might decrease its affection points
     * @param point positive affection modifier (to be subtracted later)
     * @return custom message to be printed by Display instance later.
     */
    public String decreaseAffection(Actor trainer, Pokemon pokemon, int point) {
        Map<Pokemon, Integer> selectedTrainerMap = trainerMap.get(trainer);
        if(findPokemon(trainer, pokemon) != null) {
            int oldAP = this.getAffectionPoint(trainer, pokemon);

            selectedTrainerMap.replace(pokemon, oldAP - point);

            if (oldAP>=AffectionLevel.FOLLOW.getPoints() && getAffectionPoint(trainer,pokemon)< AffectionLevel.FOLLOW.getPoints()){
                pokemon.getBehaviours().remove(1);
            }
            return "-10 affection points";
        }
        return String.format("%s does not exist in the collection", pokemon);
    }

    /**
     * Get trainer's map.
     * @return trainer map
     */
    public Map<Actor, Map<Pokemon, Integer>> getTrainerMap() {
        return trainerMap;
    }
}

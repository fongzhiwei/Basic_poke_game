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
 *
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
     * @param actor general actor instance
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
     * Increase the Pokemon's affection points
     *
     * @param actor Actor instance, but we expect a Pokemon here.
     * @param point positive affection modifier
     * @return custom message to be printed by Display instance later.
     */
//    public String increaseAffection(Pokemon actor, int point) {
//        if (findPokemon(actor) != null) {
//            int oldAP = getAffectionPoint(actor);
//
//            if (oldAP + point >= AffectionLevel.MAX.getPoints()) {
//                this.affectionPoints.replace(actor, AffectionLevel.MAX.getPoints());
//            }
//            else {
//                this.affectionPoints.replace(actor, oldAP + point);
//            }
//            this.updateAffectionLevel(actor);
//            actor.setStatus(getAffectionPoint(actor));
//
//            if (getAffectionPoint(actor)>= AffectionLevel.FOLLOW.getPoints()){
//                actor.getBehaviours().put(1,new FollowBehaviour(trainer));
//            }
//            return String.format("%s(%d AP)", actor, this.getAffectionPoint(actor));
//        }
//        return String.format("%s does not exist in the collection", actor);
//    }

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
            pokemon.setStatus(getAffectionPoint(trainer, pokemon));

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
     *
     * @param actor Actor instance, but we expect a Pokemon here.
     * @param point positive affection modifier (to be subtracted later)
     * @return custom message to be printed by Display instance later.
     */
    public String decreaseAffection(Actor trainer, Pokemon pokemon, int point) {
        Map<Pokemon, Integer> selectedTrainerMap = trainerMap.get(trainer);
        if(findPokemon(trainer, pokemon) != null) {
            int oldAP = this.getAffectionPoint(trainer, pokemon);

            selectedTrainerMap.replace(pokemon, oldAP - point);
            pokemon.setStatus(getAffectionPoint(trainer, pokemon));

            if (oldAP>=AffectionLevel.FOLLOW.getPoints() && getAffectionPoint(trainer,pokemon)< AffectionLevel.FOLLOW.getPoints()){
                pokemon.getBehaviours().remove(1);
            }
            return "-10 affection points";
        }
        return String.format("%s does not exist in the collection", pokemon);
    }

    /**
     * Update the Pokemon's affection level.
     *
     * @param pokemon a Pokemon instance
     */
//    public void updateAffectionLevel(Actor trainer,Pokemon pokemon) {
//        if (getAffectionPoint(trainer,pokemon) < AffectionLevel.NEUTRAL.getPoints() && !pokemon.hasCapability(AffectionLevel.DISLIKE)) {
//            pokemon.setAffectionLevel(AffectionLevel.DISLIKE);
//        } else if (getAffectionPoint(trainer,pokemon) <= AffectionLevel.LIKE.getPoints() && !pokemon.hasCapability(AffectionLevel.NEUTRAL)) {
//            pokemon.setAffectionLevel(AffectionLevel.NEUTRAL);
//        } else if (getAffectionPoint(trainer,pokemon) <= AffectionLevel.FOLLOW.getPoints() && !pokemon.hasCapability(AffectionLevel.LIKE)) {
//            pokemon.setAffectionLevel(AffectionLevel.LIKE);
//        } else if (getAffectionPoint(trainer,pokemon) <= AffectionLevel.MAX.getPoints() && !pokemon.hasCapability(AffectionLevel.FOLLOW)) {
//            pokemon.setAffectionLevel(AffectionLevel.FOLLOW);
//        } else if (getAffectionPoint(trainer,pokemon) == AffectionLevel.MAX.getPoints() && !pokemon.hasCapability(AffectionLevel.MAX)) {
//            pokemon.setAffectionLevel(AffectionLevel.MAX);
//        }
//    }
}

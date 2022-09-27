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

    /**
     * The player/trainer in the game
     */
    private Actor trainer;

    /**
     * private singleton constructor
     */
    private AffectionManager() {
        this.affectionPoints = new HashMap<>();
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
     * @param trainer the actor instance
     */
    public void registerTrainer(Actor trainer) {
        this.trainer = trainer;
    }

    /**
     * Add Pokemon to the collection. By default, it has 0 affection point. Ideally, you'll register all instantiated Pokemon
     *
     * @param pokemon the Pokemon instance
     */
    public void registerPokemon(Pokemon pokemon) {
        this.affectionPoints.put(pokemon, 0);
    }

    /**
     * Get the affection point by using the game.pokemon instance as the key.
     *
     * @param pokemon Pokemon instance
     * @return integer of affection point.
     */
    public int getAffectionPoint(Pokemon pokemon) { // changed Charmander game.pokemon --> Pokemon game.pokemon
        return affectionPoints.get(pokemon);
    }

    /**
     * Useful method to search a game.pokemon by using Actor instance.
     *
     * @param actor general actor instance
     * @return the Pokemon instance.
     */
    private Pokemon findPokemon(Actor actor) {
        for (Pokemon pokemon : affectionPoints.keySet()) {
            if (pokemon.equals(actor)) {
                return pokemon;
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
    public String increaseAffection(Pokemon actor, int point) {
        if (findPokemon(actor) != null) {
            int oldAP = getAffectionPoint(actor);

            if (oldAP + point >= 100) {
                this.affectionPoints.replace(actor, 100);
            }
            else {
                this.affectionPoints.replace(actor, oldAP + point);
            }
            this.updateAffectionLevel(actor);
            actor.setStatus(getAffectionPoint(actor));

            if (getAffectionPoint(actor)>= 75){
                actor.getBehaviours().put(1,new FollowBehaviour(trainer));
                System.out.println("Follow behaviour added");
            }
            return String.format("%s(%d AP)", actor, oldAP);
        }
        return String.format("%s does not exist in the collection", actor);
    }

    /**
     * Decrease the Pokemon's affection points.
     *
     * @param actor Actor instance, but we expect a Pokemon here.
     * @param point positive affection modifier (to be subtracted later)
     * @return custom message to be printed by Display instance later.
     */
    public String decreaseAffection(Pokemon actor, int point) {
        if(findPokemon(actor) != null) {
            int oldAP = this.getAffectionPoint(actor);

            this.affectionPoints.replace(actor, oldAP - point);
            this.updateAffectionLevel(actor);
            actor.setStatus(getAffectionPoint(actor));

            if (getAffectionPoint(actor)< 75){
                actor.getBehaviours().remove(1);
            }
            return "-10 affection points";
        }
        return String.format("%s does not exist in the collection", actor);
    }

    /**
     * Update the Pokemon's affection level.
     *
     * @param pokemon a Pokemon instance
     */
    public void updateAffectionLevel(Pokemon pokemon) {
        if (getAffectionPoint(pokemon) < AffectionLevel.NEUTRAL.getPoints() && !pokemon.hasCapability(AffectionLevel.DISLIKE)) {
            pokemon.setAffectionLevel(AffectionLevel.DISLIKE);
        } else if (getAffectionPoint(pokemon) <= AffectionLevel.LIKE.getPoints() && !pokemon.hasCapability(AffectionLevel.NEUTRAL)) {
            pokemon.setAffectionLevel(AffectionLevel.NEUTRAL);
        } else if (getAffectionPoint(pokemon) <= AffectionLevel.FOLLOW.getPoints() && !pokemon.hasCapability(AffectionLevel.LIKE)) {
            pokemon.setAffectionLevel(AffectionLevel.LIKE);
        } else if (getAffectionPoint(pokemon) <= AffectionLevel.MAX.getPoints() && !pokemon.hasCapability(AffectionLevel.FOLLOW)) {
            pokemon.setAffectionLevel(AffectionLevel.FOLLOW);
        } else if (getAffectionPoint(pokemon) == AffectionLevel.MAX.getPoints() && !pokemon.hasCapability(AffectionLevel.MAX)) {
            pokemon.setAffectionLevel(AffectionLevel.MAX);
        }
    }
}

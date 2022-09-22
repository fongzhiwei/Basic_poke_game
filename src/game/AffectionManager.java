package game;

import edu.monash.fit2099.engine.actors.Actor;
import game.pokemon.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Affection Manager
 * <p>
 * Created by:
 *
 * @author Riordan D. Alfredo
 * Modified by:
 */
public class AffectionManager {

    /**
     * Singleton instance (the one and only for a whole game).
     */
    private static AffectionManager instance;
    /**
     * HINT: is it just for a Charmander?
     */
    private final Map<Pokemon, Integer> affectionPoints; // changed Charmander, Integer --> Pokemon, Integer

    /**
     * We assume there's only one trainer in this manager.
     * Think about how will you extend it.
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
     * @param pokemon
     */
    public void registerPokemon(Pokemon pokemon) { // changed Charmander game.pokemon --> Pokemon game.pokemon
        this.affectionPoints.put(pokemon, this.getAffectionPoint(pokemon));
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
    private Pokemon findPokemon(Actor actor) { // changed Charmander findPokemon() --> Pokemon findPokemon()
        for (Pokemon pokemon : affectionPoints.keySet()) { // changed for(Charmander game.pokemon:) --> for(Pokemon game.pokemon:)
            if (pokemon.equals(actor)) {
                return pokemon;
            }
        }
        return null;
    }

    /**
     * Increase the affection. Work on both cases when there's a Pokemon,
     * or when it doesn't exist in the collection.
     *
     * @param actor Actor instance, but we expect a Pokemon here.
     * @param point positive affection modifier
     * @return custom message to be printed by Display instance later.
     */
    public String increaseAffection(Pokemon actor, int point) {  // changed Actor actor --> Pokemon actor
        if (findPokemon(actor) != null) {
            int oldAP = getAffectionPoint(actor);

            if (oldAP + point >= 100) {
                actor.setAffectionPoints(100);
            }
            else {
                actor.setAffectionPoints(oldAP + point);
            }
            this.affectionPoints.replace(actor, getAffectionPoint(actor) + point);
            updateAffectionLevel(actor);

            return String.format("%s(%d AP)", actor, oldAP);
        }
        return String.format("%s does not exist in the collection", actor);
    }

    /**
     * Decrease the affection level of the . Work on both cases when it is
     *
     * @param actor Actor instance, but we expect a Pokemon here.
     * @param point positive affection modifier (to be subtracted later)
     * @return custom message to be printed by Display instance later.
     */
    public String decreaseAffection(Pokemon actor, int point) { // changed Actor actor --> Pokemon actor
        if(findPokemon(actor) != null) {
            int oldAP = this.getAffectionPoint(actor);
            actor.setAffectionPoints(oldAP - point);
            this.affectionPoints.replace(actor, getAffectionPoint(actor) - point);
            updateAffectionLevel(actor);

            return "-10 affection points";
        }
        return String.format("%s does not exist in the collection", actor);
    }

    public void updateAffectionLevel(Pokemon pokemon) {
        AffectionLevel currentAffectionLevel = pokemon.getAffectionLevel();

        if (this.getAffectionPoint(pokemon) < AffectionLevel.NEUTRAL.getPoints()) {
            if (!pokemon.hasCapability(AffectionLevel.DISLIKE)) {
                pokemon.removeCapability(currentAffectionLevel);
                pokemon.setAffectionLevel(AffectionLevel.DISLIKE);
                pokemon.setStatus();
            }
        }
        else if (this.getAffectionPoint(pokemon) <= AffectionLevel.LIKE.getPoints()) {
            if (!pokemon.hasCapability(AffectionLevel.NEUTRAL)) {
                pokemon.removeCapability(currentAffectionLevel);
                pokemon.setAffectionLevel(AffectionLevel.NEUTRAL);
                pokemon.setStatus();
            }
        }
        else if (this.getAffectionPoint(pokemon) <= AffectionLevel.FOLLOW.getPoints()) {
            if (!pokemon.hasCapability(AffectionLevel.LIKE)) {
                pokemon.removeCapability(currentAffectionLevel);
                pokemon.setAffectionLevel(AffectionLevel.LIKE);
                pokemon.setStatus();
            }
        }
        else if (this.getAffectionPoint(pokemon) <= AffectionLevel.MAX.getPoints()) {
            if (!pokemon.hasCapability(AffectionLevel.FOLLOW)) {
                pokemon.removeCapability(currentAffectionLevel);
                pokemon.setAffectionLevel(AffectionLevel.FOLLOW);
                pokemon.setStatus();
            }
        }
        else if (this.getAffectionPoint(pokemon) == AffectionLevel.MAX.getPoints()) {
            if (!pokemon.hasCapability(AffectionLevel.MAX)) {
                pokemon.removeCapability(currentAffectionLevel);
                pokemon.setAffectionLevel(AffectionLevel.MAX);
                pokemon.setStatus();
            }
        }
    }

}

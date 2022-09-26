package game.time;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Soh Meng Jienq
 * @version 2.0
 *
 * @see game.pokemon.Charmander
 * @see game.pokemon.Squirtle
 * @see game.pokemon.Bulbasaur
 * @see game.environment.Lava
 * @see game.environment.Puddle
 * @see game.environment.Tree
 */

public interface TimePerception {
    /**
     * This method will show the effect of some terrains and pokemons during the period of day.
     */
    void dayEffect();

    /**
     * This method will show the effect of some terrains and pokemons during the period of night.
     */
    void nightEffect();

    /**
     * a default interface method that register current instance to the Singleton manager.
     * It allows corresponding class uses to be affected by global reset
     */
    default void registerInstance(){
        TimePerceptionManager.getInstance().append(this);
    }
}

package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.AffectionManager;
import game.pokemon.CanEvolve;
import game.pokemon.Pokemon;

/**
 * An Action to evolve a Pokemon.
 *
 * Created by:
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 * @version 1.0
 *
 * @see edu.monash.fit2099.engine.actions.Action
 * @see CanEvolve
 * @see AffectionManager
 * @see Pokemon
 */
public class EvolveAction extends Action {
    /**
     * The Pokemon that is to be evolved
     */
    protected CanEvolve target;

    /**
     * Constructor.
     *
     * @param target the Pokemon to be evolved
     *
     */
    public EvolveAction(CanEvolve target) {
        this.target = target;
    }

    /**
     * Execute evolve action to evolve a pokemon on the map
     * @param actor the actor to evolve the pokemon (player)
     * @param map the game map
     * @return a string display after action is completed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String oldPokemon = this.target.toString();
        // Only Pokemons implement CanEvolve interface
        Pokemon pokemon = (Pokemon) target;
        Location currentLocation = map.locationOf(pokemon);

        // Evolved pokemon is the new pokemon's evolved form -- different for different pokemon
        Pokemon evolvedPokemon;
        evolvedPokemon = target.evolve();

        // Remove the old pokemon from the map
        map.removeActor(pokemon);
        // Add in the new pokemon from the map
        map.addActor(evolvedPokemon, currentLocation);
        return String.format("%s evolved to %s", oldPokemon, evolvedPokemon);
    }

    /**
     * Method to return appropriate menu description for the action
     * @param actor the actor who performed the action
     * @return a string for menu description
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("Evolve %s.", this.target);
    }

}

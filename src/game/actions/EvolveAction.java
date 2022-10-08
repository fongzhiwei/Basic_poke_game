package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.pokemon.Charizard;
import game.pokemon.Charmeleon;
import game.pokemon.Pokemon;

/**
 * An Action to evolve a Pokemon.
 *
 * Created by:
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 * @version 1.0
 *
 * @see edu.monash.fit2099.engine.actions.Action
 *
 */
public class EvolveAction extends Action {
    /**
     * The Pokemon that is to be evolved
     */
    protected Pokemon target;

    /**
     * Constructor.
     *
     * @param target the Pokemon to be evolved
     *
     */
    public EvolveAction(Pokemon target) {
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
        Location currentLocation = map.locationOf(this.target);

        map.removeActor(this.target);
        Pokemon evolvedPokemon;

        if (this.target.getDisplayChar() == 'c') {
            evolvedPokemon = new Charmeleon();
        }
        else{
            evolvedPokemon = new Charizard();
        }

        this.target = evolvedPokemon;
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

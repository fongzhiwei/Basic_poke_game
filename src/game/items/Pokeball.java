package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.actions.SummonAction;
import game.pokemon.Pokemon;

/**
 * A class that represents pokeball.
 * @author Fong Zhiwei <zfon0005@student.monash.edu>
 * @version 1.0
 *
 * @see Item
 * @see Status
 * @see Pokemon
 */

public class Pokeball extends Item {

    private Pokemon pokemon;

    /**
     * Constructor.
     * Pokebal shows symbol '0' in game map, and it has a status of ball.
     * @param pokemon Pokemon is the actor that is contained inside the pokeball.
     */
    public Pokeball(Pokemon pokemon){
        super("Pokeball with "+ pokemon.toString(),'0',true);
        this.pokemon = pokemon;
        this.addCapability(Status.BALL);
        this.addAction(new SummonAction(pokemon));
    }

    /**
     * This is a method to get pokemon.
     * @return the pokemon from the pokeball
     */
    public Pokemon getPokemon(){
        return pokemon;
    }

}






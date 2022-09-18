package game.items;

import edu.monash.fit2099.engine.items.Item;
import pokemon.Pokemon;

public class Pokeball extends Item {

    private Pokemon pokemon;

    public Pokeball(Pokemon pokemon){
        super("Pokeball with "+ pokemon.toString(),'0',true);
        this.pokemon = pokemon;
    }
}

package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.pokemon.Pokemon;

public class Pokeball extends Item {

    private Pokemon pokemon;

    public Pokeball(Pokemon pokemon){
        super("Pokeball with "+ pokemon.toString(),'0',true);
        this.pokemon = pokemon;
    }

    public Pokemon getPokemon(){
        return pokemon;
    }}

//    public void addSummonAction(){
//        this.addAction(SummonAction());
//    }
//}

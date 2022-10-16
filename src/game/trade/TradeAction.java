package game.trade;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.Pokefruit;
import game.items.PokemonEgg;
import game.pokemon.BasePokemon;
import game.pokemon.Charmander;
import game.pokemon.Pokemon;

import java.util.ArrayList;

public class TradeAction extends Action {
    /**
     * Trade target (player)
     */
    protected Actor target;

    /**
     * Trade item (merchandise)
     */
    protected Tradable merchandise;

    protected int price;


    public TradeAction(Actor actor, Tradable merchandise, int price){
        this.target = actor;
        this.merchandise = merchandise;
        this.price = price;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        int money = 0;
        int count = 0;
        ArrayList<Integer> candyIndex = new ArrayList<>();


        for(Item item : actor.getInventory()){
            if (item.hasCapability(Status.CURRENCY)){
                money += 1;
                candyIndex.add(actor.getInventory().indexOf(item));
            }
        }
        if (price > money){
            return "Trade cancelled. The player does not have enough candy";
        }
        else {
            while (count < price){
                actor.removeItemFromInventory(actor.getInventory().get(candyIndex.get(count)-count));
                count += 1;
            }
            merchandise.tradedWith(actor);
        }

        return "Trade success. "+ merchandise + " is added";
    }
    public String menuDescription(Actor actor) {
        return actor + " trades " +merchandise+ " with " + price + " candies with Nurse Joy" ;
    }
}



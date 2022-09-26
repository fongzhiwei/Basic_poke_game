package game.trade;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.pokemon.Charmander;
import game.items.Pokeball;

public class TradeCharmanderAction extends Action {

    protected Actor target;
    protected Charmander merchandise;

    protected Pokeball pokeball;

    public TradeCharmanderAction(Actor target){
        this.target = target;
        this.merchandise = new Charmander();
        this.pokeball = new Pokeball(merchandise);
    }

    @Override
    public String execute(Actor target, GameMap map) {
        boolean tradeStatus = merchandise.tradedWith(target);
        if (!tradeStatus){
            return "Trade cancelled. The player does not have enough candy";
        }
        else {
            target.addItemToInventory(pokeball);
            return "Trade success. Charmander inside a pokeball is added";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " trades Charmander with 1 Candy with Nurse Joy" ;
    }
}

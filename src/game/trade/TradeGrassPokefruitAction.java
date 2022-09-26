package game.trade;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Element;
import game.items.Pokefruit;

public class TradeGrassPokefruitAction extends Action {

    protected Actor target;

    protected Pokefruit merchandise;

    public TradeGrassPokefruitAction(Actor target){
        this.target = target;
        this.merchandise = new Pokefruit(Element.FIRE);
    }

    @Override
    public String execute(Actor target, GameMap map) {
        boolean tradeStatus = merchandise.tradedWith(target);
        if (!tradeStatus){
            return "Trade cancelled. The player does not have enough candy";
        }
        else {
            target.addItemToInventory(merchandise);
            return "Trade success. Grass pokefruit is added";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return target + " trades Grass Pokefruit with 1 Candy with Nurse Joy" ;
    }
}
